package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

public final class zzbwd extends zzed implements zzbwb {
   zzbwd(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IDataTypeCallback");
   }

   public final void zza(DataTypeResult var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
