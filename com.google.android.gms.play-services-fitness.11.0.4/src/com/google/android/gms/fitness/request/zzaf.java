package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzaf extends zzed implements zzad {
   zzaf(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.request.IBleScanCallback");
   }

   public final void onDeviceFound(BleDevice var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }

   public final void onScanStopped() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(2, var1);
   }
}
