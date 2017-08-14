package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;

final class zzl extends ILocationSourceDelegate.zza {
   // $FF: synthetic field
   private LocationSource zzblK;

   zzl(GoogleMap var1, LocationSource var2) {
      this.zzblK = var2;
      super();
   }

   public final void activate(com.google.android.gms.maps.internal.zzah var1) {
      this.zzblK.activate(new zzm(this, var1));
   }

   public final void deactivate() {
      this.zzblK.deactivate();
   }
}
