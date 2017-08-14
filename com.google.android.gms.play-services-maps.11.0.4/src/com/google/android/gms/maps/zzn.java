package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Circle;

final class zzn extends com.google.android.gms.maps.internal.zzw {
   // $FF: synthetic field
   private GoogleMap.OnCircleClickListener zzblM;

   zzn(GoogleMap var1, GoogleMap.OnCircleClickListener var2) {
      this.zzblM = var2;
      super();
   }

   public final void zza(com.google.android.gms.maps.model.internal.zzd var1) {
      this.zzblM.onCircleClick(new Circle(var1));
   }
}
