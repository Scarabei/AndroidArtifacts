package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbno extends zzbny {
   // $FF: synthetic field
   private boolean zzaOC;
   // $FF: synthetic field
   private zzbnn zzaOD;

   zzbno(zzbnn var1, GoogleApiClient var2, boolean var3) {
      this.zzaOD = var1;
      this.zzaOC = false;
      super(var1, var2, (zzbno)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzboi)(new zzboi(this.zzaOD.zzaLV, this.zzaOC)), new zzbnw(this));
   }
}
