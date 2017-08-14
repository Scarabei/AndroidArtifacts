package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzps extends zzed implements zzpq {
   zzps(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
   }

   public final void zza(zzpf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }
}
