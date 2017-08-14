package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcxx extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0 && var2.length <= 3);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      Object var4 = var2.length < 2 ? dv.zzbLu : var2[1];
      List var5;
      int var6;
      int var7 = (var6 = (var5 = (List)var3.zzDs()).size()) - 1;
      if (var2.length == 3) {
         if ((var7 = (int)zzcxp.zzc(var2[2])) < 0) {
            var7 = var6 - Math.abs(var7);
         } else {
            var7 = Math.min(var7, var6 - 1);
         }
      }

      int var8 = -1;

      for(int var9 = var7; var9 >= 0; --var9) {
         if (var3.zzbH(var9) && zzcxp.zzd((dp)var4, (dp)var5.get(var9))) {
            var8 = var9;
            break;
         }
      }

      return new dt((double)var8);
   }
}
