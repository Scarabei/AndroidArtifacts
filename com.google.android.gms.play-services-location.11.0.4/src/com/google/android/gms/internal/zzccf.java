package com.google.android.gms.internal;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;

final class zzccf extends zzccm {
   // $FF: synthetic field
   private Location zzbiH;

   zzccf(zzccb var1, GoogleApiClient var2, Location var3) {
      this.zzbiH = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zzc(this.zzbiH);
      this.setResult(Status.zzaBm);
   }
}
