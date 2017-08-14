package com.google.android.gms.internal;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzk;

final class zzcde extends zzk {
   private final zzbdw zzaEU;

   zzcde(zzbdw var1) {
      this.zzaEU = var1;
   }

   public final void onLocationResult(LocationResult var1) {
      this.zzaEU.zza(new zzcdf(this, var1));
   }

   public final void onLocationAvailability(LocationAvailability var1) {
      this.zzaEU.zza(new zzcdg(this, var1));
   }

   public final synchronized void release() {
      this.zzaEU.clear();
   }
}
