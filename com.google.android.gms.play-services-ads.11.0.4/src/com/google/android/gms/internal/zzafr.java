package com.google.android.gms.internal;

import android.util.Log;

@zzzn
public final class zzafr extends zzajc {
   public static void v(String var0) {
      if (zzhM()) {
         Log.v("Ads", var0);
      }

   }

   public static boolean zzhM() {
      if (zzz(2)) {
         zzme var0 = zzmo.zzEp;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).booleanValue()) {
            return true;
         }
      }

      return false;
   }
}
