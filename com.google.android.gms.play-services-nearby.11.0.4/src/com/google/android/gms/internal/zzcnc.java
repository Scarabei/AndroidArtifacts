package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcnc extends zzed implements zzcna {
   zzcnc(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
   }

   public final void zza(zzcny var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zza(zzcoa var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(3, var2);
   }

   public final void zza(zzcok var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(4, var2);
   }
}
