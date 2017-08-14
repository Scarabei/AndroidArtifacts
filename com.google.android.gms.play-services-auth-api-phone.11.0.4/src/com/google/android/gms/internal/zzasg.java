package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzasg extends zzee implements zzasf {
   public zzasg() {
      this.attachInterface(this, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         Status var5 = (Status)zzef.zza(var2, Status.CREATOR);
         this.zzf(var5);
         return true;
      } else {
         return false;
      }
   }
}
