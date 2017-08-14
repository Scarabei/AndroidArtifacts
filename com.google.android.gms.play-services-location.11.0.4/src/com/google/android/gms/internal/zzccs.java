package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.zzaa;

final class zzccs extends zzcct {
   // $FF: synthetic field
   private zzaa zzbiL;

   zzccs(zzccq var1, GoogleApiClient var2, zzaa var3) {
      this.zzbiL = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zza((zzaa)this.zzbiL, (zzbaz)this);
   }
}
