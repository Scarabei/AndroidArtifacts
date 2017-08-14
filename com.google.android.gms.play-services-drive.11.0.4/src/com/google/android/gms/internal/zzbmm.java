package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.List;

final class zzbmm extends zzbmg {
   // $FF: synthetic field
   private List zzaOf;

   zzbmm(zzbmh var1, GoogleApiClient var2, List var3) {
      this.zzaOf = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbku)(new zzbku(this.zzaOf)), new zzbqq(this));
   }
}
