package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public final class zzasc extends zzed implements zzasb {
   zzasc(IBinder var1) {
      super(var1, "com.google.android.gms.auth.api.internal.IAuthService");
   }

   public final void zza(zzarz var1, ProxyRequest var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }
}
