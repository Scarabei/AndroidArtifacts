package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.List;

final class zzbnq extends zzbmg {
   // $FF: synthetic field
   private List zzaOE;
   // $FF: synthetic field
   private zzbnn zzaOD;

   zzbnq(zzbnn var1, GoogleApiClient var2, List var3) {
      this.zzaOD = var1;
      this.zzaOE = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqo)(new zzbqo(this.zzaOD.zzaLV, this.zzaOE)), new zzbqq(this));
   }
}
