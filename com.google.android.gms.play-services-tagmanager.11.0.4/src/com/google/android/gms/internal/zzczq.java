package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzczq extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2 || var2.length == 3);
      zzbo.zzaf(var2[1] instanceof dw);
      if (var2.length == 3) {
         zzbo.zzaf(var2[2] instanceof dw);
      }

      Object var3 = new ArrayList();
      if (zzcxp.zza(var2[0])) {
         var3 = (List)((dw)var2[1]).zzDs();
      } else if (var2.length > 2) {
         var3 = (List)((dw)var2[2]).zzDs();
      }

      dv var4;
      return (var4 = ed.zza(var1, (List)var3)) instanceof dv && ed.zzm(var4) ? var4 : dv.zzbLu;
   }
}
