package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.Polygon;

final class zzo extends zzbc {
   // $FF: synthetic field
   private GoogleMap.OnPolygonClickListener zzblN;

   zzo(GoogleMap var1, GoogleMap.OnPolygonClickListener var2) {
      this.zzblN = var2;
      super();
   }

   public final void zza(com.google.android.gms.maps.model.internal.zzs var1) {
      this.zzblN.onPolygonClick(new Polygon(var1));
   }
}
