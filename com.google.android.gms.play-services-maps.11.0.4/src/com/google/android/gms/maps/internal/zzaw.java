package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzaw extends zzee implements zzav {
   public zzaw() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         boolean var5 = this.onMyLocationButtonClick();
         var3.writeNoException();
         zzef.zza(var3, var5);
         return true;
      } else {
         return false;
      }
   }
}
