package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzba;
import com.google.android.gms.maps.model.PointOfInterest;

final class zzr extends zzba {
   // $FF: synthetic field
   private GoogleMap.OnPoiClickListener zzblQ;

   zzr(GoogleMap var1, GoogleMap.OnPoiClickListener var2) {
      this.zzblQ = var2;
      super();
   }

   public final void zza(PointOfInterest var1) throws RemoteException {
      this.zzblQ.onPoiClick(var1);
   }
}
