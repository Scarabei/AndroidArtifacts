package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcnn extends zzed implements zzcnl {
   zzcnn(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
   }

   public final void zza(zzcog var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }
}
