package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.dimen;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(FloatingActionButton.Behavior.class)
public class FloatingActionButton extends VisibilityAwareImageButton {
   private static final String LOG_TAG = "FloatingActionButton";
   public static final int SIZE_MINI = 1;
   public static final int SIZE_NORMAL = 0;
   public static final int SIZE_AUTO = -1;
   private static final int AUTO_MINI_LARGEST_SCREEN_WIDTH = 470;
   private ColorStateList mBackgroundTint;
   private Mode mBackgroundTintMode;
   private int mBorderWidth;
   private int mRippleColor;
   private int mSize;
   int mImagePadding;
   private int mMaxImageSize;
   boolean mCompatPadding;
   final Rect mShadowPadding;
   private final Rect mTouchArea;
   private AppCompatImageHelper mImageHelper;
   private FloatingActionButtonImpl mImpl;

   public FloatingActionButton(Context context) {
      this(context, (AttributeSet)null);
   }

   public FloatingActionButton(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mShadowPadding = new Rect();
      this.mTouchArea = new Rect();
      ThemeUtils.checkAppCompatTheme(context);
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.FloatingActionButton, defStyleAttr, style.Widget_Design_FloatingActionButton);
      this.mBackgroundTint = a.getColorStateList(styleable.FloatingActionButton_backgroundTint);
      this.mBackgroundTintMode = ViewUtils.parseTintMode(a.getInt(styleable.FloatingActionButton_backgroundTintMode, -1), (Mode)null);
      this.mRippleColor = a.getColor(styleable.FloatingActionButton_rippleColor, 0);
      this.mSize = a.getInt(styleable.FloatingActionButton_fabSize, -1);
      this.mBorderWidth = a.getDimensionPixelSize(styleable.FloatingActionButton_borderWidth, 0);
      float elevation = a.getDimension(styleable.FloatingActionButton_elevation, 0.0F);
      float pressedTranslationZ = a.getDimension(styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
      this.mCompatPadding = a.getBoolean(styleable.FloatingActionButton_useCompatPadding, false);
      a.recycle();
      this.mImageHelper = new AppCompatImageHelper(this);
      this.mImageHelper.loadFromAttributes(attrs, defStyleAttr);
      this.mMaxImageSize = (int)this.getResources().getDimension(dimen.design_fab_image_size);
      this.getImpl().setBackgroundDrawable(this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
      this.getImpl().setElevation(elevation);
      this.getImpl().setPressedTranslationZ(pressedTranslationZ);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int preferredSize = this.getSizeDimension();
      this.mImagePadding = (preferredSize - this.mMaxImageSize) / 2;
      this.getImpl().updatePadding();
      int w = resolveAdjustedSize(preferredSize, widthMeasureSpec);
      int h = resolveAdjustedSize(preferredSize, heightMeasureSpec);
      int d = Math.min(w, h);
      this.setMeasuredDimension(d + this.mShadowPadding.left + this.mShadowPadding.right, d + this.mShadowPadding.top + this.mShadowPadding.bottom);
   }

   @ColorInt
   public int getRippleColor() {
      return this.mRippleColor;
   }

   public void setRippleColor(@ColorInt int color) {
      if (this.mRippleColor != color) {
         this.mRippleColor = color;
         this.getImpl().setRippleColor(color);
      }

   }

   @Nullable
   public ColorStateList getBackgroundTintList() {
      return this.mBackgroundTint;
   }

   public void setBackgroundTintList(@Nullable ColorStateList tint) {
      if (this.mBackgroundTint != tint) {
         this.mBackgroundTint = tint;
         this.getImpl().setBackgroundTintList(tint);
      }

   }

   @Nullable
   public Mode getBackgroundTintMode() {
      return this.mBackgroundTintMode;
   }

   public void setBackgroundTintMode(@Nullable Mode tintMode) {
      if (this.mBackgroundTintMode != tintMode) {
         this.mBackgroundTintMode = tintMode;
         this.getImpl().setBackgroundTintMode(tintMode);
      }

   }

   public void setBackgroundDrawable(Drawable background) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setBackgroundResource(int resid) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setBackgroundColor(int color) {
      Log.i("FloatingActionButton", "Setting a custom background is not supported.");
   }

   public void setImageResource(@DrawableRes int resId) {
      this.mImageHelper.setImageResource(resId);
   }

   public void show() {
      this.show((FloatingActionButton.OnVisibilityChangedListener)null);
   }

   public void show(@Nullable FloatingActionButton.OnVisibilityChangedListener listener) {
      this.show(listener, true);
   }

   void show(FloatingActionButton.OnVisibilityChangedListener listener, boolean fromUser) {
      this.getImpl().show(this.wrapOnVisibilityChangedListener(listener), fromUser);
   }

   public void hide() {
      this.hide((FloatingActionButton.OnVisibilityChangedListener)null);
   }

   public void hide(@Nullable FloatingActionButton.OnVisibilityChangedListener listener) {
      this.hide(listener, true);
   }

   void hide(@Nullable FloatingActionButton.OnVisibilityChangedListener listener, boolean fromUser) {
      this.getImpl().hide(this.wrapOnVisibilityChangedListener(listener), fromUser);
   }

   public void setUseCompatPadding(boolean useCompatPadding) {
      if (this.mCompatPadding != useCompatPadding) {
         this.mCompatPadding = useCompatPadding;
         this.getImpl().onCompatShadowChanged();
      }

   }

   public boolean getUseCompatPadding() {
      return this.mCompatPadding;
   }

   public void setSize(int size) {
      if (size != this.mSize) {
         this.mSize = size;
         this.requestLayout();
      }

   }

   public int getSize() {
      return this.mSize;
   }

   @Nullable
   private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable final FloatingActionButton.OnVisibilityChangedListener listener) {
      return listener == null ? null : new FloatingActionButtonImpl.InternalVisibilityChangedListener() {
         public void onShown() {
            listener.onShown(FloatingActionButton.this);
         }

         public void onHidden() {
            listener.onHidden(FloatingActionButton.this);
         }
      };
   }

