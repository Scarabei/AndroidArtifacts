package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzblq extends zzblx {
   // $FF: synthetic field
   private int zzaNL;

   zzblq(zzblo var1, GoogleApiClient var2, int var3) {
      this.zzaNL = 536870912;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zza((zzbla)(new zzbla(this.zzaNL)), new zzblv(this));
   }
}
