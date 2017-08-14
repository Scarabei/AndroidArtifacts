package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcxz extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof dw);
      List var3 = (List)((dw)var2[0]).zzDs();
      Object var4 = dv.zzbLu;
      if (!var3.isEmpty()) {
         var4 = (dp)var3.remove(var3.size() - 1);
      }

      return (dp)var4;
   }
}
