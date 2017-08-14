package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

public abstract class zzbwc extends zzee implements zzbwb {
   public zzbwc() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IDataTypeCallback");
   }

   public static zzbwb zzR(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbwb)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IDataTypeCallback")) instanceof zzbwb ? (zzbwb)var1 : new zzbwd(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         DataTypeResult var5 = (DataTypeResult)zzef.zza(var2, DataTypeResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
