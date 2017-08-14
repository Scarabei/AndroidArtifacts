package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;

final class zzbzb extends zzbvp {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private String zzaWd;

   zzbzb(zzbyz var1, GoogleApiClient var2, String var3, String var4) {
      this.val$name = null;
      this.zzaWd = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzh var4 = new zzbzh(this, (zzbza)null);
      ((zzbwv)var3.zzrf()).zza(new zzba(this.val$name, this.zzaWd, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new SessionStopResult(var1, Collections.emptyList());
   }
}
