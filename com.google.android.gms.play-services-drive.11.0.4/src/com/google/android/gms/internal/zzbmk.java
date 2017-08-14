package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzbmk extends zzbmg {
   // $FF: synthetic field
   private zzbkr zzaOa;

   zzbmk(zzbmh var1, GoogleApiClient var2, zzbkr var3) {
      this.zzaOa = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbkr)this.zzaOa, (zzboq)null, (String)null, new zzbqq(this));
   }
}
