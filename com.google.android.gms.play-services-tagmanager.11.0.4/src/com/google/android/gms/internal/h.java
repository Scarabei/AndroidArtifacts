package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class h extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2 || var2.length == 3);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      String var4 = zzcxp.zzd(var2[1]);
      double var5 = Double.POSITIVE_INFINITY;
      if (var2.length == 3 && !Double.isNaN(zzcxp.zzb(var2[2]))) {
         var5 = zzcxp.zzc(var2[2]);
      }

      var5 = Math.min(Math.max(var5, 0.0D), (double)var3.length());
      return new dt((double)var3.lastIndexOf(var4, (int)var5));
   }
}
