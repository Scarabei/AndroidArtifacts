package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;

final class zzd extends com.google.android.gms.maps.internal.zzac {
   // $FF: synthetic field
   private GoogleMap.OnInfoWindowClickListener zzblC;

   zzd(GoogleMap var1, GoogleMap.OnInfoWindowClickListener var2) {
      this.zzblC = var2;
      super();
   }

   public final void zze(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblC.onInfoWindowClick(new Marker(var1));
   }
}
