package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbnp extends zzbmc {
   // $FF: synthetic field
   private zzbnn zzaOD;

   zzbnp(zzbnn var1, GoogleApiClient var2) {
      this.zzaOD = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbov)(new zzbov(this.zzaOD.zzaLV)), new zzbnv(this));
   }
}
