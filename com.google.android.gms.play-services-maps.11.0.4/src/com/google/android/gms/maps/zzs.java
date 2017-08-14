package com.google.android.gms.maps;

import com.google.android.gms.maps.model.CameraPosition;

final class zzs extends com.google.android.gms.maps.internal.zzm {
   // $FF: synthetic field
   private GoogleMap.OnCameraChangeListener zzblR;

   zzs(GoogleMap var1, GoogleMap.OnCameraChangeListener var2) {
      this.zzblR = var2;
      super();
   }

   public final void onCameraChange(CameraPosition var1) {
      this.zzblR.onCameraChange(var1);
   }
}
