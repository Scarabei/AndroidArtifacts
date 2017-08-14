package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.zzai;
import com.google.android.gms.fitness.request.zzbi;
import com.google.android.gms.fitness.request.zzbm;

public final class zzbws extends zzed implements zzbwr {
   zzbws(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
   }

   public final void zza(zzbi var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void zza(zzbm var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zza(zzai var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }
}
