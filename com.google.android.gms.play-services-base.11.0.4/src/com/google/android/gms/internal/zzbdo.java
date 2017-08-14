package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzbdo extends zzee implements zzbdn {
   public zzbdo() {
      this.attachInterface(this, "com.google.android.gms.common.api.internal.IStatusCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         Status var5 = (Status)zzef.zza(var2, Status.CREATOR);
         this.zzu(var5);
         return true;
      } else {
         return false;
      }
   }
}
