package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

public abstract class zzbzl extends zzee implements zzbzk {
   public zzbzl() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
   }

   public static zzbzk zzX(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbzk)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback")) instanceof zzbzk ? (zzbzk)var1 : new zzbzm(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         BleDevicesResult var5 = (BleDevicesResult)zzef.zza(var2, BleDevicesResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
