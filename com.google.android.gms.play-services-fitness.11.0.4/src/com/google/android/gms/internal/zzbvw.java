package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

public abstract class zzbvw extends zzee implements zzbvv {
   public zzbvw() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IDataReadCallback");
   }

   public static zzbvv zzP(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbvv)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback")) instanceof zzbvv ? (zzbvv)var1 : new zzbvx(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         DataReadResult var5 = (DataReadResult)zzef.zza(var2, DataReadResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
