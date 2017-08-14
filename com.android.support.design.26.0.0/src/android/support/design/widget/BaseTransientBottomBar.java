package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R.anim;
import android.support.design.R.layout;
import android.support.design.R.styleable;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar {
   public static final int LENGTH_INDEFINITE = -2;
   public static final int LENGTH_SHORT = -1;
   public static final int LENGTH_LONG = 0;
   static final int ANIMATION_DURATION = 250;
   static final int ANIMATION_FADE_DURATION = 180;
   static final Handler sHandler;
   static final int MSG_SHOW = 0;
   static final int MSG_DISMISS = 1;
   private static final boolean USE_OFFSET_API;
   private final ViewGroup mTargetParent;
   private final Context mContext;
   final BaseTransientBottomBar.SnackbarBaseLayout mView;
   private final BaseTransientBottomBar.ContentViewCallback mContentViewCallback;
   private int mDuration;
   private List mCallbacks;
   private final AccessibilityManager mAccessibilityManager;
   final SnackbarManager.Callback mManagerCallback = new SnackbarManager.Callback() {
      public void show() {
         BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(0, BaseTransientBottomBar.this));
      }

      public void dismiss(int event) {
         BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(1, event, 0, BaseTransientBottomBar.this));
      }
   };

   protected BaseTransientBottomBar(@NonNull ViewGroup parent, @NonNull View content, @NonNull BaseTransientBottomBar.ContentViewCallback contentViewCallback) {
      if (parent == null) {
         throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
      } else if (content == null) {
         throw new IllegalArgumentException("Transient bottom bar must have non-null content");
      } else if (contentViewCallback == null) {
         throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
      } else {
         this.mTargetParent = parent;
         this.mContentViewCallback = contentViewCallback;
         this.mContext = parent.getContext();
         ThemeUtils.checkAppCompatTheme(this.mContext);
         LayoutInflater inflater = LayoutInflater.from(this.mContext);
         this.mView = (BaseTransientBottomBar.SnackbarBaseLayout)inflater.inflate(layout.design_layout_snackbar, this.mTargetParent, false);
         this.mView.addView(content);
         ViewCompat.setAccessibilityLiveRegion(this.mView, 1);
         ViewCompat.setImportantForAccessibility(this.mView, 1);
         ViewCompat.setFitsSystemWindows(this.mView, true);
         ViewCompat.setOnApplyWindowInsetsListener(this.mView, new OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
               v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), insets.getSystemWindowInsetBottom());
               return insets;
            }
         });
         this.mAccessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
      }
   }

   @NonNull
   public BaseTransientBottomBar setDuration(int duration) {
      this.mDuration = duration;
      return this;
   }

   public int getDuration() {
      return this.mDuration;
   }

   @NonNull
   public Context getContext() {
      return this.mContext;
   }

   @NonNull
   public View getView() {
      return this.mView;
   }

   public void show() {
      SnackbarManager.getInstance().show(this.mDuration, this.mManagerCallback);
   }

   public void dismiss() {
      this.dispatchDismiss(3);
   }

   void dispatchDismiss(int event) {
      SnackbarManager.getInstance().dismiss(this.mManagerCallback, event);
   }

   @NonNull
   public BaseTransientBottomBar addCallback(@NonNull BaseTransientBottomBar.BaseCallback callback) {
      if (callback == null) {
         return this;
      } else {
         if (this.mCallbacks == null) {
            this.mCallbacks = new ArrayList();
         }

         this.mCallbacks.add(callback);
         return this;
      }
   }

   @NonNull
   public BaseTransientBottomBar removeCallback(@NonNull BaseTransientBottomBar.BaseCallback callback) {
      if (callback == null) {
         return this;
      } else if (this.mCallbacks == null) {
         return this;
      } else {
         this.mCallbacks.remove(callback);
         return this;
      }
   }

   public boolean isShown() {
      return SnackbarManager.getInstance().isCurrent(this.mManagerCallback);
   }

   public boolean isShownOrQueued() {
      return SnackbarManager.getInstance().isCurrentOrNext(this.mManagerCallback);
   }

   final void showView() {
      if (this.mView.getParent() == null) {
         LayoutParams lp = this.mView.getLayoutParams();
         if (lp instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams clp = (CoordinatorLayout.LayoutParams)lp;
            BaseTransientBottomBar.Behavior behavior = new BaseTransientBottomBar.Behavior();
            behavior.setStartAlphaSwipeDistance(0.1F);
            behavior.setEndAlphaSwipeDistance(0.6F);
            behavior.setSwipeDirection(0);
            behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
               public void onDismiss(View view) {
                  view.setVisibility(8);
                  BaseTransientBottomBar.this.dispatchDismiss(0);
               }

               public void onDragStateChanged(int state) {
                  switch(state) {
                  case 0:
                     SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.mManagerCallback);
                     break;
                  case 1:
                  case 2:
                     SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.mManagerCallback);
                  }

               }
            });
            clp.setBehavior(behavior);
            clp.insetEdge = 80;
         }

         this.mTargetParent.addView(this.mView);
      }

      this.mView.setOnAttachStateChangeListener(new BaseTransientBottomBar.OnAttachStateChangeListener() {
         public void onViewAttachedToWindow(View v) {
         }

         public void onViewDetachedFromWindow(View v) {
            if (BaseTransientBottomBar.this.isShownOrQueued()) {
               BaseTransientBottomBar.sHandler.post(new Runnable() {
                  public void run() {
                     BaseTransientBottomBar.this.onViewHidden(3);
                  }
               });
            }

         }
      });
      if (ViewCompat.isLaidOut(this.mView)) {
         if (this.shouldAnimate()) {
            this.animateViewIn();
         } else {
            this.onViewShown();
         }
      } else {
         this.mView.setOnLayoutChangeListener(new BaseTransientBottomBar.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int left, int top, int right, int bottom) {
               BaseTransientBottomBar.this.mView.setOnLayoutChangeListener((BaseTransientBottomBar.OnLayoutChangeListener)null);
               if (BaseTransientBottomBar.this.shouldAnimate()) {
                  BaseTransientBottomBar.this.animateViewIn();
               } else {
                  BaseTransientBottomBar.this.onViewShown();
               }

            }
         });
      }

   }

   void animateViewIn() {
      if (VERSION.SDK_INT >= 12) {
         final int viewHeight = this.mView.getHeight();
         if (USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(this.mView, viewHeight);
         } else {
            this.mView.setTranslationY((float)viewHeight);
         }

         ValueAnimator animator = new ValueAnimator();
         animator.setIntValues(new int[]{viewHeight, 0});
         animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         animator.setDuration(250L);
         animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
               BaseTransientBottomBar.this.mContentViewCallback.animateContentIn(70, 180);
            }

            public void onAnimationEnd(Animator animator) {
               BaseTransientBottomBar.this.onViewShown();
            }
         });
         animator.addUpdateListener(new AnimatorUpdateListener() {
            private int mPreviousAnimatedIntValue = viewHeight;

            public void onAnimationUpdate(ValueAnimator animator) {
               int currentAnimatedIntValue = ((Integer)animator.getAnimatedValue()).intValue();
               if (BaseTransientBottomBar.USE_OFFSET_API) {
                  ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.mView, currentAnimatedIntValue - this.mPreviousAnimatedIntValue);
               } else {
                  BaseTransientBottomBar.this.mView.setTranslationY((float)currentAnimatedIntValue);
               }

               this.mPreviousAnimatedIntValue = currentAnimatedIntValue;
            }
         });
         animator.start();
      } else {
         Animation anim = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), anim.design_snackbar_in);
         anim.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         anim.setDuration(250L);
         anim.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
               BaseTransientBottomBar.this.onViewShown();
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
         });
         this.mView.startAnimation(anim);
      }

   }

   private void animateViewOut(final int event) {
      if (VERSION.SDK_INT >= 12) {
         ValueAnimator animator = new ValueAnimator();
         animator.setIntValues(new int[]{0, this.mView.getHeight()});
         animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         animator.setDuration(250L);
         animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
               BaseTransientBottomBar.this.mContentViewCallback.animateContentOut(0, 180);
            }

            public void onAnimationEnd(Animator animator) {
               BaseTransientBottomBar.this.onViewHidden(event);
            }
         });
         animator.addUpdateListener(new AnimatorUpdateListener() {
            private int mPreviousAnimatedIntValue = 0;

            public void onAnimationUpdate(ValueAnimator animator) {
               int currentAnimatedIntValue = ((Integer)animator.getAnimatedValue()).intValue();
               if (BaseTransientBottomBar.USE_OFFSET_API) {
                  ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.mView, currentAnimatedIntValue - this.mPreviousAnimatedIntValue);
               } else {
                  BaseTransientBottomBar.this.mView.setTranslationY((float)currentAnimatedIntValue);
               }

               this.mPreviousAnimatedIntValue = currentAnimatedIntValue;
            }
         });
         animator.start();
      } else {
         Animation anim = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), anim.design_snackbar_out);
         anim.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         anim.setDuration(250L);
         anim.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
               BaseTransientBottomBar.this.onViewHidden(event);
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
         });
         this.mView.startAnimation(anim);
      }

   }

   final void hideView(int event) {
      if (this.shouldAnimate() && this.mView.getVisibility() == 0) {
         this.animateViewOut(event);
      } else {
         this.onViewHidden(event);
      }

   }

   void onViewShown() {
      SnackbarManager.getInstance().onShown(this.mManagerCallback);
      if (this.mCallbacks != null) {
         int callbackCount = this.mCallbacks.size();

         for(int i = callbackCount - 1; i >= 0; --i) {
            ((BaseTransientBottomBar.BaseCallback)this.mCallbacks.get(i)).onShown(this);
         }
      }

   }

   void onViewHidden(int event) {
      SnackbarManager.getInstance().onDismissed(this.mManagerCallback);
      if (this.mCallbacks != null) {
         int callbackCount = this.mCallbacks.size();

         for(int i = callbackCount - 1; i >= 0; --i) {
            ((BaseTransientBottomBar.BaseCallback)this.mCallbacks.get(i)).onDismissed(this, event);
         }
      }

      if (VERSION.SDK_INT < 11) {
         this.mView.setVisibility(8);
      }

      ViewParent parent = this.mView.getParent();
      if (parent instanceof ViewGroup) {
         ((ViewGroup)parent).removeView(this.mView);
      }

   }

   boolean shouldAnimate() {
      return !this.mAccessibilityManager.isEnabled();
   }

   static {
      USE_OFFSET_API = VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19;
      sHandler = new Handler(Looper.getMainLooper(), new Callback() {
         public boolean handleMessage(Message message) {
            switch(message.what) {
            case 0:
               ((BaseTransientBottomBar)message.obj).showView();
               return true;
            case 1:
               ((BaseTransientBottomBar)message.obj).hideView(message.arg1);
               return true;
            default:
               return false;
            }
         }
      });
   }

   final class Behavior extends SwipeDismissBehavior {
      public boolean canSwipeDismissView(View child) {
         return child instanceof BaseTransientBottomBar.SnackbarBaseLayout;
      }

      public boolean onInterceptTouchEvent(CoordinatorLayout parent, BaseTransientBottomBar.SnackbarBaseLayout child, MotionEvent event) {
         switch(event.getActionMasked()) {
         case 0:
            if (parent.isPointInChildBounds(child, (int)event.getX(), (int)event.getY())) {
               SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.mManagerCallback);
            }
            break;
         case 1:
         case 3:
            SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.mManagerCallback);
         case 2:
         }

         return super.onInterceptTouchEvent(parent, child, event);
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   static class SnackbarBaseLayout extends FrameLayout {
      private BaseTransientBottomBar.OnLayoutChangeListener mOnLayoutChangeListener;
      private BaseTransientBottomBar.OnAttachStateChangeListener mOnAttachStateChangeListener;

      SnackbarBaseLayout(Context context) {
         this(context, (AttributeSet)null);
      }

      SnackbarBaseLayout(Context context, AttributeSet attrs) {
         super(context, attrs);
         TypedArray a = context.obtainStyledAttributes(attrs, styleable.SnackbarLayout);
         if (a.hasValue(styleable.SnackbarLayout_elevation)) {
            ViewCompat.setElevation(this, (float)a.getDimensionPixelSize(styleable.SnackbarLayout_elevation, 0));
         }

         a.recycle();
         this.setClickable(true);
      }

      protected void onLayout(boolean changed, int l, int t, int r, int b) {
         super.onLayout(changed, l, t, r, b);
         if (this.mOnLayoutChangeListener != null) {
            this.mOnLayoutChangeListener.onLayoutChange(this, l, t, r, b);
         }

      }

      protected void onAttachedToWindow() {
         super.onAttachedToWindow();
         if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewAttachedToWindow(this);
         }

         ViewCompat.requestApplyInsets(this);
      }

      protected void onDetachedFromWindow() {
         super.onDetachedFromWindow();
         if (this.mOnAttachStateChangeListener != null) {
            this.mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
         }

      }

      void setOnLayoutChangeListener(BaseTransientBottomBar.OnLayoutChangeListener onLayoutChangeListener) {
         this.mOnLayoutChangeListener = onLayoutChangeListener;
      }

      void setOnAttachStateChangeListener(BaseTransientBottomBar.OnAttachStateChangeListener listener) {
         this.mOnAttachStateChangeListener = listener;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   interface OnAttachStateChangeListener {
      void onViewAttachedToWindow(View var1);

      void onViewDetachedFromWindow(View var1);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   interface OnLayoutChangeListener {
      void onLayoutChange(View var1, int var2, int var3, int var4, int var5);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   @IntRange(
      from = 1L
   )
   public @interface Duration {
   }

   public interface ContentViewCallback {
      void animateContentIn(int var1, int var2);

      void animateContentOut(int var1, int var2);
   }

   public abstract static class BaseCallback {
      public static final int DISMISS_EVENT_SWIPE = 0;
      public static final int DISMISS_EVENT_ACTION = 1;
      public static final int DISMISS_EVENT_TIMEOUT = 2;
      public static final int DISMISS_EVENT_MANUAL = 3;
      public static final int DISMISS_EVENT_CONSECUTIVE = 4;

      public void onDismissed(Object transientBottomBar, int event) {
      }

      public void onShown(Object transientBottomBar) {
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      public @interface DismissEvent {
      }
   }
}
