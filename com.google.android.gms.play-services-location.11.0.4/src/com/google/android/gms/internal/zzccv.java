package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzccv extends zzee implements zzccu {
   public zzccv() {
      this.attachInterface(this, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         zzcco var5 = (zzcco)zzef.zza(var2, zzcco.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
