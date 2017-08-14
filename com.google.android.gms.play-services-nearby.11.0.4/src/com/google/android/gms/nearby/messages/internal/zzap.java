package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzap extends zzav {
   // $FF: synthetic field
   private zzbdw zzbhE;
   // $FF: synthetic field
   private zzbdw zzbzl;
   // $FF: synthetic field
   private SubscribeOptions zzbzn;

   zzap(zzak var1, GoogleApiClient var2, zzbdw var3, zzbdw var4, SubscribeOptions var5) {
      this.zzbhE = var3;
      this.zzbzl = var4;
      this.zzbzn = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      var3.zza(this.zzzX(), this.zzbhE, this.zzbzl, this.zzbzn, (byte[])null);
   }
}
