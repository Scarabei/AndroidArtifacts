package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzbc;

final class zzbzf extends zzbvr {
   // $FF: synthetic field
   private PendingIntent zzaVX;

   zzbzf(zzbyz var1, GoogleApiClient var2, PendingIntent var3) {
      this.zzaVX = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwv)var3.zzrf()).zza(new zzbc(this.zzaVX, var4));
   }
}
