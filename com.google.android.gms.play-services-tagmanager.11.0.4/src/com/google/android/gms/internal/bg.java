package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class bg extends zzcxq {
   private static final dt zzbKg = new dt(0.0D);
   private static final dt zzbKh = new dt(2.147483647E9D);

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      double var3 = 0.0D;
      double var5 = 2.147483647E9D;
      Object var7 = var2.length > 0 ? var2[0] : zzbKg;
      Object var8 = var2.length > 1 ? var2[1] : zzbKh;
      if (zzg((dp)var7) && zzg((dp)var8) && zzcxp.zzb((dp)var7, (dp)var8)) {
         var3 = ((Double)((dt)var7).zzDo()).doubleValue();
         var5 = ((Double)((dt)var8).zzDo()).doubleValue();
      }

      return new dt((double)Math.round(Math.random() * (var5 - var3) + var3));
   }

   private static boolean zzg(dp var0) {
      return var0 instanceof dt && !Double.isNaN(((Double)((dt)var0).zzDo()).doubleValue());
   }
}
