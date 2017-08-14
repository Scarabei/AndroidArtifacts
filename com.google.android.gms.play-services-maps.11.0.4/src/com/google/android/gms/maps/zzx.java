package com.google.android.gms.maps;

import com.google.android.gms.maps.model.LatLng;

final class zzx extends com.google.android.gms.maps.internal.zzak {
   // $FF: synthetic field
   private GoogleMap.OnMapClickListener zzblW;

   zzx(GoogleMap var1, GoogleMap.OnMapClickListener var2) {
      this.zzblW = var2;
      super();
   }

   public final void onMapClick(LatLng var1) {
      this.zzblW.onMapClick(var1);
   }
}
