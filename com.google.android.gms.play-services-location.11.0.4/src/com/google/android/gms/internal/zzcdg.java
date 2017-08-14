package com.google.android.gms.internal;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

final class zzcdg implements zzbdz {
   // $FF: synthetic field
   private LocationAvailability zzbiQ;

   zzcdg(zzcde var1, LocationAvailability var2) {
      this.zzbiQ = var2;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      LocationCallback var3 = (LocationCallback)var1;
      var3.onLocationAvailability(this.zzbiQ);
   }
}
