package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationRequest;

final class zzccj extends zzccm {
   // $FF: synthetic field
   private LocationRequest zzbiD;
   // $FF: synthetic field
   private PendingIntent zzbiz;

   zzccj(zzccb var1, GoogleApiClient var2, LocationRequest var3, PendingIntent var4) {
      this.zzbiD = var3;
      this.zzbiz = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzccn var4 = new zzccn(this);
      var3.zza((LocationRequest)this.zzbiD, (PendingIntent)this.zzbiz, (zzccu)var4);
   }
}
