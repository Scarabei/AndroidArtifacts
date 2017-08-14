package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpq;

final class zzat extends zzav {
   zzat(zzak var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      zzbdw var5 = this.zzzX();
      zzh var6 = new zzh(new zzcpq(var5));
      ((zzs)var3.zzrf()).zza(var6);
   }
}
