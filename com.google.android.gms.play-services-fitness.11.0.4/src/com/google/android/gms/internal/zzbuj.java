package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

public final class zzbuj {
   public static String zza(Object var0, zzbug var1) {
      zzbuh var2;
      if (!(var2 = var1.zztF()).zzcY(var1.zzy(var0))) {
         return null;
      } else {
         Object var3 = var1.zzx(var0);

         for(int var4 = 0; var4 < var2.zzz(var3); ++var4) {
            String var5 = var2.zzg(var3, var4);
            if (!var1.zzd(var0, var4)) {
               if (!var2.zzf(var3, var4) && !zzbuk.zzaVr.contains(var5)) {
                  return String.valueOf(var5).concat(" not set");
               }
            } else {
               double var6;
               double var8;
               if ((var8 = (double)var2.zze(var3, var4)) == 1.0D) {
                  var6 = (double)var1.zzc(var0, var4);
               } else {
                  if (var8 != 2.0D) {
                     continue;
                  }

                  var6 = var1.zzb(var0, var4);
               }

               zzbum var10;
               if ((var10 = zzbuk.zztR().zzdg(var5)) != null && !var10.zzf(var6)) {
                  return "Field out of range";
               }

               String var16 = var2.zzA(var3);
               zzbum var11;
               if ((var11 = zzbuk.zztR().zzC(var16, var5)) != null) {
                  long var12;
                  if ((var12 = var1.zza(var0, TimeUnit.NANOSECONDS)) == 0L) {
                     if (var6 == 0.0D) {
                        return null;
                     }

                     return "DataPoint out of range";
                  }

                  double var14 = var6 / (double)var12;
                  if (!var11.zzf(var14)) {
                     return "DataPoint out of range";
                  }
               }
            }
         }

         return null;
      }
   }
}
