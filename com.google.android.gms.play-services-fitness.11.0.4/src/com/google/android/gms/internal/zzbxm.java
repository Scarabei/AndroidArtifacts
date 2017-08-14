package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zzbg;

final class zzbxm extends zzbus {
   // $FF: synthetic field
   private BleScanCallback zzaVz;

   zzbxm(zzbxk var1, GoogleApiClient var2, BleScanCallback var3) {
      this.zzaVz = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbup var3 = (zzbup)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwh)var3.zzrf()).zza(new zzbg(this.zzaVz, var4));
   }
}
