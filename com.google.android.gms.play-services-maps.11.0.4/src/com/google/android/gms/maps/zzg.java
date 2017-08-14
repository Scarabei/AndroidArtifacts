package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Marker;

final class zzg extends com.google.android.gms.maps.internal.zzi {
   // $FF: synthetic field
   private GoogleMap.InfoWindowAdapter zzblF;

   zzg(GoogleMap var1, GoogleMap.InfoWindowAdapter var2) {
      this.zzblF = var2;
      super();
   }

   public final IObjectWrapper zzh(com.google.android.gms.maps.model.internal.zzp var1) {
      return com.google.android.gms.dynamic.zzn.zzw(this.zzblF.getInfoWindow(new Marker(var1)));
   }

   public final IObjectWrapper zzi(com.google.android.gms.maps.model.internal.zzp var1) {
      return com.google.android.gms.dynamic.zzn.zzw(this.zzblF.getInfoContents(new Marker(var1)));
   }
}
