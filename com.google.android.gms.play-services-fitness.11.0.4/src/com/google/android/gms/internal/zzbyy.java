package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

final class zzbyy extends zzbxh {
   private final zzbaz zzaIz;
   private final zzbyx zzaWb;

   private zzbyy(zzbaz var1, zzbyx var2) {
      this.zzaIz = var1;
      this.zzaWb = var2;
   }

   public final void zzu(Status var1) {
      if (this.zzaWb != null && var1.isSuccess()) {
         this.zzaWb.zztS();
      }

      this.zzaIz.setResult(var1);
   }

   // $FF: synthetic method
   zzbyy(zzbaz var1, zzbyx var2, zzbyt var3) {
      this(var1, var2);
   }
}
