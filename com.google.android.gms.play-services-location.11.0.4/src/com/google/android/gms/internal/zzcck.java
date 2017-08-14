package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationListener;

final class zzcck extends zzccm {
   // $FF: synthetic field
   private LocationListener zzbiE;

   zzcck(zzccb var1, GoogleApiClient var2, LocationListener var3) {
      this.zzbiE = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzbdy var4 = zzbea.zza(this.zzbiE, LocationListener.class.getSimpleName());
      zzccn var5 = new zzccn(this);
      var3.zza((zzbdy)var4, (zzccu)var5);
   }
}
