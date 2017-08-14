package com.google.android.gms.maps;

final class zzw extends com.google.android.gms.maps.internal.zzo {
   // $FF: synthetic field
   private GoogleMap.OnCameraIdleListener zzblV;

   zzw(GoogleMap var1, GoogleMap.OnCameraIdleListener var2) {
      this.zzblV = var2;
      super();
   }

   public final void onCameraIdle() {
      this.zzblV.onCameraIdle();
   }
}
