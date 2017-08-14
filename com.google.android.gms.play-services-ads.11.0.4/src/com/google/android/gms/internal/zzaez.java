package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;

@zzzn
public final class zzaez {
   public static String zzb(String var0, Context var1) {
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(var1) && !TextUtils.isEmpty(var0)) {
         String var2;
         if ((var2 = com.google.android.gms.ads.internal.zzbs.zzbY().zzw(var1)) == null) {
            return var0;
         } else {
            zzme var4 = zzmo.zzDz;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
               var4 = zzmo.zzDA;
               String var3 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4);
               if (var0.contains(var3)) {
                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  if (zzagz.zzaL(var0)) {
                     com.google.android.gms.ads.internal.zzbs.zzbY().zzf(var1, var2);
                     return var0.replaceAll(var3, var2);
                  }

                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  if (zzagz.zzaM(var0)) {
                     com.google.android.gms.ads.internal.zzbs.zzbY().zzg(var1, var2);
                     return var0.replaceAll(var3, var2);
                  }
               }
            } else if (!var0.contains("fbs_aeid")) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               if (zzagz.zzaL(var0)) {
                  com.google.android.gms.ads.internal.zzbs.zzbY().zzf(var1, var2);
                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  return zzagz.zzb(var0, "fbs_aeid", var2).toString();
               }

               com.google.android.gms.ads.internal.zzbs.zzbz();
               if (zzagz.zzaM(var0)) {
                  com.google.android.gms.ads.internal.zzbs.zzbY().zzg(var1, var2);
                  com.google.android.gms.ads.internal.zzbs.zzbz();
                  return zzagz.zzb(var0, "fbs_aeid", var2).toString();
               }
            }

            return var0;
         }
      } else {
         return var0;
      }
   }
}
