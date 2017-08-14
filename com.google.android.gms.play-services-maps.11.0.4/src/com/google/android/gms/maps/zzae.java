package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzae extends zzbk {
   // $FF: synthetic field
   private StreetViewPanorama.OnStreetViewPanoramaClickListener zzbmF;

   zzae(StreetViewPanorama var1, StreetViewPanorama.OnStreetViewPanoramaClickListener var2) {
      this.zzbmF = var2;
      super();
   }

   public final void onStreetViewPanoramaClick(StreetViewPanoramaOrientation var1) {
      this.zzbmF.onStreetViewPanoramaClick(var1);
   }
}
