package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzacx extends zzed implements zzacv {
   zzacx(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
   }

   public final String getType() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(1, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final int getAmount() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(2, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
