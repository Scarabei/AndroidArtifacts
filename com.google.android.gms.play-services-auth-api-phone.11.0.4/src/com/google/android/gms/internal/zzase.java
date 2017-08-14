package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzase extends zzed implements zzasd {
   zzase(IBinder var1) {
      super(var1, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
   }

   public final void zza(zzasf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }
}
