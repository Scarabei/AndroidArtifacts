package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbmr extends zzbmg {
   // $FF: synthetic field
   private zzbmn zzaOj;

   zzbmr(zzbmn var1, GoogleApiClient var2) {
      this.zzaOj = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbky)(new zzbky(zzbmn.zza(this.zzaOj).getRequestId(), false)), new zzbqq(this));
   }
}
