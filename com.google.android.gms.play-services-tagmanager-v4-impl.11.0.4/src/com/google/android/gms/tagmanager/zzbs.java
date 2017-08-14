package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import java.io.File;

final class zzbs {
   private static int version() {
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

         zzdj.e(var10000);
         var0 = 0;
      }

      return var0;
   }

   @TargetApi(9)
   static boolean zzfk(String var0) {
      if (version() < 9) {
         return false;
      } else {
         File var1;
         (var1 = new File(var0)).setReadable(false, false);
         var1.setWritable(false, false);
         var1.setReadable(true, true);
         var1.setWritable(true, true);
         return true;
      }
   }
}
