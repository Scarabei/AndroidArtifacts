package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;

public abstract class zzay extends zzee implements zzax {
   public zzay() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
         this.zzF(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
