package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzv extends zzed implements zzt {
   zzv(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.data.IDataSourceListener");
   }

   public final void zzc(DataPoint var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
