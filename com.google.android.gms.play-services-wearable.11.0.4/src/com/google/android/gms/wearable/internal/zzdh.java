package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdh extends com.google.android.gms.internal.zzee implements zzdg {
   public zzdh() {
      this.attachInterface(this, "com.google.android.gms.wearable.internal.IChannelStreamCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         int var5 = var2.readInt();
         int var6 = var2.readInt();
         this.zzm(var5, var6);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
