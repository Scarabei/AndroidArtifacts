package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbjn extends zzed implements zzbjl {
   zzbjn(IBinder var1) {
      super(var1, "com.google.android.gms.contextmanager.fence.internal.IContextFenceListener");
   }

   public final void zza(zzbjh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void zza(zzbjd var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }
}
