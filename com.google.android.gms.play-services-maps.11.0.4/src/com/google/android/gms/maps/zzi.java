package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzaw;

final class zzi extends zzaw {
   // $FF: synthetic field
   private GoogleMap.OnMyLocationButtonClickListener zzblH;

   zzi(GoogleMap var1, GoogleMap.OnMyLocationButtonClickListener var2) {
      this.zzblH = var2;
      super();
   }

   public final boolean onMyLocationButtonClick() throws RemoteException {
      return this.zzblH.onMyLocationButtonClick();
   }
}
