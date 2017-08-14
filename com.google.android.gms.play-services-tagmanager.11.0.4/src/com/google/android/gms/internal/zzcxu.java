package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcxu extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 2);
      zzbo.zzaf(var2[0] instanceof dw);
      zzbo.zzaf(var2[1] instanceof du);
      dw var3 = (dw)var2[0];
      du var4 = (du)var2[1];
      List var5;
      int var6 = (var5 = (List)var3.zzDs()).size();

      for(int var7 = 0; var7 < var6 && var7 < ((List)var3.zzDs()).size(); ++var7) {
         if (var3.zzbH(var7)) {
            ((zzcxo)var4.zzDp()).zzb(var1, (dp)var5.get(var7), new dt((double)var7), var3);
         }
      }

      return dv.zzbLu;
   }
}
