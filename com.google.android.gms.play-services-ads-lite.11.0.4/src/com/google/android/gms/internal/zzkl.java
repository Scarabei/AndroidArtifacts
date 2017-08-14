package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkl extends zzee implements zzkk {
   public zzkl() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         long var5 = this.getValue();
         var3.writeNoException();
         var3.writeLong(var5);
         return true;
      } else {
         return false;
      }
   }
}
