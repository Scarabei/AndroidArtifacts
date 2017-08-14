package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

final class zzcdv extends LocationServices.zza {
   // $FF: synthetic field
   private LocationSettingsRequest zzbjg;
   // $FF: synthetic field
   private String zzbjh;

   zzcdv(zzcdu var1, GoogleApiClient var2, LocationSettingsRequest var3, String var4) {
      this.zzbjg = var3;
      this.zzbjh = null;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zza((LocationSettingsRequest)this.zzbjg, (zzbaz)this, (String)this.zzbjh);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new LocationSettingsResult(var1);
   }
}
