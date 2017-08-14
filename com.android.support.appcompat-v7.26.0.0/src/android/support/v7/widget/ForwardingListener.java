package android.support.v7.widget;

import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class ForwardingListener implements OnTouchListener, OnAttachStateChangeListener {
   private final float mScaledTouchSlop;
   private final int mTapTimeout;
   private final int mLongPressTimeout;
   final View mSrc;
   private Runnable mDisallowIntercept;
   private Runnable mTriggerLongPress;
   private boolean mForwarding;
   private int mActivePointerId;
   private final int[] mTmpLocation = new int[2];

   public ForwardingListener(View src) {
      this.mSrc = src;
      src.setLongClickable(true);
      src.addOnAttachStateChangeListener(this);
      this.mScaledTouchSlop = (float)ViewConfiguration.get(src.getContext()).getScaledTouchSlop();
      this.mTapTimeout = ViewConfiguration.getTapTimeout();
      this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
   }

   public abstract ShowableListMenu getPopup();

   public boolean onTouch(View v, MotionEvent event) {
      boolean wasForwarding = this.mForwarding;
      boolean forwarding;
      if (wasForwarding) {
         forwarding = this.onTouchForwarded(event) || !this.onForwardingStopped();
      } else {
         forwarding = this.onTouchObserved(event) && this.onForwardingStarted();
         if (forwarding) {
            long now = SystemClock.uptimeMillis();
            MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
            this.mSrc.onTouchEvent(e);
            e.recycle();
         }
      }

      this.mForwarding = forwarding;
      return forwarding || wasForwarding;
   }

   public void onViewAttachedToWindow(View v) {
   }

   public void onViewDetachedFromWindow(View v) {
      this.mForwarding = false;
      this.mActivePointerId = -1;
      if (this.mDisallowIntercept != null) {
         this.mSrc.removeCallbacks(this.mDisallowIntercept);
      }

   }

   protected boolean onForwardingStarted() {
      ShowableListMenu popup = this.getPopup();
      if (popup != null && !popup.isShowing()) {
         popup.show();
      }

      return true;
   }

   protected boolean onForwardingStopped() {
      ShowableListMenu popup = this.getPopup();
      if (popup != null && popup.isShowing()) {
         popup.dismiss();
      }

      return true;
   }

   private boolean onTouchObserved(MotionEvent srcEvent) {
      View src = this.mSrc;
      if (!src.isEnabled()) {
         return false;
      } else {
         int actionMasked = srcEvent.getActionMasked();
         switch(actionMasked) {
         case 0:
            this.mActivePointerId = srcEvent.getPointerId(0);
            if (this.mDisallowIntercept == null) {
               this.mDisallowIntercept = new ForwardingListener.DisallowIntercept();
            }

            src.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
            if (this.mTriggerLongPress == null) {
               this.mTriggerLongPress = new ForwardingListener.TriggerLongPress();
            }

            src.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
            break;
         case 1:
         case 3:
            this.clearCallbacks();
            break;
         case 2:
            int activePointerIndex = srcEvent.findPointerIndex(this.mActivePointerId);
            if (activePointerIndex >= 0) {
               float x = srcEvent.getX(activePointerIndex);
               float y = srcEvent.getY(activePointerIndex);
               if (!pointInView(src, x, y, this.mScaledTouchSlop)) {
                  this.clearCallbacks();
                  src.getParent().requestDisallowInterceptTouchEvent(true);
                  return true;
               }
            }
         }

         return false;
      }
   }

   private void clearCallbacks() {
      if (this.mTriggerLongPress != null) {
         this.mSrc.removeCallbacks(this.mTriggerLongPress);
      }

      if (this.mDisallowIntercept != null) {
         this.mSrc.removeCallbacks(this.mDisallowIntercept);
      }

   }

   void onLongPress() {
      this.clearCallbacks();
      View src = this.mSrc;
      if (src.isEnabled() && !src.isLongClickable()) {
         if (this.onForwardingStarted()) {
            src.getParent().requestDisallowInterceptTouchEvent(true);
            long now = SystemClock.uptimeMillis();
            MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0F, 0.0F, 0);
            src.onTouchEvent(e);
            e.recycle();
            this.mForwarding = true;
         }
      }
   }

   private boolean onTouchForwarded(MotionEvent srcEvent) {
      View src = this.mSrc;
      ShowableListMenu popup = this.getPopup();
      if (popup != null && popup.isShowing()) {
         DropDownListView dst = (DropDownListView)popup.getListView();
         if (dst != null && dst.isShown()) {
            MotionEvent dstEvent = MotionEvent.obtainNoHistory(srcEvent);
            this.toGlobalMotionEvent(src, dstEvent);
            this.toLocalMotionEvent(dst, dstEvent);
            boolean handled = dst.onForwardedEvent(dstEvent, this.mActivePointerId);
            dstEvent.recycle();
            int action = srcEvent.getActionMasked();
            boolean keepForwarding = action != 1 && action != 3;
            return handled && keepForwarding;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean pointInView(View view, float localX, float localY, float slop) {
      return localX >= -slop && localY >= -slop && localX < (float)(view.getRight() - view.getLeft()) + slop && localY < (float)(view.getBottom() - view.getTop()) + slop;
   }

   private boolean toLocalMotionEvent(View view, MotionEvent event) {
      int[] loc = this.mTmpLocation;
      view.getLocationOnScreen(loc);
      event.offsetLocation((float)(-loc[0]), (float)(-loc[1]));
      return true;
   }

   private boolean toGlobalMotionEvent(View view, MotionEvent event) {
      int[] loc = this.mTmpLocation;
      view.getLocationOnScreen(loc);
      event.offsetLocation((float)loc[0], (float)loc[1]);
      return true;
   }

   private class TriggerLongPress implements Runnable {
      public void run() {
         ForwardingListener.this.onLongPress();
      }
   }

   private class DisallowIntercept implements Runnable {
      public void run() {
         ViewParent parent = ForwardingListener.this.mSrc.getParent();
         if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
         }

      }
   }
}
