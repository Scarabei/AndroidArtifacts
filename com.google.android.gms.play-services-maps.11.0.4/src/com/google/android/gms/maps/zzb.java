package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzas {
   // $FF: synthetic field
   private GoogleMap.OnMarkerClickListener zzblA;

   zzb(GoogleMap var1, GoogleMap.OnMarkerClickListener var2) {
      this.zzblA = var2;
      super();
   }

   public final boolean zza(com.google.android.gms.maps.model.internal.zzp var1) {
      return this.zzblA.onMarkerClick(new Marker(var1));
   }
}
