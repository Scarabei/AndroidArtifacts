package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzbzd extends zzbvp {
   // $FF: synthetic field
   private SessionReadRequest zzaWf;

   zzbzd(zzbyz var1, GoogleApiClient var2, SessionReadRequest var3) {
      this.zzaWf = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzg var4 = new zzbzg(this, (zzbza)null);
      ((zzbwv)var3.zzrf()).zza(new SessionReadRequest(this.zzaWf, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return SessionReadResult.zzE(var1);
   }
}
