package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzva extends zzee implements zzuz {
   public zzva() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         int var5 = this.zzfo();
         var3.writeNoException();
         var3.writeInt(var5);
         return true;
      } else {
         return false;
      }
   }
}
