package com.google.android.gms.maps;

import com.google.android.gms.maps.model.GroundOverlay;

final class zzk extends com.google.android.gms.maps.internal.zzy {
   // $FF: synthetic field
   private GoogleMap.OnGroundOverlayClickListener zzblJ;

   zzk(GoogleMap var1, GoogleMap.OnGroundOverlayClickListener var2) {
      this.zzblJ = var2;
      super();
   }

   public final void zza(com.google.android.gms.maps.model.internal.zzg var1) {
      this.zzblJ.onGroundOverlayClick(new GroundOverlay(var1));
   }
}
