package com.google.android.gms.internal;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

final class zzcdf implements zzbdz {
   // $FF: synthetic field
   private LocationResult zzbiP;

   zzcdf(zzcde var1, LocationResult var2) {
      this.zzbiP = var2;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      LocationCallback var3 = (LocationCallback)var1;
      var3.onLocationResult(this.zzbiP);
   }
}
