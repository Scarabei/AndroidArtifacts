package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;

final class zzl extends com.google.android.gms.location.places.zzm.zza {
   // $FF: synthetic field
   private String val$query;
   // $FF: synthetic field
   private LatLngBounds zzbkm;
   // $FF: synthetic field
   private AutocompleteFilter zzbkn;

   zzl(zzh var1, Api var2, GoogleApiClient var3, String var4, LatLngBounds var5, AutocompleteFilter var6) {
      this.val$query = var4;
      this.zzbkm = var5;
      this.zzbkn = var6;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzm var3 = (zzm)var1;
      var3.zza(new com.google.android.gms.location.places.zzm(this), this.val$query, this.zzbkm, this.zzbkn);
   }
}
