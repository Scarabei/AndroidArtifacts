package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzblt extends zzbmf {
   zzblt(zzblo var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbmh var3 = (zzbmh)var1;
      ((zzbom)var3.zzrf()).zzc(new zzblu(this, this));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new BooleanResult(var1, false);
   }
}
