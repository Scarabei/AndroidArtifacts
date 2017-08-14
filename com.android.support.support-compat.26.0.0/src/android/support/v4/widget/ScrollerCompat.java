package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/** @deprecated */
@Deprecated
public final class ScrollerCompat {
   OverScroller mScroller;

   /** @deprecated */
   @Deprecated
   public static ScrollerCompat create(Context context) {
      return create(context, (Interpolator)null);
   }

   /** @deprecated */
   @Deprecated
   public static ScrollerCompat create(Context context, Interpolator interpolator) {
      return new ScrollerCompat(context, interpolator);
   }

   ScrollerCompat(Context context, Interpolator interpolator) {
      this.mScroller = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
   }

   /** @deprecated */
   @Deprecated
   public boolean isFinished() {
      return this.mScroller.isFinished();
   }

   /** @deprecated */
   @Deprecated
   public int getCurrX() {
      return this.mScroller.getCurrX();
   }

   /** @deprecated */
   @Deprecated
   public int getCurrY() {
      return this.mScroller.getCurrY();
   }

   /** @deprecated */
   @Deprecated
   public int getFinalX() {
      return this.mScroller.getFinalX();
   }

   /** @deprecated */
   @Deprecated
   public int getFinalY() {
      return this.mScroller.getFinalY();
   }

   /** @deprecated */
   @Deprecated
   public float getCurrVelocity() {
      return this.mScroller.getCurrVelocity();
   }

   /** @deprecated */
   @Deprecated
   public boolean computeScrollOffset() {
      return this.mScroller.computeScrollOffset();
   }

   /** @deprecated */
   @Deprecated
   public void startScroll(int startX, int startY, int dx, int dy) {
      this.mScroller.startScroll(startX, startY, dx, dy);
   }

   /** @deprecated */
   @Deprecated
   public void startScroll(int startX, int startY, int dx, int dy, int duration) {
      this.mScroller.startScroll(startX, startY, dx, dy, duration);
   }

   /** @deprecated */
   @Deprecated
   public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
      this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
   }

   /** @deprecated */
   @Deprecated
   public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
      this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, overX, overY);
   }

   /** @deprecated */
   @Deprecated
   public boolean springBack(int startX, int startY, int minX, int maxX, int minY, int maxY) {
      return this.mScroller.springBack(startX, startY, minX, maxX, minY, maxY);
   }

   /** @deprecated */
   @Deprecated
   public void abortAnimation() {
      this.mScroller.abortAnimation();
   }

   /** @deprecated */
   @Deprecated
   public void notifyHorizontalEdgeReached(int startX, int finalX, int overX) {
      this.mScroller.notifyHorizontalEdgeReached(startX, finalX, overX);
   }

   /** @deprecated */
   @Deprecated
   public void notifyVerticalEdgeReached(int startY, int finalY, int overY) {
      this.mScroller.notifyVerticalEdgeReached(startY, finalY, overY);
   }

   /** @deprecated */
   @Deprecated
   public boolean isOverScrolled() {
      return this.mScroller.isOverScrolled();
   }
}
