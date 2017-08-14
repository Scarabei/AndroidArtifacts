package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

final class zzbxy extends zzbuy {
   // $FF: synthetic field
   private GoalsReadRequest zzaVE;

   zzbxy(zzbxx var1, GoogleApiClient var2, GoalsReadRequest var3) {
      this.zzaVE = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbux var3 = (zzbux)var1;
      zzbxz var4 = new zzbxz(this);
      ((zzbwl)var3.zzrf()).zza(new GoalsReadRequest(this.zzaVE, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new GoalsResult(var1, Collections.emptyList());
   }
}
