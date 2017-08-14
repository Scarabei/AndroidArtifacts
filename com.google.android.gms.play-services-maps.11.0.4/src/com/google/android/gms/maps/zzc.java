package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

final class zzc extends zzau {
   // $FF: synthetic field
   private GoogleMap.OnMarkerDragListener zzblB;

   zzc(GoogleMap var1, GoogleMap.OnMarkerDragListener var2) {
      this.zzblB = var2;
      super();
   }

   public final void zzb(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblB.onMarkerDragStart(new Marker(var1));
   }

   public final void zzc(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblB.onMarkerDragEnd(new Marker(var1));
   }

   public final void zzd(com.google.android.gms.maps.model.internal.zzp var1) {
      this.zzblB.onMarkerDrag(new Marker(var1));
   }
}
