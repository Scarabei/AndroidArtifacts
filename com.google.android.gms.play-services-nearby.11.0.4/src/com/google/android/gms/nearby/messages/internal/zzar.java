package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;

final class zzar extends zzav {
   // $FF: synthetic field
   private zzbdw zzbhE;

   zzar(zzak var1, GoogleApiClient var2, zzbdw var3) {
      this.zzbhE = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      var3.zza(this.zzzX(), this.zzbhE);
   }
}
