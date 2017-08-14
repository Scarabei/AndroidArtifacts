package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

public final class zzbxc extends zzed implements zzbxa {
   zzbxc(IBinder var1) {
      super(var1, "com.google.android.gms.fitness.internal.ISessionReadCallback");
   }

   public final void zza(SessionReadResult var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
