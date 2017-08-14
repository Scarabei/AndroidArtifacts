package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzae extends zzee implements zzad {
   public zzae() {
      this.attachInterface(this, "com.google.android.gms.fitness.request.IBleScanCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            BleDevice var5 = (BleDevice)zzef.zza(var2, BleDevice.CREATOR);
            this.onDeviceFound(var5);
            break;
         case 2:
            this.onScanStopped();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
