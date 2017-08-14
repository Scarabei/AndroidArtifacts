package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzj extends com.google.android.gms.location.places.zzf {
   // $FF: synthetic field
   private String zzbkk;

   zzj(zzh var1, Api var2, GoogleApiClient var3, String var4) {
      this.zzbkk = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzm var3 = (zzm)var1;
      var3.zza(new com.google.android.gms.location.places.zzd(this), this.zzbkk);
   }
}
