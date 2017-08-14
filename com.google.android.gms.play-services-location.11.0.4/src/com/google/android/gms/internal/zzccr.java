package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.GeofencingRequest;

final class zzccr extends zzcct {
   // $FF: synthetic field
   private GeofencingRequest zzbiK;
   // $FF: synthetic field
   private PendingIntent zzaVL;

   zzccr(zzccq var1, GoogleApiClient var2, GeofencingRequest var3, PendingIntent var4) {
      this.zzbiK = var3;
      this.zzaVL = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      var3.zza((GeofencingRequest)this.zzbiK, (PendingIntent)this.zzaVL, (zzbaz)this);
   }
}
