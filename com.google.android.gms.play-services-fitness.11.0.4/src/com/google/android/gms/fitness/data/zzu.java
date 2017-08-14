package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzu extends zzee implements zzt {
   public zzu() {
      this.attachInterface(this, "com.google.android.gms.fitness.data.IDataSourceListener");
   }

   public static zzt zzN(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzt)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener")) instanceof zzt ? (zzt)var1 : new zzv(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         DataPoint var5 = (DataPoint)zzef.zza(var2, DataPoint.CREATOR);
         this.zzc(var5);
         return true;
      } else {
         return false;
      }
   }
}
