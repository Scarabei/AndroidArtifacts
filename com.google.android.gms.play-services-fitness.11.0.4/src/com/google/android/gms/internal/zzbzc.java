package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.SessionInsertRequest;

final class zzbzc extends zzbvr {
   // $FF: synthetic field
   private SessionInsertRequest zzaWe;

   zzbzc(zzbyz var1, GoogleApiClient var2, SessionInsertRequest var3) {
      this.zzaWe = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwv)var3.zzrf()).zza(new SessionInsertRequest(this.zzaWe, var4));
   }
}
