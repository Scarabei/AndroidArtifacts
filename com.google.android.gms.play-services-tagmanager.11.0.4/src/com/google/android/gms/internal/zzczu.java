package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczu extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 3);
      dp var3 = var2[0];
      dp var4 = var2[1];
      dp var5 = var2[2];
      zzbo.zzaf(var3 != dv.zzbLt);
      zzbo.zzaf(var3 != dv.zzbLu);
      zzbo.zzaf(!ed.zzm(var3));
      zzbo.zzaf(!ed.zzm(var4));
      zzbo.zzaf(!ed.zzm(var5));
      if (ed.zzl(var3)) {
         return var5;
      } else {
         String var6 = zzcxp.zzd(var4);
         if (var3 instanceof dz) {
            dz var10;
            if (!(var10 = (dz)var3).isImmutable()) {
               var10.zzc(var6, var5);
            }

            return var5;
         } else {
            if (var3 instanceof dw) {
               dw var7 = (dw)var3;
               double var8;
               if ("length".equals(var6)) {
                  zzbo.zzaf(!Double.isInfinite(var8 = zzcxp.zzb(var5)) && var8 == Math.floor(var8));
                  var7.setSize((int)var8);
                  return var5;
               }

               if (!Double.isInfinite(var8 = zzcxp.zzb(var4)) && var8 >= 0.0D && var6.equals(Integer.toString((int)var8))) {
                  var7.zza((int)var8, var5);
                  return var5;
               }
            }

            var3.zzc(var6, var5);
            return var5;
         }
      }
   }
}
