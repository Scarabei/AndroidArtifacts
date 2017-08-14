package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzaf extends zzbm {
   // $FF: synthetic field
   private StreetViewPanorama.OnStreetViewPanoramaLongClickListener zzbmG;

   zzaf(StreetViewPanorama var1, StreetViewPanorama.OnStreetViewPanoramaLongClickListener var2) {
      this.zzbmG = var2;
      super();
   }

   public final void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation var1) {
      this.zzbmG.onStreetViewPanoramaLongClick(var1);
   }
}
