package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceReport;

final class zzz extends com.google.android.gms.location.places.zzm.zzf {
   // $FF: synthetic field
   private PlaceReport zzbkq;

   zzz(zzx var1, Api var2, GoogleApiClient var3, PlaceReport var4) {
      this.zzbkq = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzaa var3 = (zzaa)var1;
      var3.zza(new com.google.android.gms.location.places.zzm(this), this.zzbkq);
   }
}
