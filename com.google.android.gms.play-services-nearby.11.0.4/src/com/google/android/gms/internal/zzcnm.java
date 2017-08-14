package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcnm extends zzee implements zzcnl {
   public zzcnm() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         zzcog var5 = (zzcog)zzef.zza(var2, zzcog.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
