package com.google.android.gms.maps;

final class zzt extends com.google.android.gms.maps.internal.zzu {
   // $FF: synthetic field
   private GoogleMap.OnCameraMoveStartedListener zzblS;

   zzt(GoogleMap var1, GoogleMap.OnCameraMoveStartedListener var2) {
      this.zzblS = var2;
      super();
   }

   public final void onCameraMoveStarted(int var1) {
      this.zzblS.onCameraMoveStarted(var1);
   }
}
