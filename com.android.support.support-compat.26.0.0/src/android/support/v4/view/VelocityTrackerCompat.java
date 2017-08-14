package android.support.v4.view;

import android.view.VelocityTracker;

/** @deprecated */
@Deprecated
public final class VelocityTrackerCompat {
   /** @deprecated */
   @Deprecated
   public static float getXVelocity(VelocityTracker tracker, int pointerId) {
      return tracker.getXVelocity(pointerId);
   }

   /** @deprecated */
   @Deprecated
   public static float getYVelocity(VelocityTracker tracker, int pointerId) {
      return tracker.getYVelocity(pointerId);
   }
}
