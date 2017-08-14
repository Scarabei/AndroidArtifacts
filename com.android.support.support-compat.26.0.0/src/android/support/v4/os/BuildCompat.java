package android.support.v4.os;

import android.os.Build.VERSION;

public class BuildCompat {
   /** @deprecated */
   @Deprecated
   public static boolean isAtLeastN() {
      return VERSION.SDK_INT >= 24;
   }

   /** @deprecated */
   @Deprecated
   public static boolean isAtLeastNMR1() {
      return VERSION.SDK_INT >= 25;
   }

   /** @deprecated */
   public static boolean isAtLeastO() {
      return VERSION.SDK_INT >= 26;
   }

   public static boolean isAtLeastOMR1() {
      return VERSION.CODENAME.startsWith("OMR") || isAtLeastP();
   }

   public static boolean isAtLeastP() {
      return VERSION.CODENAME.equals("P");
   }
}
