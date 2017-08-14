package com.google.android.gms.maps;

import com.google.android.gms.maps.model.IndoorBuilding;

final class zza extends com.google.android.gms.maps.internal.zzaa {
   // $FF: synthetic field
   private GoogleMap.OnIndoorStateChangeListener zzblz;

   zza(GoogleMap var1, GoogleMap.OnIndoorStateChangeListener var2) {
      this.zzblz = var2;
      super();
   }

   public final void onIndoorBuildingFocused() {
      this.zzblz.onIndoorBuildingFocused();
   }

   public final void zza(com.google.android.gms.maps.model.internal.zzj var1) {
      this.zzblz.onIndoorLevelActivated(new IndoorBuilding(var1));
   }
}
