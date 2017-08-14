package com.google.android.gms.internal;

import android.os.Build.VERSION;

final class zzcuv {
   public static int version() {
      int var0;
      try {
         var0 = Integer.parseInt(VERSION.SDK);
      } catch (NumberFormatException var1) {
         String var10001 = String.valueOf(VERSION.SDK);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Invalid version number: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Invalid version number: ");
         }

         zzcvk.e(var10000);
         var0 = 0;
      }

      return var0;
   }
}
