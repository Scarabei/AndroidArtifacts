package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.request.zzz;

public final class zzbwk extends zzed implements zzbwj {
   zzbwk(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
   }

   public final void zza(DataTypeCreateRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void zza(zzr var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zza(zzz var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22, var2);
   }
}
