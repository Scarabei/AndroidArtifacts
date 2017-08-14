package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R.attr;
import android.support.v7.mediarouter.R.string;
import android.support.v7.mediarouter.R.styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;

public class MediaRouteButton extends View {
   private static final String TAG = "MediaRouteButton";
   private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
   private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
   private final MediaRouter mRouter;
   private final MediaRouteButton.MediaRouterCallback mCallback;
   private MediaRouteSelector mSelector;
   private MediaRouteDialogFactory mDialogFactory;
   private boolean mAttachedToWindow;
   private Drawable mRemoteIndicator;
   private boolean mRemoteActive;
   private boolean mCheatSheetEnabled;
   private boolean mIsConnecting;
   private ColorStateList mButtonTint;
   private int mMinWidth;
   private int mMinHeight;
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] CHECKABLE_STATE_SET = new int[]{16842911};

   public MediaRouteButton(Context context) {
      this(context, (AttributeSet)null);
   }

   public MediaRouteButton(Context context, AttributeSet attrs) {
      this(context, attrs, attr.mediaRouteButtonStyle);
   }

   public MediaRouteButton(Context context, AttributeSet attrs, int defStyleAttr) {
      super(MediaRouterThemeHelper.createThemedContext(context, defStyleAttr), attrs, defStyleAttr);
      this.mSelector = MediaRouteSelector.EMPTY;
      this.mDialogFactory = MediaRouteDialogFactory.getDefault();
      context = this.getContext();
      this.mRouter = MediaRouter.getInstance(context);
      this.mCallback = new MediaRouteButton.MediaRouterCallback();
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.MediaRouteButton, defStyleAttr, 0);
      this.mButtonTint = a.getColorStateList(styleable.MediaRouteButton_buttonTint);
      this.setRemoteIndicatorDrawable(a.getDrawable(styleable.MediaRouteButton_externalRouteEnabledDrawable));
      this.mMinWidth = a.getDimensionPixelSize(styleable.MediaRouteButton_android_minWidth, 0);
      this.mMinHeight = a.getDimensionPixelSize(styleable.MediaRouteButton_android_minHeight, 0);
      a.recycle();
      this.updateContentDescription();
      this.setClickable(true);
      this.setLongClickable(true);
   }

   @NonNull
   public MediaRouteSelector getRouteSelector() {
      return this.mSelector;
   }

   public void setRouteSelector(MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         if (!this.mSelector.equals(selector)) {
            if (this.mAttachedToWindow) {
               if (!this.mSelector.isEmpty()) {
                  this.mRouter.removeCallback(this.mCallback);
               }

               if (!selector.isEmpty()) {
                  this.mRouter.addCallback(selector, this.mCallback);
               }
            }

            this.mSelector = selector;
            this.refreshRoute();
         }

      }
   }

   @NonNull
   public MediaRouteDialogFactory getDialogFactory() {
      return this.mDialogFactory;
   }

   public void setDialogFactory(@NonNull MediaRouteDialogFactory factory) {
      if (factory == null) {
         throw new IllegalArgumentException("factory must not be null");
      } else {
         this.mDialogFactory = factory;
      }
   }

   public boolean showDialog() {
      if (!this.mAttachedToWindow) {
         return false;
      } else {
         FragmentManager fm = this.getFragmentManager();
         if (fm == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
         } else {
            MediaRouter.RouteInfo route = this.mRouter.getSelectedRoute();
            if (!route.isDefaultOrBluetooth() && route.matchesSelector(this.mSelector)) {
               if (fm.findFragmentByTag("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                  Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                  return false;
               }

               MediaRouteControllerDialogFragment f = this.mDialogFactory.onCreateControllerDialogFragment();
               f.show(fm, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
            } else {
               if (fm.findFragmentByTag("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                  Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                  return false;
               }

               MediaRouteChooserDialogFragment f = this.mDialogFactory.onCreateChooserDialogFragment();
               f.setRouteSelector(this.mSelector);
               f.show(fm, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
            }

            return true;
         }
      }
   }

   private FragmentManager getFragmentManager() {
      Activity activity = this.getActivity();
      return activity instanceof FragmentActivity ? ((FragmentActivity)activity).getSupportFragmentManager() : null;
   }

   private Activity getActivity() {
      for(Context context = this.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
         if (context instanceof Activity) {
            return (Activity)context;
         }
      }

      return null;
   }

   void setCheatSheetEnabled(boolean enable) {
      this.mCheatSheetEnabled = enable;
   }

   public boolean performClick() {
      boolean handled = super.performClick();
      if (!handled) {
         this.playSoundEffect(0);
      }

      return this.showDialog() || handled;
   }

   public boolean performLongClick() {
      if (super.performLongClick()) {
         return true;
      } else if (!this.mCheatSheetEnabled) {
         return false;
      } else {
         int[] screenPos = new int[2];
         Rect displayFrame = new Rect();
         this.getLocationOnScreen(screenPos);
         this.getWindowVisibleDisplayFrame(displayFrame);
         Context context = this.getContext();
         int width = this.getWidth();
         int height = this.getHeight();
         int midy = screenPos[1] + height / 2;
         int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
         Toast cheatSheet = Toast.makeText(context, string.mr_button_content_description, 0);
         if (midy < displayFrame.height()) {
            cheatSheet.setGravity(8388661, screenWidth - screenPos[0] - width / 2, height);
         } else {
            cheatSheet.setGravity(81, 0, height);
         }

         cheatSheet.show();
         this.performHapticFeedback(0);
         return true;
      }
   }

   protected int[] onCreateDrawableState(int extraSpace) {
      int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
      if (this.mIsConnecting) {
         mergeDrawableStates(drawableState, CHECKABLE_STATE_SET);
      } else if (this.mRemoteActive) {
         mergeDrawableStates(drawableState, CHECKED_STATE_SET);
      }

      return drawableState;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if (this.mRemoteIndicator != null) {
         int[] myDrawableState = this.getDrawableState();
         this.mRemoteIndicator.setState(myDrawableState);
         this.invalidate();
      }

   }

   public void setRemoteIndicatorDrawable(Drawable d) {
      if (this.mRemoteIndicator != null) {
         this.mRemoteIndicator.setCallback((Callback)null);
         this.unscheduleDrawable(this.mRemoteIndicator);
      }

      if (this.mButtonTint != null) {
         d = DrawableCompat.wrap(d.mutate());
         DrawableCompat.setTintList(d, this.mButtonTint);
      }

      this.mRemoteIndicator = d;
      if (d != null) {
         d.setCallback(this);
         d.setState(this.getDrawableState());
         d.setVisible(this.getVisibility() == 0, false);
      }

      this.refreshDrawableState();
   }

   protected boolean verifyDrawable(Drawable who) {
      return super.verifyDrawable(who) || who == this.mRemoteIndicator;
   }

   public void jumpDrawablesToCurrentState() {
      if (this.getBackground() != null) {
         DrawableCompat.jumpToCurrentState(this.getBackground());
      }

      if (this.mRemoteIndicator != null) {
         DrawableCompat.jumpToCurrentState(this.mRemoteIndicator);
      }

   }

   public void setVisibility(int visibility) {
      super.setVisibility(visibility);
      if (this.mRemoteIndicator != null) {
         this.mRemoteIndicator.setVisible(this.getVisibility() == 0, false);
      }

   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mAttachedToWindow = true;
      if (!this.mSelector.isEmpty()) {
         this.mRouter.addCallback(this.mSelector, this.mCallback);
      }

      this.refreshRoute();
   }

   public void onDetachedFromWindow() {
      this.mAttachedToWindow = false;
      if (!this.mSelector.isEmpty()) {
         this.mRouter.removeCallback(this.mCallback);
      }

      super.onDetachedFromWindow();
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthSize = MeasureSpec.getSize(widthMeasureSpec);
      int heightSize = MeasureSpec.getSize(heightMeasureSpec);
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      int heightMode = MeasureSpec.getMode(heightMeasureSpec);
      int width = Math.max(this.mMinWidth, this.mRemoteIndicator != null ? this.mRemoteIndicator.getIntrinsicWidth() + this.getPaddingLeft() + this.getPaddingRight() : 0);
      int height = Math.max(this.mMinHeight, this.mRemoteIndicator != null ? this.mRemoteIndicator.getIntrinsicHeight() + this.getPaddingTop() + this.getPaddingBottom() : 0);
      int measuredWidth;
      switch(widthMode) {
      case Integer.MIN_VALUE:
         measuredWidth = Math.min(widthSize, width);
         break;
      case 0:
      default:
         measuredWidth = width;
         break;
      case 1073741824:
         measuredWidth = widthSize;
      }

      int measuredHeight;
      switch(heightMode) {
      case Integer.MIN_VALUE:
         measuredHeight = Math.min(heightSize, height);
         break;
      case 0:
      default:
         measuredHeight = height;
         break;
      case 1073741824:
         measuredHeight = heightSize;
      }

      this.setMeasuredDimension(measuredWidth, measuredHeight);
   }

   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      if (this.mRemoteIndicator != null) {
         int left = this.getPaddingLeft();
         int right = this.getWidth() - this.getPaddingRight();
         int top = this.getPaddingTop();
         int bottom = this.getHeight() - this.getPaddingBottom();
         int drawWidth = this.mRemoteIndicator.getIntrinsicWidth();
         int drawHeight = this.mRemoteIndicator.getIntrinsicHeight();
         int drawLeft = left + (right - left - drawWidth) / 2;
         int drawTop = top + (bottom - top - drawHeight) / 2;
         this.mRemoteIndicator.setBounds(drawLeft, drawTop, drawLeft + drawWidth, drawTop + drawHeight);
         this.mRemoteIndicator.draw(canvas);
      }

   }

   void refreshRoute() {
      MediaRouter.RouteInfo route = this.mRouter.getSelectedRoute();
      boolean isRemote = !route.isDefaultOrBluetooth() && route.matchesSelector(this.mSelector);
      boolean isConnecting = isRemote && route.isConnecting();
      boolean needsRefresh = false;
      if (this.mRemoteActive != isRemote) {
         this.mRemoteActive = isRemote;
         needsRefresh = true;
      }

      if (this.mIsConnecting != isConnecting) {
         this.mIsConnecting = isConnecting;
         needsRefresh = true;
      }

      if (needsRefresh) {
         this.updateContentDescription();
         this.refreshDrawableState();
      }

      if (this.mAttachedToWindow) {
         this.setEnabled(this.mRouter.isRouteAvailable(this.mSelector, 1));
      }

      if (this.mRemoteIndicator.getCurrent() instanceof AnimationDrawable) {
         AnimationDrawable curDrawable = (AnimationDrawable)this.mRemoteIndicator.getCurrent();
         if (this.mAttachedToWindow) {
            if ((needsRefresh || isConnecting) && !curDrawable.isRunning()) {
               curDrawable.start();
            }
         } else if (isRemote && !isConnecting) {
            if (curDrawable.isRunning()) {
               curDrawable.stop();
            }

            curDrawable.selectDrawable(curDrawable.getNumberOfFrames() - 1);
         }
      }

   }

   private void updateContentDescription() {
      int resId;
      if (this.mIsConnecting) {
         resId = string.mr_cast_button_connecting;
      } else if (this.mRemoteActive) {
         resId = string.mr_cast_button_connected;
      } else {
         resId = string.mr_cast_button_disconnected;
      }

      this.setContentDescription(this.getContext().getString(resId));
   }

   private final class MediaRouterCallback extends MediaRouter.Callback {
      public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onProviderAdded(MediaRouter router, MediaRouter.ProviderInfo provider) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onProviderRemoved(MediaRouter router, MediaRouter.ProviderInfo provider) {
         MediaRouteButton.this.refreshRoute();
      }

      public void onProviderChanged(MediaRouter router, MediaRouter.ProviderInfo provider) {
         MediaRouteButton.this.refreshRoute();
      }
   }
}
