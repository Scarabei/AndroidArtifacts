package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzczp extends zzcxq {
   public static final zzczp zzbJW = new zzczp();

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      dp var3 = var2[0];
      dp var4 = var2[1];
      zzbo.zzaf(!ed.zzm(var3));
      zzbo.zzaf(!ed.zzm(var4));
      String var5 = zzcxp.zzd(var4);
      double var10000;
      double var6;
      int var8;
      if (var3 instanceof dw) {
         if ("length".equals(var5)) {
            return new ds(true);
         }

         var10000 = var6 = zzcxp.zzb(var4);
         if (var10000 == Math.floor(var10000) && var5.equals(Integer.toString((int)var6)) && (var8 = (int)var6) >= 0 && var8 < ((List)((dw)var3).zzDs()).size()) {
            return new ds(true);
         }
      } else if (var3 instanceof eb) {
         if ("length".equals(var5)) {
            return new ds(true);
         }

         var10000 = var6 = zzcxp.zzb(var4);
         if (var10000 == Math.floor(var10000) && var5.equals(Integer.toString((int)var6)) && (var8 = (int)var6) >= 0 && var8 < ((String)((eb)var3).value()).length()) {
            return new ds(true);
         }

         return new ds(false);
      }

      return new ds(var3.zzfY(var5));
   }
}
