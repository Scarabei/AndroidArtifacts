package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.location.LocationListener;

final class zzcdi implements zzbdz {
   // $FF: synthetic field
   private Location zzbiR;

   zzcdi(zzcdh var1, Location var2) {
      this.zzbiR = var2;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      LocationListener var3 = (LocationListener)var1;
      var3.onLocationChanged(this.zzbiR);
   }
}
