package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcye extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof dw);
      Object var3 = dv.zzbLu;
      List var4;
      if (!(var4 = (List)((dw)var2[0]).zzDs()).isEmpty()) {
         var3 = (dp)var4.remove(0);
      }

      return (dp)var3;
   }
}
