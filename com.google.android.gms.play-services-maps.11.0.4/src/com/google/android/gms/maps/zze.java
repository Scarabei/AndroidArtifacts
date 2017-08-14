package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;

final class zze extends com.google.android.gms.maps.internal.zzag {
   // $FF: synthetic field
   private GoogleMap.OnInfoWindowLongClickListener zzblD;

   zze(GoogleMap var1, GoogleMap.OnInfoWindowLongClickListener var2) {
      this.zzblD = var2;
      super();
   }

   public final void zzf(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblD.onInfoWindowLongClick(new Marker(var1));
   }
}
