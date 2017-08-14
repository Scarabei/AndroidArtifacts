package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.BleDevice;

public final class zza extends zzae {
   private final BleScanCallback zzaWj;

   private zza(BleScanCallback var1) {
      this.zzaWj = (BleScanCallback)zzbo.zzu(var1);
   }

   public final void onDeviceFound(BleDevice var1) throws RemoteException {
      this.zzaWj.onDeviceFound(var1);
   }

   public final void onScanStopped() throws RemoteException {
      this.zzaWj.onScanStopped();
   }

   // $FF: synthetic method
   zza(BleScanCallback var1, zzb var2) {
      this(var1);
   }
}
