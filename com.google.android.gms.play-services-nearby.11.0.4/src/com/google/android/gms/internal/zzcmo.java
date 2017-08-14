package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcmo extends zzed implements zzcmm {
   zzcmo(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
   }

   public final void zza(zzcnq var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zza(zzcoi var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(3, var2);
   }
}
