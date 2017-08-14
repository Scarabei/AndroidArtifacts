package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceFilter;

final class zzy extends com.google.android.gms.location.places.zzm.zzd {
   // $FF: synthetic field
   private PlaceFilter zzbkp;

   zzy(zzx var1, Api var2, GoogleApiClient var3, PlaceFilter var4) {
      this.zzbkp = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzaa var3 = (zzaa)var1;
      var3.zza(new com.google.android.gms.location.places.zzm(this), this.zzbkp);
   }
}
