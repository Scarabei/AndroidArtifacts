package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzv;

final class zzbyf extends zzbvd {
   // $FF: synthetic field
   private PendingIntent zzaVL;

   zzbyf(zzbya var1, GoogleApiClient var2, PendingIntent var3) {
      this.zzaVL = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbva var3 = (zzbva)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwn)var3.zzrf()).zza(new zzv(this.zzaVL, var4));
   }
}
