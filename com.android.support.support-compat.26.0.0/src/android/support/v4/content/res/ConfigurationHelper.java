package android.support.v4.content.res;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class ConfigurationHelper {
   /** @deprecated */
   @Deprecated
   public static int getScreenHeightDp(@NonNull Resources resources) {
      return resources.getConfiguration().screenHeightDp;
   }

   /** @deprecated */
   @Deprecated
   public static int getScreenWidthDp(@NonNull Resources resources) {
      return resources.getConfiguration().screenWidthDp;
   }

   /** @deprecated */
   @Deprecated
   public static int getSmallestScreenWidthDp(@NonNull Resources resources) {
      return resources.getConfiguration().smallestScreenWidthDp;
   }

   public static int getDensityDpi(@NonNull Resources resources) {
      return VERSION.SDK_INT >= 17 ? resources.getConfiguration().densityDpi : resources.getDisplayMetrics().densityDpi;
   }
}
