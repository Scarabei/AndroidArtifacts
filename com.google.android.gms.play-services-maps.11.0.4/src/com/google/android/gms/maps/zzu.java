package com.google.android.gms.maps;

final class zzu extends com.google.android.gms.maps.internal.zzs {
   // $FF: synthetic field
   private GoogleMap.OnCameraMoveListener zzblT;

   zzu(GoogleMap var1, GoogleMap.OnCameraMoveListener var2) {
      this.zzblT = var2;
      super();
   }

   public final void onCameraMove() {
      this.zzblT.onCameraMove();
   }
}
