package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzadc extends zzed implements zzadb {
   zzadc(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
   }

   public final IBinder zza(IObjectWrapper var1, zzuq var2, int var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      var4.writeInt(11020000);
      Parcel var5;
      IBinder var6 = (var5 = this.zza(1, var4)).readStrongBinder();
      var5.recycle();
      return var6;
   }
}
