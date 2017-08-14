package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public abstract class AutoScrollHelper implements OnTouchListener {
   public static final float RELATIVE_UNSPECIFIED = 0.0F;
   public static final float NO_MAX = Float.MAX_VALUE;
   public static final float NO_MIN = 0.0F;
   public static final int EDGE_TYPE_INSIDE = 0;
   public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
   public static final int EDGE_TYPE_OUTSIDE = 2;
   private static final int HORIZONTAL = 0;
   private static final int VERTICAL = 1;
   final AutoScrollHelper.ClampedScroller mScroller = new AutoScrollHelper.ClampedScroller();
   private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
   final View mTarget;
   private Runnable mRunnable;
   private float[] mRelativeEdges = new float[]{0.0F, 0.0F};
   private float[] mMaximumEdges = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
   private int mEdgeType;
   private int mActivationDelay;
   private float[] mRelativeVelocity = new float[]{0.0F, 0.0F};
   private float[] mMinimumVelocity = new float[]{0.0F, 0.0F};
   private float[] mMaximumVelocity = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
   private boolean mAlreadyDelayed;
   boolean mNeedsReset;
   boolean mNeedsCancel;
   boolean mAnimating;
   private boolean mEnabled;
   private boolean mExclusive;
   private static final int DEFAULT_EDGE_TYPE = 1;
   private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
   private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
   private static final float DEFAULT_MAXIMUM_EDGE = Float.MAX_VALUE;
   private static final float DEFAULT_RELATIVE_EDGE = 0.2F;
   private static final float DEFAULT_RELATIVE_VELOCITY = 1.0F;
   private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
   private static final int DEFAULT_RAMP_UP_DURATION = 500;
   private static final int DEFAULT_RAMP_DOWN_DURATION = 500;

   public AutoScrollHelper(View target) {
      this.mTarget = target;
      DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
      int maxVelocity = (int)(1575.0F * metrics.density + 0.5F);
      int minVelocity = (int)(315.0F * metrics.density + 0.5F);
      this.setMaximumVelocity((float)maxVelocity, (float)maxVelocity);
      this.setMinimumVelocity((float)minVelocity, (float)minVelocity);
      this.setEdgeType(1);
      this.setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
      this.setRelativeEdges(0.2F, 0.2F);
      this.setRelativeVelocity(1.0F, 1.0F);
      this.setActivationDelay(DEFAULT_ACTIVATION_DELAY);
      this.setRampUpDuration(500);
      this.setRampDownDuration(500);
   }

   public AutoScrollHelper setEnabled(boolean enabled) {
      if (this.mEnabled && !enabled) {
         this.requestStop();
      }

      this.mEnabled = enabled;
      return this;
   }

   public boolean isEnabled() {
      return this.mEnabled;
   }

   public AutoScrollHelper setExclusive(boolean exclusive) {
      this.mExclusive = exclusive;
      return this;
   }

   public boolean isExclusive() {
      return this.mExclusive;
   }

   public AutoScrollHelper setMaximumVelocity(float horizontalMax, float verticalMax) {
      this.mMaximumVelocity[0] = horizontalMax / 1000.0F;
      this.mMaximumVelocity[1] = verticalMax / 1000.0F;
      return this;
   }

   public AutoScrollHelper setMinimumVelocity(float horizontalMin, float verticalMin) {
      this.mMinimumVelocity[0] = horizontalMin / 1000.0F;
      this.mMinimumVelocity[1] = verticalMin / 1000.0F;
      return this;
   }

   public AutoScrollHelper setRelativeVelocity(float horizontal, float vertical) {
      this.mRelativeVelocity[0] = horizontal / 1000.0F;
      this.mRelativeVelocity[1] = vertical / 1000.0F;
      return this;
   }

   public AutoScrollHelper setEdgeType(int type) {
      this.mEdgeType = type;
      return this;
   }

   public AutoScrollHelper setRelativeEdges(float horizontal, float vertical) {
      this.mRelativeEdges[0] = horizontal;
      this.mRelativeEdges[1] = vertical;
      return this;
   }

   public AutoScrollHelper setMaximumEdges(float horizontalMax, float verticalMax) {
      this.mMaximumEdges[0] = horizontalMax;
      this.mMaximumEdges[1] = verticalMax;
      return this;
   }

   public AutoScrollHelper setActivationDelay(int delayMillis) {
      this.mActivationDelay = delayMillis;
      return this;
   }

   public AutoScrollHelper setRampUpDuration(int durationMillis) {
      this.mScroller.setRampUpDuration(durationMillis);
      return this;
   }

   public AutoScrollHelper setRampDownDuration(int durationMillis) {
      this.mScroller.setRampDownDuration(durationMillis);
      return this;
   }

   public boolean onTouch(View v, MotionEvent event) {
      if (!this.mEnabled) {
         return false;
      } else {
         int action = event.getActionMasked();
         switch(action) {
         case 0:
            this.mNeedsCancel = true;
            this.mAlreadyDelayed = false;
         case 2:
            float xTargetVelocity = this.computeTargetVelocity(0, event.getX(), (float)v.getWidth(), (float)this.mTarget.getWidth());
            float yTargetVelocity = this.computeTargetVelocity(1, event.getY(), (float)v.getHeight(), (float)this.mTarget.getHeight());
            this.mScroller.setTargetVelocity(xTargetVelocity, yTargetVelocity);
            if (!this.mAnimating && this.shouldAnimate()) {
               this.startAnimating();
            }
            break;
         case 1:
         case 3:
            this.requestStop();
         }

         return this.mExclusive && this.mAnimating;
      }
   }

   boolean shouldAnimate() {
      AutoScrollHelper.ClampedScroller scroller = this.mScroller;
      int verticalDirection = scroller.getVerticalDirection();
      int horizontalDirection = scroller.getHorizontalDirection();
      return verticalDirection != 0 && this.canTargetScrollVertically(verticalDirection) || horizontalDirection != 0 && this.canTargetScrollHorizontally(horizontalDirection);
   }

   private void startAnimating() {
      if (this.mRunnable == null) {
         this.mRunnable = new AutoScrollHelper.ScrollAnimationRunnable();
      }

      this.mAnimating = true;
      this.mNeedsReset = true;
      if (!this.mAlreadyDelayed && this.mActivationDelay > 0) {
         ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, (long)this.mActivationDelay);
      } else {
         this.mRunnable.run();
      }

      this.mAlreadyDelayed = true;
   }

   private void requestStop() {
      if (this.mNeedsReset) {
         this.mAnimating = false;
      } else {
         this.mScroller.requestStop();
      }

   }

   private float computeTargetVelocity(int direction, float coordinate, float srcSize, float dstSize) {
      float relativeEdge = this.mRelativeEdges[direction];
      float maximumEdge = this.mMaximumEdges[direction];
      float value = this.getEdgeValue(relativeEdge, srcSize, maximumEdge, coordinate);
      if (value == 0.0F) {
         return 0.0F;
      } else {
         float relativeVelocity = this.mRelativeVelocity[direction];
         float minimumVelocity = this.mMinimumVelocity[direction];
         float maximumVelocity = this.mMaximumVelocity[direction];
         float targetVelocity = relativeVelocity * dstSize;
         return value > 0.0F ? constrain(value * targetVelocity, minimumVelocity, maximumVelocity) : -constrain(-value * targetVelocity, minimumVelocity, maximumVelocity);
      }
   }

   public abstract void scrollTargetBy(int var1, int var2);

   public abstract boolean canTargetScrollHorizontally(int var1);

   public abstract boolean canTargetScrollVertically(int var1);

   private float getEdgeValue(float relativeValue, float size, float maxValue, float current) {
      float edgeSize = constrain(relativeValue * size, 0.0F, maxValue);
      float valueLeading = this.constrainEdgeValue(current, edgeSize);
      float valueTrailing = this.constrainEdgeValue(size - current, edgeSize);
      float value = valueTrailing - valueLeading;
      float interpolated;
      if (value < 0.0F) {
         interpolated = -this.mEdgeInterpolator.getInterpolation(-value);
      } else {
         if (value <= 0.0F) {
            return 0.0F;
         }

         interpolated = this.mEdgeInterpolator.getInterpolation(value);
      }

      return constrain(interpolated, -1.0F, 1.0F);
   }

   private float constrainEdgeValue(float current, float leading) {
      if (leading == 0.0F) {
         return 0.0F;
      } else {
         switch(this.mEdgeType) {
         case 0:
         case 1:
            if (current < leading) {
               if (current >= 0.0F) {
                  return 1.0F - current / leading;
               }

               if (this.mAnimating && this.mEdgeType == 1) {
                  return 1.0F;
               }
            }
            break;
         case 2:
            if (current < 0.0F) {
               return current / -leading;
            }
         }

         return 0.0F;
      }
   }

   static int constrain(int value, int min, int max) {
      if (value > max) {
         return max;
      } else {
         return value < min ? min : value;
      }
   }

   static float constrain(float value, float min, float max) {
      if (value > max) {
         return max;
      } else {
         return value < min ? min : value;
      }
   }

   void cancelTargetTouch() {
      long eventTime = SystemClock.uptimeMillis();
      MotionEvent cancel = MotionEvent.obtain(eventTime, eventTime, 3, 0.0F, 0.0F, 0);
      this.mTarget.onTouchEvent(cancel);
      cancel.recycle();
   }

   private static class ClampedScroller {
      private int mRampUpDuration;
      private int mRampDownDuration;
      private float mTargetVelocityX;
      private float mTargetVelocityY;
      private long mStartTime = Long.MIN_VALUE;
      private long mDeltaTime = 0L;
      private int mDeltaX = 0;
      private int mDeltaY = 0;
      private long mStopTime = -1L;
      private float mStopValue;
      private int mEffectiveRampDown;

      public void setRampUpDuration(int durationMillis) {
         this.mRampUpDuration = durationMillis;
      }

      public void setRampDownDuration(int durationMillis) {
         this.mRampDownDuration = durationMillis;
      }

      public void start() {
         this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
         this.mStopTime = -1L;
         this.mDeltaTime = this.mStartTime;
         this.mStopValue = 0.5F;
         this.mDeltaX = 0;
         this.mDeltaY = 0;
      }

      public void requestStop() {
         long currentTime = AnimationUtils.currentAnimationTimeMillis();
         this.mEffectiveRampDown = AutoScrollHelper.constrain((int)(currentTime - this.mStartTime), 0, this.mRampDownDuration);
         this.mStopValue = this.getValueAt(currentTime);
         this.mStopTime = currentTime;
      }

      public boolean isFinished() {
         return this.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + (long)this.mEffectiveRampDown;
      }

      private float getValueAt(long currentTime) {
         if (currentTime < this.mStartTime) {
            return 0.0F;
         } else {
            long elapsedSinceStart;
            if (this.mStopTime >= 0L && currentTime >= this.mStopTime) {
               elapsedSinceStart = currentTime - this.mStopTime;
               return 1.0F - this.mStopValue + this.mStopValue * AutoScrollHelper.constrain((float)elapsedSinceStart / (float)this.mEffectiveRampDown, 0.0F, 1.0F);
            } else {
               elapsedSinceStart = currentTime - this.mStartTime;
               return 0.5F * AutoScrollHelper.constrain((float)elapsedSinceStart / (float)this.mRampUpDuration, 0.0F, 1.0F);
            }
         }
      }

      private float interpolateValue(float value) {
         return -4.0F * value * value + 4.0F * value;
      }

      public void computeScrollDelta() {
         if (this.mDeltaTime == 0L) {
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
         } else {
            long currentTime = AnimationUtils.currentAnimationTimeMillis();
            float value = this.getValueAt(currentTime);
            float scale = this.interpolateValue(value);
            long elapsedSinceDelta = currentTime - this.mDeltaTime;
            this.mDeltaTime = currentTime;
            this.mDeltaX = (int)((float)elapsedSinceDelta * scale * this.mTargetVelocityX);
            this.mDeltaY = (int)((float)elapsedSinceDelta * scale * this.mTargetVelocityY);
         }
      }

      public void setTargetVelocity(float x, float y) {
         this.mTargetVelocityX = x;
         this.mTargetVelocityY = y;
      }

      public int getHorizontalDirection() {
         return (int)(this.mTargetVelocityX / Math.abs(this.mTargetVelocityX));
      }

      public int getVerticalDirection() {
         return (int)(this.mTargetVelocityY / Math.abs(this.mTargetVelocityY));
      }

      public int getDeltaX() {
         return this.mDeltaX;
      }

      public int getDeltaY() {
         return this.mDeltaY;
      }
   }

   private class ScrollAnimationRunnable implements Runnable {
      public void run() {
         if (AutoScrollHelper.this.mAnimating) {
            if (AutoScrollHelper.this.mNeedsReset) {
               AutoScrollHelper.this.mNeedsReset = false;
               AutoScrollHelper.this.mScroller.start();
            }

            AutoScrollHelper.ClampedScroller scroller = AutoScrollHelper.this.mScroller;
            if (!scroller.isFinished() && AutoScrollHelper.this.shouldAnimate()) {
               if (AutoScrollHelper.this.mNeedsCancel) {
                  AutoScrollHelper.this.mNeedsCancel = false;
                  AutoScrollHelper.this.cancelTargetTouch();
               }

               scroller.computeScrollDelta();
               int deltaX = scroller.getDeltaX();
               int deltaY = scroller.getDeltaY();
               AutoScrollHelper.this.scrollTargetBy(deltaX, deltaY);
               ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
            } else {
               AutoScrollHelper.this.mAnimating = false;
            }
         }
      }
   }
}
