package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;

final class zzy extends zzao {
   // $FF: synthetic field
   private GoogleMap.OnMapLongClickListener zzblX;

   zzy(GoogleMap var1, GoogleMap.OnMapLongClickListener var2) {
      this.zzblX = var2;
      super();
   }

   public final void onMapLongClick(LatLng var1) {
      this.zzblX.onMapLongClick(var1);
   }
}
