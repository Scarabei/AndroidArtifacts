package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcnj extends zzee implements zzcni {
   public zzcnj() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IResultListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         int var5 = var2.readInt();
         this.zzbq(var5);
         return true;
      } else {
         return false;
      }
   }
}
