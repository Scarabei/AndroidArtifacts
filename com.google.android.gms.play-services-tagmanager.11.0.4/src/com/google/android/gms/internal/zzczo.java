package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzczo extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      dp var3 = var2[0];
      dp var4 = var2[1];
      zzbo.zzaf(var3 instanceof eb || !ed.zzl(var3));
      zzbo.zzaf(!ed.zzm(var3));
      zzbo.zzaf(!ed.zzm(var4));
      String var5 = zzcxp.zzd(var4);
      double var10000;
      double var7;
      if (var3 instanceof dw) {
         dw var6 = (dw)var3;
         if ("length".equals(var5)) {
            return new dt((double)((List)var6.zzDs()).size());
         }

         var10000 = var7 = zzcxp.zzb(var4);
         dp var9;
         if (var10000 == Math.floor(var10000) && var5.equals(Integer.toString((int)var7)) && (var9 = var6.zzbG((int)var7)) != dv.zzbLu) {
            return var9;
         }
      } else if (var3 instanceof eb) {
         eb var10 = (eb)var3;
         if ("length".equals(var5)) {
            return new dt((double)((String)var10.value()).length());
         }

         var10000 = var7 = zzcxp.zzb(var4);
         if (var10000 == Math.floor(var10000) && var5.equals(Integer.toString((int)var7))) {
            return var10.zzbI((int)var7);
         }

         return dv.zzbLu;
      }

      return var3.zzfZ(var5);
   }
}
