package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcyg extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 2);
      zzbo.zzaf(var2[0] instanceof dw);
      zzbo.zzaf(var2[1] instanceof du);
      dw var3 = (dw)var2[0];
      du var4 = (du)var2[1];
      List var5;
      int var6 = (var5 = (List)var3.zzDs()).size();
      boolean var7 = false;

      for(int var8 = 0; !var7 && var8 < var6 && var8 < ((List)var3.zzDs()).size(); ++var8) {
         if (var3.zzbH(var8)) {
            dp var9 = ((zzcxo)var4.zzDp()).zzb(var1, (dp)var5.get(var8), new dt((double)var8), var3);
            var7 |= zzcxp.zza(var9);
         }
      }

      return new ds(var7);
   }
}
