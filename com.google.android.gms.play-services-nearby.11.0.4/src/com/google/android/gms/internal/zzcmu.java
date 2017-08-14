package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcmu extends zzed implements zzcms {
   zzcmu(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
   }

   public final void zza(zzcno var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zza(zzcnu var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(3, var2);
   }

   public final void zza(zzcnw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(4, var2);
   }
}
