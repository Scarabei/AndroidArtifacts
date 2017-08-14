package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AddPlaceRequest;

final class zzi extends com.google.android.gms.location.places.zzm.zzc {
   // $FF: synthetic field
   private AddPlaceRequest zzbkj;

   zzi(zzh var1, Api var2, GoogleApiClient var3, AddPlaceRequest var4) {
      this.zzbkj = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzm var3 = (zzm)var1;
      var3.zza(new com.google.android.gms.location.places.zzm(this), this.zzbkj);
   }
}
