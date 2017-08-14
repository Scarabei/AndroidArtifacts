package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;

final class zzf extends com.google.android.gms.maps.internal.zzae {
   // $FF: synthetic field
   private GoogleMap.OnInfoWindowCloseListener zzblE;

   zzf(GoogleMap var1, GoogleMap.OnInfoWindowCloseListener var2) {
      this.zzblE = var2;
      super();
   }

   public final void zzg(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblE.onInfoWindowClose(new Marker(var1));
   }
}
