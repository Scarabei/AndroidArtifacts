package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public abstract class zzbo extends zzee implements zzbn {
   public zzbo() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IBinder var6;
         IInterface var7;
         Object var5 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate")) instanceof IStreetViewPanoramaDelegate ? (IStreetViewPanoramaDelegate)var7 : new zzbs(var6));
         this.zza((IStreetViewPanoramaDelegate)var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
