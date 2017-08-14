package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.internal.zzbha;

public final class zzw {
   public static boolean zzf(Context var0, int var1) {
      if (!zzb(var0, var1, "com.google.android.gms")) {
         return false;
      } else {
         PackageManager var3 = var0.getPackageManager();

         PackageInfo var2;
         try {
            var2 = var3.getPackageInfo("com.google.android.gms", 64);
         } catch (NameNotFoundException var4) {
            if (Log.isLoggable("UidVerifier", 3)) {
               Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
            }

            return false;
         }

         return com.google.android.gms.common.zzp.zzax(var0).zza(var0.getPackageManager(), var2);
      }
   }

   @TargetApi(19)
   public static boolean zzb(Context var0, int var1, String var2) {
      return zzbha.zzaP(var0).zzf(var1, var2);
   }
}
