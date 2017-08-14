package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataUpdateRequest;

final class zzbyd extends zzbvd {
   // $FF: synthetic field
   private DataUpdateRequest zzaVJ;

   zzbyd(zzbya var1, GoogleApiClient var2, DataUpdateRequest var3) {
      this.zzaVJ = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwn)var3.zzrf()).zza(new DataUpdateRequest(this.zzaVJ, var4));
   }
}
