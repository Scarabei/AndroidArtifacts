package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpq;

final class zzas extends zzav {
   // $FF: synthetic field
   private PendingIntent zzaVL;

   zzas(zzak var1, GoogleApiClient var2, PendingIntent var3) {
      this.zzaVL = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      zzbdw var10001 = this.zzzX();
      PendingIntent var6 = this.zzaVL;
      zzbdw var5 = var10001;
      zzbe var7 = new zzbe((IBinder)null, new zzcpq(var5), var6);
      ((zzs)var3.zzrf()).zza(var7);
   }
}
