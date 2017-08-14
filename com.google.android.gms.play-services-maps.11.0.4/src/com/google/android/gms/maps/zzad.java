package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

final class zzad extends zzbg {
   // $FF: synthetic field
   private StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener zzbmE;

   zzad(StreetViewPanorama var1, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener var2) {
      this.zzbmE = var2;
      super();
   }

   public final void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera var1) {
      this.zzbmE.onStreetViewPanoramaCameraChange(var1);
   }
}
