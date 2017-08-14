package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbnj extends zzbmg {
   // $FF: synthetic field
   private zzbog zzaOA;

   zzbnj(zzbnh var1, GoogleApiClient var2, zzbog var3) {
      this.zzaOA = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqm)(new zzbqm(this.zzaOA)), new zzbqq(this));
   }
}
