package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzam;

final class zzj extends zzam {
   // $FF: synthetic field
   private GoogleMap.OnMapLoadedCallback zzblI;

   zzj(GoogleMap var1, GoogleMap.OnMapLoadedCallback var2) {
      this.zzblI = var2;
      super();
   }

   public final void onMapLoaded() throws RemoteException {
      this.zzblI.onMapLoaded();
   }
}
