package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzj extends zzl {
   zzj(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzd var3 = (zzd)var1;
      ((zzt)var3.zzrf()).zzc(new zzk(this), var3.zzmI());
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
