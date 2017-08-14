package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzccw extends zzed implements zzccu {
   zzccw(IBinder var1) {
      super(var1, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
   }

   public final void zza(zzcco var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
