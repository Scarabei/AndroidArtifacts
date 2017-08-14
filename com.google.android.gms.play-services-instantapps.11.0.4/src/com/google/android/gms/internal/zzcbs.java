package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.zzp;

final class zzcbs {
   static boolean zzbg(Context var0) {
      PackageManager var1 = var0.getPackageManager();

      PackageInfo var2;
      try {
         var2 = var1.getPackageInfo("com.google.android.gms", 64);
      } catch (NameNotFoundException var3) {
         return false;
      }

      if (!zzp.zzax(var0).zza(var0.getPackageManager(), var2)) {
         String var10002 = String.valueOf(var2.packageName);
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Incorrect signature for package ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Incorrect signature for package ");
         }

         Log.e("InstantAppsApi", var10001);
         return false;
      } else {
         return true;
      }
   }
}
