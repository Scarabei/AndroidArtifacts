package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public final class zzzu {
   private static boolean zzc(Context var0, boolean var1) {
      if (!var1) {
         return true;
      } else {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         int var2;
         if ((var2 = zzagz.zzP(var0)) == 0) {
            return false;
         } else {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            return var2 <= zzagz.zzQ(var0);
         }
      }
   }

   // $FF: synthetic method
   static boolean zzd(Context var0, boolean var1) {
      return zzc(var0, var1);
   }
}
