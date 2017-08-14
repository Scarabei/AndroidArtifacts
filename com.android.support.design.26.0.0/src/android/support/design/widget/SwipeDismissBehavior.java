package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SwipeDismissBehavior extends CoordinatorLayout.Behavior {
   public static final int STATE_IDLE = 0;
   public static final int STATE_DRAGGING = 1;
   public static final int STATE_SETTLING = 2;
   public static final int SWIPE_DIRECTION_START_TO_END = 0;
   public static final int SWIPE_DIRECTION_END_TO_START = 1;
   public static final int SWIPE_DIRECTION_ANY = 2;
   private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5F;
   private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0F;
   private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5F;
   ViewDragHelper mViewDragHelper;
   SwipeDismissBehavior.OnDismissListener mListener;
   private boolean mInterceptingEvents;
   private float mSensitivity = 0.0F;
   private boolean mSensitivitySet;
   int mSwipeDirection = 2;
   float mDragDismissThreshold = 0.5F;
   float mAlphaStartSwipeDistance = 0.0F;
   float mAlphaEndSwipeDistance = 0.5F;
   private final Callback mDragCallback = new Callback() {
      private static final int INVALID_POINTER_ID = -1;
      private int mOriginalCapturedViewLeft;
      private int mActivePointerId = -1;

      public boolean tryCaptureView(View child, int pointerId) {
         return this.mActivePointerId == -1 && SwipeDismissBehavior.this.canSwipeDismissView(child);
      }

      public void onViewCaptured(View capturedChild, int activePointerId) {
         this.mActivePointerId = activePointerId;
         this.mOriginalCapturedViewLeft = capturedChild.getLeft();
         ViewParent parent = capturedChild.getParent();
         if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
         }

      }

      public void onViewDragStateChanged(int state) {
         if (SwipeDismissBehavior.this.mListener != null) {
            SwipeDismissBehavior.this.mListener.onDragStateChanged(state);
         }

      }

      public void onViewReleased(View child, float xvel, float yvel) {
         this.mActivePointerId = -1;
         int childWidth = child.getWidth();
         boolean dismiss = false;
         int targetLeft;
         if (this.shouldDismiss(child, xvel)) {
            targetLeft = child.getLeft() < this.mOriginalCapturedViewLeft ? this.mOriginalCapturedViewLeft - childWidth : this.mOriginalCapturedViewLeft + childWidth;
            dismiss = true;
         } else {
            targetLeft = this.mOriginalCapturedViewLeft;
         }

         if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(targetLeft, child.getTop())) {
            ViewCompat.postOnAnimation(child, SwipeDismissBehavior.this.new SettleRunnable(child, dismiss));
         } else if (dismiss && SwipeDismissBehavior.this.mListener != null) {
            SwipeDismissBehavior.this.mListener.onDismiss(child);
         }

      }

      private boolean shouldDismiss(View child, float xvel) {
         if (xvel != 0.0F) {
            boolean isRtl = ViewCompat.getLayoutDirection(child) == 1;
            if (SwipeDismissBehavior.this.mSwipeDirection == 2) {
               return true;
            } else if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
               return isRtl ? xvel < 0.0F : xvel > 0.0F;
            } else if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
               return isRtl ? xvel > 0.0F : xvel < 0.0F;
            } else {
               return false;
            }
         } else {
            int distance = child.getLeft() - this.mOriginalCapturedViewLeft;
            int thresholdDistance = Math.round((float)child.getWidth() * SwipeDismissBehavior.this.mDragDismissThreshold);
            return Math.abs(distance) >= thresholdDistance;
         }
      }

      public int getViewHorizontalDragRange(View child) {
         return child.getWidth();
      }

      public int clampViewPositionHorizontal(View child, int left, int dx) {
         boolean isRtl = ViewCompat.getLayoutDirection(child) == 1;
         int min;
         int max;
         if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
            if (isRtl) {
               min = this.mOriginalCapturedViewLeft - child.getWidth();
               max = this.mOriginalCapturedViewLeft;
            } else {
               min = this.mOriginalCapturedViewLeft;
               max = this.mOriginalCapturedViewLeft + child.getWidth();
            }
         } else if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
            if (isRtl) {
               min = this.mOriginalCapturedViewLeft;
               max = this.mOriginalCapturedViewLeft + child.getWidth();
            } else {
               min = this.mOriginalCapturedViewLeft - child.getWidth();
               max = this.mOriginalCapturedViewLeft;
            }
         } else {
            min = this.mOriginalCapturedViewLeft - child.getWidth();
            max = this.mOriginalCapturedViewLeft + child.getWidth();
         }

         return SwipeDismissBehavior.clamp(min, left, max);
      }

      public int clampViewPositionVertical(View child, int top, int dy) {
         return child.getTop();
      }

      public void onViewPositionChanged(View child, int left, int top, int dx, int dy) {
         float startAlphaDistance = (float)this.mOriginalCapturedViewLeft + (float)child.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance;
         float endAlphaDistance = (float)this.mOriginalCapturedViewLeft + (float)child.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance;
         if ((float)left <= startAlphaDistance) {
            child.setAlpha(1.0F);
         } else if ((float)left >= endAlphaDistance) {
            child.setAlpha(0.0F);
         } else {
            float distance = SwipeDismissBehavior.fraction(startAlphaDistance, endAlphaDistance, (float)left);
            child.setAlpha(SwipeDismissBehavior.clamp(0.0F, 1.0F - distance, 1.0F));
         }

      }
   };

   public void setListener(SwipeDismissBehavior.OnDismissListener listener) {
      this.mListener = listener;
   }

   public void setSwipeDirection(int direction) {
      this.mSwipeDirection = direction;
   }

   public void setDragDismissDistance(float distance) {
      this.mDragDismissThreshold = clamp(0.0F, distance, 1.0F);
   }

   public void setStartAlphaSwipeDistance(float fraction) {
      this.mAlphaStartSwipeDistance = clamp(0.0F, fraction, 1.0F);
   }

   public void setEndAlphaSwipeDistance(float fraction) {
      this.mAlphaEndSwipeDistance = clamp(0.0F, fraction, 1.0F);
   }

   public void setSensitivity(float sensitivity) {
      this.mSensitivity = sensitivity;
      this.mSensitivitySet = true;
   }

   public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
      boolean dispatchEventToHelper = this.mInterceptingEvents;
      switch(event.getActionMasked()) {
      case 0:
         this.mInterceptingEvents = parent.isPointInChildBounds(child, (int)event.getX(), (int)event.getY());
         dispatchEventToHelper = this.mInterceptingEvents;
         break;
      case 1:
      case 3:
         this.mInterceptingEvents = false;
      case 2:
      }

      if (dispatchEventToHelper) {
         this.ensureViewDragHelper(parent);
         return this.mViewDragHelper.shouldInterceptTouchEvent(event);
      } else {
         return false;
      }
   }

   public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
      if (this.mViewDragHelper != null) {
         this.mViewDragHelper.processTouchEvent(event);
         return true;
      } else {
         return false;
      }
   }

   public boolean canSwipeDismissView(@NonNull View view) {
      return true;
   }

   private void ensureViewDragHelper(ViewGroup parent) {
      if (this.mViewDragHelper == null) {
         this.mViewDragHelper = this.mSensitivitySet ? ViewDragHelper.create(parent, this.mSensitivity, this.mDragCallback) : ViewDragHelper.create(parent, this.mDragCallback);
      }

   }

   static float clamp(float min, float value, float max) {
      return Math.min(Math.max(min, value), max);
   }

   static int clamp(int min, int value, int max) {
      return Math.min(Math.max(min, value), max);
   }

   public int getDragState() {
      return this.mViewDragHelper != null ? this.mViewDragHelper.getViewDragState() : 0;
   }

   static float fraction(float startValue, float endValue, float value) {
      return (value - startValue) / (endValue - startValue);
   }

   private class SettleRunnable implements Runnable {
      private final View mView;
      private final boolean mDismiss;

      SettleRunnable(View view, boolean dismiss) {
         this.mView = view;
         this.mDismiss = dismiss;
      }

      public void run() {
         if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
            ViewCompat.postOnAnimation(this.mView, this);
         } else if (this.mDismiss && SwipeDismissBehavior.this.mListener != null) {
            SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
         }

      }
   }

   public interface OnDismissListener {
      void onDismiss(View var1);

      void onDragStateChanged(int var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   private @interface SwipeDirection {
   }
}
