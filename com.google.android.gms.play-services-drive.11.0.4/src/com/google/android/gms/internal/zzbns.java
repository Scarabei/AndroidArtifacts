package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbns extends zzbmg {
   // $FF: synthetic field
   private zzbnn zzaOD;

   zzbns(zzbnn var1, GoogleApiClient var2) {
      this.zzaOD = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzblk)(new zzblk(this.zzaOD.zzaLV)), new zzbqq(this));
   }
}
