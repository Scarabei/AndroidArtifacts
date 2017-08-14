package com.google.android.gms.maps;

final class zzv extends com.google.android.gms.maps.internal.zzq {
   // $FF: synthetic field
   private GoogleMap.OnCameraMoveCanceledListener zzblU;

   zzv(GoogleMap var1, GoogleMap.OnCameraMoveCanceledListener var2) {
      this.zzblU = var2;
      super();
   }

   public final void onCameraMoveCanceled() {
      this.zzblU.onCameraMoveCanceled();
   }
}
