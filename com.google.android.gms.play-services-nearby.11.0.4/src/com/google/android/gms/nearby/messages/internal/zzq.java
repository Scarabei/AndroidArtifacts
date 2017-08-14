package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzq extends zzee implements zzp {
   public zzq() {
      this.attachInterface(this, "com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         Status var5 = (Status)zzef.zza(var2, Status.CREATOR);
         this.zzG(var5);
         return true;
      } else {
         return false;
      }
   }
}
