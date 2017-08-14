package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzcyq extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      Object var3 = var2[0];
      Object var4 = var2[1];
      if (var3 instanceof dz || var3 instanceof dw || var3 instanceof du) {
         var3 = new eb(zzcxp.zzd((dp)var3));
      }

      if (var4 instanceof dz || var4 instanceof dw || var4 instanceof du) {
         var4 = new eb(zzcxp.zzd((dp)var4));
      }

      boolean var5 = var3 instanceof eb && var4 instanceof eb || !Double.isNaN(zzcxp.zzb((dp)var3)) && !Double.isNaN(zzcxp.zzb((dp)var4)) ? !zzcxp.zzb((dp)var3, (dp)var4) : false;
      return new ds(var5);
   }
}
