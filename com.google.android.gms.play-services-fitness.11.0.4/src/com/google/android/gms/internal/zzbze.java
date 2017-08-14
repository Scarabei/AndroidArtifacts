package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.request.zzaw;

final class zzbze extends zzbvr {
   // $FF: synthetic field
   private PendingIntent zzaVX;
   // $FF: synthetic field
   private int zzaWg;

   zzbze(zzbyz var1, GoogleApiClient var2, PendingIntent var3, int var4) {
      this.zzaVX = var3;
      this.zzaWg = 0;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvo var3 = (zzbvo)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwv)var3.zzrf()).zza(new zzaw(this.zzaVX, var4, this.zzaWg));
   }
}
