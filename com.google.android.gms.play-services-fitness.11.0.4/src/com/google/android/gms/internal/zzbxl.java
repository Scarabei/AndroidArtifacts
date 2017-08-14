package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.StartBleScanRequest;

final class zzbxl extends zzbus {
   // $FF: synthetic field
   private StartBleScanRequest zzaVy;

   zzbxl(zzbxk var1, GoogleApiClient var2, StartBleScanRequest var3) {
      this.zzaVy = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbup var3 = (zzbup)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwh)var3.zzrf()).zza(new StartBleScanRequest(this.zzaVy, var4));
   }
}
