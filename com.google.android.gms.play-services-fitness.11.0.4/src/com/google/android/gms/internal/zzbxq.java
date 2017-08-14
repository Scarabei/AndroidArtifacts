package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzag;
import com.google.android.gms.fitness.result.BleDevicesResult;

final class zzbxq extends zzbuq {
   zzbxq(zzbxk var1, GoogleApiClient var2) {
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbup var3 = (zzbup)var1;
      zzbxr var4 = new zzbxr(this, (zzbxl)null);
      ((zzbwh)var3.zzrf()).zza(new zzag(var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return BleDevicesResult.zzB(var1);
   }
}
