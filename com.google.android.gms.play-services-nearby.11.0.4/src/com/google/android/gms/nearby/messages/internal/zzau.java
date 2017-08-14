package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;

final class zzau extends zzav {
   // $FF: synthetic field
   private zzbdw zzbzj;

   zzau(zzak var1, GoogleApiClient var2, zzbdw var3) {
      this.zzbzj = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      var3.zzb(this.zzzX(), this.zzbzj);
   }
}
