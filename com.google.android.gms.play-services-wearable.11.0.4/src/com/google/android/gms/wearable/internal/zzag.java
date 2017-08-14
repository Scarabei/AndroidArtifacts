package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.ChannelApi;

final class zzag extends zzn {
   private final String zzakv;
   private ChannelApi.ChannelListener zzbSg;

   zzag(GoogleApiClient var1, ChannelApi.ChannelListener var2, String var3) {
      super(var1);
      this.zzbSg = (ChannelApi.ChannelListener)com.google.android.gms.common.internal.zzbo.zzu(var2);
      this.zzakv = var3;
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, this.zzbSg, this.zzakv);
      this.zzbSg = null;
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      this.zzbSg = null;
      return var1;
   }
}
