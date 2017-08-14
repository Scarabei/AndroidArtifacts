package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

public abstract class zzbvz extends zzee implements zzbvy {
   public zzbvz() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
   }

   public static zzbvy zzQ(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbvy)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IDataSourcesCallback")) instanceof zzbvy ? (zzbvy)var1 : new zzbwa(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         DataSourcesResult var5 = (DataSourcesResult)zzef.zza(var2, DataSourcesResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
