package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class b extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      double var3 = zzcxp.zzb(var2[0]);
      double var5 = zzcxp.zzb(var2[1]);
      if (!Double.isNaN(var3) && !Double.isNaN(var5)) {
         if (!Double.isInfinite(var3) && var5 != 0.0D) {
            if (!Double.isInfinite(var3) && Double.isInfinite(var5)) {
               return new dt(var3);
            } else {
               return var3 == 0.0D && var5 != 0.0D && !Double.isInfinite(var5) ? new dt(var3) : new dt(var3 % var5);
            }
         } else {
            return new dt(Double.NaN);
         }
      } else {
         return new dt(Double.NaN);
      }
   }
}