   int getSizeDimension() {
      return this.getSizeDimension(this.mSize);
   }

   private int getSizeDimension(int size) {
      Resources res = this.getResources();
      switch(size) {
      case -1:
         int width = res.getConfiguration().screenWidthDp;
         int height = res.getConfiguration().screenHeightDp;
         return Math.max(width, height) < 470 ? this.getSizeDimension(1) : this.getSizeDimension(0);
      case 0:
      default:
         return res.getDimensionPixelSize(dimen.design_fab_size_normal);
      case 1:
         return res.getDimensionPixelSize(dimen.design_fab_size_mini);
      }
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.getImpl().onAttachedToWindow();
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.getImpl().onDetachedFromWindow();
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      this.getImpl().onDrawableStateChanged(this.getDrawableState());
   }

   public void jumpDrawablesToCurrentState() {
      super.jumpDrawablesToCurrentState();
      this.getImpl().jumpDrawableToCurrentState();
   }

   public boolean getContentRect(@NonNull Rect rect) {
      if (ViewCompat.isLaidOut(this)) {
         rect.set(0, 0, this.getWidth(), this.getHeight());
         rect.left += this.mShadowPadding.left;
         rect.top += this.mShadowPadding.top;
         rect.right -= this.mShadowPadding.right;
         rect.bottom -= this.mShadowPadding.bottom;
         return true;
      } else {
         return false;
      }
   }

   @NonNull
   public Drawable getContentBackground() {
      return this.getImpl().getContentBackground();
   }

   private static int resolveAdjustedSize(int desiredSize, int measureSpec) {
      int result = desiredSize;
      int specMode = MeasureSpec.getMode(measureSpec);
      int specSize = MeasureSpec.getSize(measureSpec);
      switch(specMode) {
      case Integer.MIN_VALUE:
         result = Math.min(desiredSize, specSize);
         break;
      case 0:
         result = desiredSize;
         break;
      case 1073741824:
         result = specSize;
      }

      return result;
   }

   public boolean onTouchEvent(MotionEvent ev) {
      switch(ev.getAction()) {
      case 0:
         if (this.getContentRect(this.mTouchArea) && !this.mTouchArea.contains((int)ev.getX(), (int)ev.getY())) {
            return false;
         }
      default:
         return super.onTouchEvent(ev);
      }
   }

   public float getCompatElevation() {
      return this.getImpl().getElevation();
   }

   public void setCompatElevation(float elevation) {
      this.getImpl().setElevation(elevation);
   }

   private FloatingActionButtonImpl getImpl() {
      if (this.mImpl == null) {
         this.mImpl = this.createImpl();
      }

      return this.mImpl;
   }

   private FloatingActionButtonImpl createImpl() {
      return (FloatingActionButtonImpl)(VERSION.SDK_INT >= 21 ? new FloatingActionButtonLollipop(this, new FloatingActionButton.ShadowDelegateImpl()) : new FloatingActionButtonImpl(this, new FloatingActionButton.ShadowDelegateImpl()));
   }

   private class ShadowDelegateImpl implements ShadowViewDelegate {
      public float getRadius() {
         return (float)FloatingActionButton.this.getSizeDimension() / 2.0F;
      }

      public void setShadowPadding(int left, int top, int right, int bottom) {
         FloatingActionButton.this.mShadowPadding.set(left, top, right, bottom);
         FloatingActionButton.this.setPadding(left + FloatingActionButton.this.mImagePadding, top + FloatingActionButton.this.mImagePadding, right + FloatingActionButton.this.mImagePadding, bottom + FloatingActionButton.this.mImagePadding);
      }

      public void setBackgroundDrawable(Drawable background) {
         FloatingActionButton.super.setBackgroundDrawable(background);
      }

      public boolean isCompatPaddingEnabled() {
         return FloatingActionButton.this.mCompatPadding;
      }
   }

   public static class Behavior extends CoordinatorLayout.Behavior {
      private static final boolean AUTO_HIDE_DEFAULT = true;
      private Rect mTmpRect;
      private FloatingActionButton.OnVisibilityChangedListener mInternalAutoHideListener;
      private boolean mAutoHideEnabled;

