package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class c extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      double var3 = zzcxp.zzb(var2[0]);
      double var5 = zzcxp.zzb(var2[1]);
      if (!Double.isNaN(var3) && !Double.isNaN(var5)) {
         if (Double.isInfinite(var3) && var5 == 0.0D || var3 == 0.0D && Double.isInfinite(var5)) {
            return new dt(Double.NaN);
         } else if (!Double.isInfinite(var3) && !Double.isInfinite(var5)) {
            return new dt(var3 * var5);
         } else {
            double var7 = (double)Double.compare(var3, 0.0D) < 0.0D ^ (double)Double.compare(var5, 0.0D) < 0.0D ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            return new dt(var7);
         }
      } else {
         return new dt(Double.NaN);
      }
   }
}
