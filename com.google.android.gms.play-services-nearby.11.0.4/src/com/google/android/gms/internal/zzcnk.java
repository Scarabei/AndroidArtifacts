package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcnk extends zzed implements zzcni {
   zzcnk(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.internal.connection.IResultListener");
   }

   public final void zzbq(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzc(2, var2);
   }
}
