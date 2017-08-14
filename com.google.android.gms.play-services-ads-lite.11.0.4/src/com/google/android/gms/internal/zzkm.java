package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzkm extends zzed implements zzkk {
   zzkm(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
   }

   public final long getValue() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      long var3 = (var2 = this.zza(1, var1)).readLong();
      var2.recycle();
      return var3;
   }
}
