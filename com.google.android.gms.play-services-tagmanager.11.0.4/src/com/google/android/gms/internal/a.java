package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class a extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      double var3 = zzcxp.zzb(var2[0]);
      double var5 = zzcxp.zzb(var2[1]);
      if (!Double.isNaN(var3) && !Double.isNaN(var5)) {
         if (Double.isInfinite(var3) && Double.isInfinite(var5)) {
            return new dt(Double.NaN);
         } else {
            boolean var7 = (double)Double.compare(var3, 0.0D) < 0.0D ^ (double)Double.compare(var5, 0.0D) < 0.0D;
            double var8;
            if (Double.isInfinite(var3) && !Double.isInfinite(var5)) {
               var8 = var7 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
               return new dt(var8);
            } else if (!Double.isInfinite(var3) && Double.isInfinite(var5)) {
               var8 = var7 ? -0.0D : 0.0D;
               return new dt(var8);
            } else if (var3 == 0.0D) {
               if (var5 == 0.0D) {
                  return new dt(Double.NaN);
               } else {
                  var8 = var7 ? -0.0D : 0.0D;
                  return new dt(var8);
               }
            } else if (!Double.isInfinite(var3) && var3 != 0.0D && var5 == 0.0D) {
               var8 = var7 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
               return new dt(var8);
            } else {
               return new dt(var3 / var5);
            }
         }
      } else {
         return new dt(Double.NaN);
      }
   }
}
