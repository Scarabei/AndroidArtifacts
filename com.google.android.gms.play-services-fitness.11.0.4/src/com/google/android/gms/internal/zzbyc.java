package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.DataDeleteRequest;

final class zzbyc extends zzbvd {
   // $FF: synthetic field
   private DataDeleteRequest zzaVI;

   zzbyc(zzbya var1, GoogleApiClient var2, DataDeleteRequest var3) {
      this.zzaVI = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwn)var3.zzrf()).zza(new DataDeleteRequest(this.zzaVI, var4));
   }
}
