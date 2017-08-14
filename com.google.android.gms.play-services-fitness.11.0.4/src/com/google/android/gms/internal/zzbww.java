package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.request.zzbc;

public final class zzbww extends zzed implements zzbwv {
   zzbww(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
   }

   public final void zza(zzay var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void zza(zzba var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void zza(SessionInsertRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void zza(SessionReadRequest var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void zza(zzaw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void zza(zzbc var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(6, var2);
   }
}