      public Behavior() {
         this.mAutoHideEnabled = true;
      }

      public Behavior(Context context, AttributeSet attrs) {
         super(context, attrs);
         TypedArray a = context.obtainStyledAttributes(attrs, styleable.FloatingActionButton_Behavior_Layout);
         this.mAutoHideEnabled = a.getBoolean(styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
         a.recycle();
      }

      public void setAutoHideEnabled(boolean autoHide) {
         this.mAutoHideEnabled = autoHide;
      }

      public boolean isAutoHideEnabled() {
         return this.mAutoHideEnabled;
      }

      public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams lp) {
         if (lp.dodgeInsetEdges == 0) {
            lp.dodgeInsetEdges = 80;
         }

      }

      public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
         if (dependency instanceof AppBarLayout) {
            this.updateFabVisibilityForAppBarLayout(parent, (AppBarLayout)dependency, child);
         } else if (isBottomSheet(dependency)) {
            this.updateFabVisibilityForBottomSheet(dependency, child);
         }

         return false;
      }

      private static boolean isBottomSheet(@NonNull View view) {
         LayoutParams lp = view.getLayoutParams();
         return lp instanceof CoordinatorLayout.LayoutParams ? ((CoordinatorLayout.LayoutParams)lp).getBehavior() instanceof BottomSheetBehavior : false;
      }

      @VisibleForTesting
      void setInternalAutoHideListener(FloatingActionButton.OnVisibilityChangedListener listener) {
         this.mInternalAutoHideListener = listener;
      }

      private boolean shouldUpdateVisibility(View dependency, FloatingActionButton child) {
         CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
         if (!this.mAutoHideEnabled) {
            return false;
         } else if (lp.getAnchorId() != dependency.getId()) {
            return false;
         } else {
            return child.getUserSetVisibility() == 0;
         }
      }

      private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout parent, AppBarLayout appBarLayout, FloatingActionButton child) {
         if (!this.shouldUpdateVisibility(appBarLayout, child)) {
            return false;
         } else {
            if (this.mTmpRect == null) {
               this.mTmpRect = new Rect();
            }

            Rect rect = this.mTmpRect;
            ViewGroupUtils.getDescendantRect(parent, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
               child.hide(this.mInternalAutoHideListener, false);
            } else {
               child.show(this.mInternalAutoHideListener, false);
            }

            return true;
         }
      }

      private boolean updateFabVisibilityForBottomSheet(View bottomSheet, FloatingActionButton child) {
         if (!this.shouldUpdateVisibility(bottomSheet, child)) {
            return false;
         } else {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            if (bottomSheet.getTop() < child.getHeight() / 2 + lp.topMargin) {
               child.hide(this.mInternalAutoHideListener, false);
            } else {
               child.show(this.mInternalAutoHideListener, false);
            }

            return true;
         }
      }

      public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int layoutDirection) {
         List dependencies = parent.getDependencies(child);
         int i = 0;

         for(int count = dependencies.size(); i < count; ++i) {
            View dependency = (View)dependencies.get(i);
            if (dependency instanceof AppBarLayout) {
               if (this.updateFabVisibilityForAppBarLayout(parent, (AppBarLayout)dependency, child)) {
                  break;
               }
            } else if (isBottomSheet(dependency) && this.updateFabVisibilityForBottomSheet(dependency, child)) {
               break;
            }
         }

         parent.onLayoutChild(child, layoutDirection);
         this.offsetIfNeeded(parent, child);
         return true;
      }

      public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull Rect rect) {
         Rect shadowPadding = child.mShadowPadding;
         rect.set(child.getLeft() + shadowPadding.left, child.getTop() + shadowPadding.top, child.getRight() - shadowPadding.right, child.getBottom() - shadowPadding.bottom);
         return true;
      }

      private void offsetIfNeeded(CoordinatorLayout parent, FloatingActionButton fab) {
         Rect padding = fab.mShadowPadding;
         if (padding != null && padding.centerX() > 0 && padding.centerY() > 0) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)fab.getLayoutParams();
            int offsetTB = 0;
            int offsetLR = 0;
            if (fab.getRight() >= parent.getWidth() - lp.rightMargin) {
               offsetLR = padding.right;
            } else if (fab.getLeft() <= lp.leftMargin) {
               offsetLR = -padding.left;
            }

            if (fab.getBottom() >= parent.getHeight() - lp.bottomMargin) {
               offsetTB = padding.bottom;
            } else if (fab.getTop() <= lp.topMargin) {
               offsetTB = -padding.top;
            }

            if (offsetTB != 0) {
               ViewCompat.offsetTopAndBottom(fab, offsetTB);
            }

            if (offsetLR != 0) {
               ViewCompat.offsetLeftAndRight(fab, offsetLR);
            }
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface Size {
   }

   public abstract static class OnVisibilityChangedListener {
      public void onShown(FloatingActionButton fab) {
      }

      public void onHidden(FloatingActionButton fab) {
      }
   }
}
