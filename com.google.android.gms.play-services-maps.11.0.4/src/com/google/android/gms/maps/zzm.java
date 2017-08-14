package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm implements LocationSource.OnLocationChangedListener {
   // $FF: synthetic field
   private com.google.android.gms.maps.internal.zzah zzblL;

   zzm(zzl var1, com.google.android.gms.maps.internal.zzah var2) {
      this.zzblL = var2;
      super();
   }

   public final void onLocationChanged(Location var1) {
      try {
         this.zzblL.zzd(var1);
      } catch (RemoteException var3) {
         throw new RuntimeRemoteException(var3);
      }
   }
}
