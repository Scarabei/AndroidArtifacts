package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

final class zzac extends zzbi {
   // $FF: synthetic field
   private StreetViewPanorama.OnStreetViewPanoramaChangeListener zzbmD;

   zzac(StreetViewPanorama var1, StreetViewPanorama.OnStreetViewPanoramaChangeListener var2) {
      this.zzbmD = var2;
      super();
   }

   public final void onStreetViewPanoramaChange(StreetViewPanoramaLocation var1) {
      this.zzbmD.onStreetViewPanoramaChange(var1);
   }
}
