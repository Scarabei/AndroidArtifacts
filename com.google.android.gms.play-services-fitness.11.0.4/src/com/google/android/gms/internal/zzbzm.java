package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

public final class zzbzm extends zzed implements zzbzk {
   zzbzm(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
   }

   public final void zza(BleDevicesResult var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
