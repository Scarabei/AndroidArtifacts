package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

final class zzdu extends zzn {
   // $FF: synthetic field
   private MessageApi.MessageListener zzbSU;

   zzdu(zzds var1, GoogleApiClient var2, MessageApi.MessageListener var3) {
      this.zzbSU = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (MessageApi.MessageListener)this.zzbSU);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
