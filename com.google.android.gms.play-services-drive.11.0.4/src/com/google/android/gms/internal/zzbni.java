package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbni extends zzbnm {
   // $FF: synthetic field
   private zzbnh zzaOz;

   zzbni(zzbnh var1, GoogleApiClient var2) {
      this.zzaOz = var1;
      super(var1, var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zzb(new zzbnk(this.zzaOz, this, (zzbni)null));
   }
}
