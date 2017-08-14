package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Message;

final class zzao extends zzav {
   // $FF: synthetic field
   private Message zzbzk;

   zzao(zzak var1, GoogleApiClient var2, Message var3) {
      this.zzbzk = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzah var3 = (zzah)var1;
      var3.zza(this.zzzX(), zzaf.zza(this.zzbzk));
   }
}
