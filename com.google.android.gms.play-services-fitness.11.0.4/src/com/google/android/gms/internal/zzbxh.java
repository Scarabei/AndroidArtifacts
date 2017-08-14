package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzbxh extends zzee implements zzbxg {
   public zzbxh() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IStatusCallback");
   }

   public static zzbxg zzW(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbxg)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback")) instanceof zzbxg ? (zzbxg)var1 : new zzbxi(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         Status var5 = (Status)zzef.zza(var2, Status.CREATOR);
         this.zzu(var5);
         return true;
      } else {
         return false;
      }
   }
}
