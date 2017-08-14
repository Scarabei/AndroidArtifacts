package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;

final class zzcll extends zzcnm {
   private final zzbaz zzaVx;

   zzcll(zzbaz var1) {
      this.zzaVx = (zzbaz)zzbo.zzu(var1);
   }

   public final void zza(zzcog var1) {
      this.zzaVx.setResult(new zzclk(new Status(var1.getStatusCode()), var1.getLocalEndpointName()));
   }
}
