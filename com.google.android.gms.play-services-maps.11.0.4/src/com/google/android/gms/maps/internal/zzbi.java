package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public abstract class zzbi extends zzee implements zzbh {
   public zzbi() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         StreetViewPanoramaLocation var5 = (StreetViewPanoramaLocation)zzef.zza(var2, StreetViewPanoramaLocation.CREATOR);
         this.onStreetViewPanoramaChange(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
