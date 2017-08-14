package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationCallback;

final class zzccd extends zzccm {
   // $FF: synthetic field
   private LocationCallback zzbiF;

   zzccd(zzccb var1, GoogleApiClient var2, LocationCallback var3) {
      this.zzbiF = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzbdy var4 = zzbea.zza(this.zzbiF, LocationCallback.class.getSimpleName());
      zzccn var5 = new zzccn(this);
      var3.zzb(var4, var5);
   }
}
