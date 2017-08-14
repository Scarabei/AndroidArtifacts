package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;

final class zzbye extends zzbvd {
   // $FF: synthetic field
   private DataUpdateListenerRegistrationRequest zzaVK;

   zzbye(zzbya var1, GoogleApiClient var2, DataUpdateListenerRegistrationRequest var3) {
      this.zzaVK = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwn)var3.zzrf()).zza(new DataUpdateListenerRegistrationRequest(this.zzaVK, var4));
   }
}
