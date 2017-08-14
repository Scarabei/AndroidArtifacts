package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.zzay;

final class zzh extends zzay {
   // $FF: synthetic field
   private GoogleMap.OnMyLocationChangeListener zzblG;

   zzh(GoogleMap var1, GoogleMap.OnMyLocationChangeListener var2) {
      this.zzblG = var2;
      super();
   }

   public final void zzF(IObjectWrapper var1) {
      this.zzblG.onMyLocationChange((Location)com.google.android.gms.dynamic.zzn.zzE(var1));
   }
}
