package android.support.v4.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper {
   private ViewParent mNestedScrollingParentTouch;
   private ViewParent mNestedScrollingParentNonTouch;
   private final View mView;
   private boolean mIsNestedScrollingEnabled;
   private int[] mTempNestedScrollConsumed;

   public NestedScrollingChildHelper(@NonNull View view) {
      this.mView = view;
   }

   public void setNestedScrollingEnabled(boolean enabled) {
      if (this.mIsNestedScrollingEnabled) {
         ViewCompat.stopNestedScroll(this.mView);
      }

      this.mIsNestedScrollingEnabled = enabled;
   }

   public boolean isNestedScrollingEnabled() {
      return this.mIsNestedScrollingEnabled;
   }

   public boolean hasNestedScrollingParent() {
      return this.hasNestedScrollingParent(0);
   }

   public boolean hasNestedScrollingParent(int type) {
      return this.getNestedScrollingParentForType(type) != null;
   }

   public boolean startNestedScroll(int axes) {
      return this.startNestedScroll(axes, 0);
   }

   public boolean startNestedScroll(int axes, int type) {
      if (this.hasNestedScrollingParent(type)) {
         return true;
      } else {
         if (this.isNestedScrollingEnabled()) {
            ViewParent p = this.mView.getParent();

            for(View child = this.mView; p != null; p = p.getParent()) {
               if (ViewParentCompat.onStartNestedScroll(p, child, this.mView, axes, type)) {
                  this.setNestedScrollingParentForType(type, p);
                  ViewParentCompat.onNestedScrollAccepted(p, child, this.mView, axes, type);
                  return true;
               }

               if (p instanceof View) {
                  child = (View)p;
               }
            }
         }

         return false;
      }
   }

   public void stopNestedScroll() {
      this.stopNestedScroll(0);
   }

   public void stopNestedScroll(int type) {
      ViewParent parent = this.getNestedScrollingParentForType(type);
      if (parent != null) {
         ViewParentCompat.onStopNestedScroll(parent, this.mView, type);
         this.setNestedScrollingParentForType(type, (ViewParent)null);
      }

   }

   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
      return this.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, 0);
   }

   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
      if (this.isNestedScrollingEnabled()) {
         ViewParent parent = this.getNestedScrollingParentForType(type);
         if (parent == null) {
            return false;
         }

         if (dxConsumed != 0 || dyConsumed != 0 || dxUnconsumed != 0 || dyUnconsumed != 0) {
            int startX = 0;
            int startY = 0;
            if (offsetInWindow != null) {
               this.mView.getLocationInWindow(offsetInWindow);
               startX = offsetInWindow[0];
               startY = offsetInWindow[1];
            }

            ViewParentCompat.onNestedScroll(parent, this.mView, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
            if (offsetInWindow != null) {
               this.mView.getLocationInWindow(offsetInWindow);
               offsetInWindow[0] -= startX;
               offsetInWindow[1] -= startY;
            }

            return true;
         }

         if (offsetInWindow != null) {
            offsetInWindow[0] = 0;
            offsetInWindow[1] = 0;
         }
      }

      return false;
   }

   public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
      return this.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, 0);
   }

   public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
      if (this.isNestedScrollingEnabled()) {
         ViewParent parent = this.getNestedScrollingParentForType(type);
         if (parent == null) {
            return false;
         }

         if (dx != 0 || dy != 0) {
            int startX = 0;
            int startY = 0;
            if (offsetInWindow != null) {
               this.mView.getLocationInWindow(offsetInWindow);
               startX = offsetInWindow[0];
               startY = offsetInWindow[1];
            }

            if (consumed == null) {
               if (this.mTempNestedScrollConsumed == null) {
                  this.mTempNestedScrollConsumed = new int[2];
               }

               consumed = this.mTempNestedScrollConsumed;
            }

            consumed[0] = 0;
            consumed[1] = 0;
            ViewParentCompat.onNestedPreScroll(parent, this.mView, dx, dy, consumed, type);
            if (offsetInWindow != null) {
               this.mView.getLocationInWindow(offsetInWindow);
               offsetInWindow[0] -= startX;
               offsetInWindow[1] -= startY;
            }

            return consumed[0] != 0 || consumed[1] != 0;
         }

         if (offsetInWindow != null) {
            offsetInWindow[0] = 0;
            offsetInWindow[1] = 0;
         }
      }

      return false;
   }

   public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
      if (this.isNestedScrollingEnabled()) {
         ViewParent parent = this.getNestedScrollingParentForType(0);
         if (parent != null) {
            return ViewParentCompat.onNestedFling(parent, this.mView, velocityX, velocityY, consumed);
         }
      }

      return false;
   }

   public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
      if (this.isNestedScrollingEnabled()) {
         ViewParent parent = this.getNestedScrollingParentForType(0);
         if (parent != null) {
            return ViewParentCompat.onNestedPreFling(parent, this.mView, velocityX, velocityY);
         }
      }

      return false;
   }

   public void onDetachedFromWindow() {
      ViewCompat.stopNestedScroll(this.mView);
   }

   public void onStopNestedScroll(@NonNull View child) {
      ViewCompat.stopNestedScroll(this.mView);
   }

   private ViewParent getNestedScrollingParentForType(int type) {
      switch(type) {
      case 0:
         return this.mNestedScrollingParentTouch;
      case 1:
         return this.mNestedScrollingParentNonTouch;
      default:
         return null;
      }
   }

   private void setNestedScrollingParentForType(int type, ViewParent p) {
      switch(type) {
      case 0:
         this.mNestedScrollingParentTouch = p;
         break;
      case 1:
         this.mNestedScrollingParentNonTouch = p;
      }

   }
}
