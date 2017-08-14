package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;

final class zzclj extends zzcnj {
   private final zzbaz zzaVx;

   zzclj(zzbaz var1) {
      this.zzaVx = (zzbaz)zzbo.zzu(var1);
   }

   public final void zzbq(int var1) {
      this.zzaVx.setResult(new Status(var1));
   }
}
