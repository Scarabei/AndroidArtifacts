package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat {
   /** @deprecated */
   @Deprecated
   public static void setQuickScaleEnabled(Object scaleGestureDetector, boolean enabled) {
      setQuickScaleEnabled((ScaleGestureDetector)scaleGestureDetector, enabled);
   }

   public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector, boolean enabled) {
      if (VERSION.SDK_INT >= 19) {
         scaleGestureDetector.setQuickScaleEnabled(enabled);
      }

   }

   /** @deprecated */
   @Deprecated
   public static boolean isQuickScaleEnabled(Object scaleGestureDetector) {
      return isQuickScaleEnabled((ScaleGestureDetector)scaleGestureDetector);
   }

   public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector) {
      return VERSION.SDK_INT >= 19 ? scaleGestureDetector.isQuickScaleEnabled() : false;
   }
}
