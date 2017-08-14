package com.google.android.gms.tagmanager;

import java.util.Map;

final class zzbq {
   public static void zza(DataLayer var0, com.google.android.gms.internal.zzbp var1) {
      if (var1.zzlz == null) {
         zzdj.zzaT("supplemental missing experimentSupplemental");
      } else {
         com.google.android.gms.internal.zzbl var3 = var1.zzlz;
         DataLayer var2 = var0;
         com.google.android.gms.internal.zzbr[] var4 = var3.zzkK;
         int var5 = var3.zzkK.length;

         int var6;
         for(var6 = 0; var6 < var5; ++var6) {
            com.google.android.gms.internal.zzbr var7 = var4[var6];
            var2.zzfc(zzgk.zzb(var7));
         }

         var3 = var1.zzlz;
         var2 = var0;
         var4 = var3.zzkJ;
         var5 = var3.zzkJ.length;

         for(var6 = 0; var6 < var5; ++var6) {
            Map var10000;
            Object var9;
            if (!((var9 = zzgk.zzg(var4[var6])) instanceof Map)) {
               String var10 = String.valueOf(var9);
               zzdj.zzaT((new StringBuilder(36 + String.valueOf(var10).length())).append("value: ").append(var10).append(" is not a map value, ignored.").toString());
               var10000 = null;
            } else {
               var10000 = (Map)var9;
            }

            Map var8 = var10000;
            if (var10000 != null) {
               var2.push(var8);
            }
         }

         zza(var0, var1.zzlz);
      }
   }

   private static void zza(DataLayer var0, com.google.android.gms.internal.zzbl var1) {
      com.google.android.gms.internal.zzbk[] var2 = var1.zzkL;
      int var3 = var1.zzkL.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         com.google.android.gms.internal.zzbk var5;
         if ((var5 = var2[var4]).key == null) {
            zzdj.zzaT("GaExperimentRandom: No key");
         } else {
            Object var6;
            Long var7;
            if (!((var6 = var0.get(var5.key)) instanceof Number)) {
               var7 = null;
            } else {
               var7 = ((Number)var6).longValue();
            }

            long var8 = var5.zzkF;
            long var10 = var5.zzkG;
            if (!var5.zzkH || var7 == null || var7.longValue() < var8 || var7.longValue() > var10) {
               if (var8 > var10) {
                  zzdj.zzaT("GaExperimentRandom: random range invalid");
                  continue;
               }

               var6 = Math.round(Math.random() * (double)(var10 - var8) + (double)var8);
            }

            var0.zzfc(var5.key);
            Map var12 = DataLayer.zzn(var5.key, var6);
            if (var5.zzkI > 0L) {
               if (!var12.containsKey("gtm")) {
                  var12.put("gtm", DataLayer.mapOf("lifetime", var5.zzkI));
               } else {
                  Object var13;
                  if ((var13 = var12.get("gtm")) instanceof Map) {
                     ((Map)var13).put("lifetime", var5.zzkI);
                  } else {
                     zzdj.zzaT("GaExperimentRandom: gtm not a map");
                  }
               }
            }

            var0.push(var12);
         }
      }

   }
}
