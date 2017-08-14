package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcxv extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0 && var2.length <= 3);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      Object var4 = var2.length < 2 ? dv.zzbLu : var2[1];
      Object var5 = var2.length < 3 ? dv.zzbLu : var2[2];
      List var6;
      int var7 = (var6 = (List)var3.zzDs()).size();
      int var8 = 0;
      if (var5 != dv.zzbLu && (var8 = (int)zzcxp.zzc((dp)var5)) < 0) {
         var8 = Math.max(var7 - Math.abs(var8), 0);
      }

      int var9 = -1;

      for(int var10 = var8; var10 < var7; ++var10) {
         if (var3.zzbH(var10) && zzcxp.zzd((dp)var4, (dp)var6.get(var10))) {
            var9 = var10;
            break;
         }
      }

      return new dt((double)var9);
   }
}
