package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources.NotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzj;

public final class zzbui {
   private static int zzaVq = -1;

   public static int zzaU(Context var0) {
      if (zzaVq == -1) {
         if (zzj.zzaG(var0)) {
            zzaVq = 3;
         } else {
            PackageManager var2;
            if ((var2 = var0.getPackageManager()).hasSystemFeature("com.google.android.tv") || var2.hasSystemFeature("android.hardware.type.television") || var2.hasSystemFeature("android.software.leanback")) {
               zzaVq = 0;
            } else if (zzj.zza(var0.getResources()) && !zzaV(var0)) {
               zzaVq = 2;
            } else if (!TextUtils.isEmpty(Build.PRODUCT) && Build.PRODUCT.startsWith("glass_")) {
               zzaVq = 6;
            } else {
               zzaVq = 1;
            }
         }
      }

      return zzaVq;
   }

   private static boolean zzaV(Context var0) {
      try {
         return ((TelephonyManager)var0.getSystemService("phone")).getPhoneType() != 0;
      } catch (NotFoundException var2) {
         Log.wtf("Fitness", "Unable to determine type of device, assuming phone.", var2);
         return true;
      }
   }
}
