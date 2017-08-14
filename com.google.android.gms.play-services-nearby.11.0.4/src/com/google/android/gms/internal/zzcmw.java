package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcmw extends zzee implements zzcmv {
   public zzcmw() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         zzcns var5 = (zzcns)zzef.zza(var2, zzcns.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
