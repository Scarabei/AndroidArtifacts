package android.support.design.widget;

import android.content.Context;
import android.support.v4.math.MathUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior extends ViewOffsetBehavior {
   private static final int INVALID_POINTER = -1;
   private Runnable mFlingRunnable;
   OverScroller mScroller;
   private boolean mIsBeingDragged;
   private int mActivePointerId = -1;
   private int mLastMotionY;
   private int mTouchSlop = -1;
   private VelocityTracker mVelocityTracker;

   public HeaderBehavior() {
   }

   public HeaderBehavior(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
      if (this.mTouchSlop < 0) {
         this.mTouchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
      }

      int action = ev.getAction();
      if (action == 2 && this.mIsBeingDragged) {
         return true;
      } else {
         int activePointerId;
         int pointerIndex;
         switch(ev.getActionMasked()) {
         case 0:
            this.mIsBeingDragged = false;
            activePointerId = (int)ev.getX();
            pointerIndex = (int)ev.getY();
            if (this.canDragView(child) && parent.isPointInChildBounds(child, activePointerId, pointerIndex)) {
               this.mLastMotionY = pointerIndex;
               this.mActivePointerId = ev.getPointerId(0);
               this.ensureVelocityTracker();
            }
            break;
         case 1:
         case 3:
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker != null) {
               this.mVelocityTracker.recycle();
               this.mVelocityTracker = null;
            }
            break;
         case 2:
            activePointerId = this.mActivePointerId;
            if (activePointerId != -1) {
               pointerIndex = ev.findPointerIndex(activePointerId);
               if (pointerIndex != -1) {
                  int y = (int)ev.getY(pointerIndex);
                  int yDiff = Math.abs(y - this.mLastMotionY);
                  if (yDiff > this.mTouchSlop) {
                     this.mIsBeingDragged = true;
                     this.mLastMotionY = y;
                  }
               }
            }
         }

         if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(ev);
         }

         return this.mIsBeingDragged;
      }
   }

   public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
      if (this.mTouchSlop < 0) {
         this.mTouchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
      }

      int activePointerIndex;
      int y;
      switch(ev.getActionMasked()) {
      case 0:
         activePointerIndex = (int)ev.getX();
         y = (int)ev.getY();
         if (!parent.isPointInChildBounds(child, activePointerIndex, y) || !this.canDragView(child)) {
            return false;
         }

         this.mLastMotionY = y;
         this.mActivePointerId = ev.getPointerId(0);
         this.ensureVelocityTracker();
         break;
      case 1:
         if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(ev);
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float yvel = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            this.fling(parent, child, -this.getScrollRangeForDragFling(child), 0, yvel);
         }
      case 3:
         this.mIsBeingDragged = false;
         this.mActivePointerId = -1;
         if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
         }
         break;
      case 2:
         activePointerIndex = ev.findPointerIndex(this.mActivePointerId);
         if (activePointerIndex == -1) {
            return false;
         }

         y = (int)ev.getY(activePointerIndex);
         int dy = this.mLastMotionY - y;
         if (!this.mIsBeingDragged && Math.abs(dy) > this.mTouchSlop) {
            this.mIsBeingDragged = true;
            if (dy > 0) {
               dy -= this.mTouchSlop;
            } else {
               dy += this.mTouchSlop;
            }
         }

         if (this.mIsBeingDragged) {
            this.mLastMotionY = y;
            this.scroll(parent, child, dy, this.getMaxDragOffset(child), 0);
         }
      }

      if (this.mVelocityTracker != null) {
         this.mVelocityTracker.addMovement(ev);
      }

      return true;
   }

   int setHeaderTopBottomOffset(CoordinatorLayout parent, View header, int newOffset) {
      return this.setHeaderTopBottomOffset(parent, header, newOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);
   }

   int setHeaderTopBottomOffset(CoordinatorLayout parent, View header, int newOffset, int minOffset, int maxOffset) {
      int curOffset = this.getTopAndBottomOffset();
      int consumed = 0;
      if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset) {
         newOffset = MathUtils.clamp(newOffset, minOffset, maxOffset);
         if (curOffset != newOffset) {
            this.setTopAndBottomOffset(newOffset);
            consumed = curOffset - newOffset;
         }
      }

      return consumed;
   }

   int getTopBottomOffsetForScrollingSibling() {
      return this.getTopAndBottomOffset();
   }

   final int scroll(CoordinatorLayout coordinatorLayout, View header, int dy, int minOffset, int maxOffset) {
      return this.setHeaderTopBottomOffset(coordinatorLayout, header, this.getTopBottomOffsetForScrollingSibling() - dy, minOffset, maxOffset);
   }

   final boolean fling(CoordinatorLayout coordinatorLayout, View layout, int minOffset, int maxOffset, float velocityY) {
      if (this.mFlingRunnable != null) {
         layout.removeCallbacks(this.mFlingRunnable);
         this.mFlingRunnable = null;
      }

      if (this.mScroller == null) {
         this.mScroller = new OverScroller(layout.getContext());
      }

      this.mScroller.fling(0, this.getTopAndBottomOffset(), 0, Math.round(velocityY), 0, 0, minOffset, maxOffset);
      if (this.mScroller.computeScrollOffset()) {
         this.mFlingRunnable = new HeaderBehavior.FlingRunnable(coordinatorLayout, layout);
         ViewCompat.postOnAnimation(layout, this.mFlingRunnable);
         return true;
      } else {
         this.onFlingFinished(coordinatorLayout, layout);
         return false;
      }
   }

   void onFlingFinished(CoordinatorLayout parent, View layout) {
   }

   boolean canDragView(View view) {
      return false;
   }

   int getMaxDragOffset(View view) {
      return -view.getHeight();
   }

   int getScrollRangeForDragFling(View view) {
      return view.getHeight();
   }

   private void ensureVelocityTracker() {
      if (this.mVelocityTracker == null) {
         this.mVelocityTracker = VelocityTracker.obtain();
      }

   }

   private class FlingRunnable implements Runnable {
      private final CoordinatorLayout mParent;
      private final View mLayout;

      FlingRunnable(CoordinatorLayout parent, View layout) {
         this.mParent = parent;
         this.mLayout = layout;
      }

      public void run() {
         if (this.mLayout != null && HeaderBehavior.this.mScroller != null) {
            if (HeaderBehavior.this.mScroller.computeScrollOffset()) {
               HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.this.mScroller.getCurrY());
               ViewCompat.postOnAnimation(this.mLayout, this);
            } else {
               HeaderBehavior.this.onFlingFinished(this.mParent, this.mLayout);
            }
         }

      }
   }
}
