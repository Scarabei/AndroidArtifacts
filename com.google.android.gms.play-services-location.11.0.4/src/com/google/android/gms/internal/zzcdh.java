package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.location.zzn;

final class zzcdh extends zzn {
   private final zzbdw zzaEU;

   zzcdh(zzbdw var1) {
      this.zzaEU = var1;
   }

   public final synchronized void onLocationChanged(Location var1) {
      this.zzaEU.zza(new zzcdi(this, var1));
   }

   public final synchronized void release() {
      this.zzaEU.clear();
   }
}
