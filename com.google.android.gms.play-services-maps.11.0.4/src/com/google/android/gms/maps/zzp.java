package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

final class zzp extends zzbe {
   // $FF: synthetic field
   private GoogleMap.OnPolylineClickListener zzblO;

   zzp(GoogleMap var1, GoogleMap.OnPolylineClickListener var2) {
      this.zzblO = var2;
      super();
   }

   public final void zza(IPolylineDelegate var1) {
      this.zzblO.onPolylineClick(new Polyline(var1));
   }
}
