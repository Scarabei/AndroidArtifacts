package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public abstract class bs extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);

      double var3;
      double var5;
      try {
         var3 = zzcxp.zzb(var2[0]);
         var5 = zzcxp.zzb(var2[1]);
      } catch (IllegalArgumentException var7) {
         return new ds(false);
      }

      return !Double.isNaN(var3) && !Double.isNaN(var5) ? new ds(this.zze(var3, var5)) : new ds(false);
   }

   protected abstract boolean zze(double var1, double var3);
}
