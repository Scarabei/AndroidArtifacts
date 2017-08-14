package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzbin {
   private final ach zzaKO;

   public static zzbin zza(int var0, int[] var1) {
      zzbo.zzaf(var1 != null && var1.length > 0);
      ach var4;
      (var4 = new ach()).zzcqq = var0;
      var4.zzcqr = 3000L;
      var4.zzcqs = var1;
      return new zzbin(var4);
   }

   private zzbin(ach var1) {
      this.zzaKO = (ach)zzbo.zzu(var1);
   }

   public final ach zzsB() {
      return this.zzaKO;
   }
}
