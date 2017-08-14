package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpq;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzaq extends zzav {
   // $FF: synthetic field
   private PendingIntent zzaVL;
   // $FF: synthetic field
   private zzbdw zzbzl;
   // $FF: synthetic field
   private SubscribeOptions zzbzn;

   zzaq(zzak var1, GoogleApiClient var2, PendingIntent var3, zzbdw var4, SubscribeOptions var5) {
      this.zzaVL = var3;
      this.zzbzl = var4;
      this.zzbzn = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      zzbdw var10001 = this.zzzX();
      SubscribeOptions var8 = this.zzbzn;
      zzbdw var7 = this.zzbzl;
      PendingIntent var6 = this.zzaVL;
      zzbdw var5 = var10001;
      SubscribeRequest var9 = new SubscribeRequest((IBinder)null, var8.getStrategy(), new zzcpq(var5), var8.getFilter(), var6, (byte[])null, var7 == null ? null : new zzcpx(var7), var8.zzbyA, var8.zzbyB);
      ((zzs)var3.zzrf()).zza(var9);
   }
}
