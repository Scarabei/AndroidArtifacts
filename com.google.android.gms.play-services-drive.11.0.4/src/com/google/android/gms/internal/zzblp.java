package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.drive.query.Query;

final class zzblp extends zzbmc {
   // $FF: synthetic field
   private Query zzaNK;

   zzblp(zzblo var1, GoogleApiClient var2, Query var3) {
      this.zzaNK = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbqi)(new zzbqi(this.zzaNK)), new zzbmd(this));
   }
}
