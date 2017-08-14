package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcmx extends zzed implements zzcmv {
   zzcmx(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
   }

   public final void zza(zzcns var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }
}
