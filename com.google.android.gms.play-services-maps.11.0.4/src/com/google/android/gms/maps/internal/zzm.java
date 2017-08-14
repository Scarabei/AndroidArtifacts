package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.CameraPosition;

public abstract class zzm extends zzee implements zzl {
   public zzm() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnCameraChangeListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         CameraPosition var5 = (CameraPosition)zzef.zza(var2, CameraPosition.CREATOR);
         this.onCameraChange(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
