package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.NodeApi;

final class zzed extends zzn {
   // $FF: synthetic field
   private NodeApi.NodeListener zzbSX;

   zzed(zzdz var1, GoogleApiClient var2, NodeApi.NodeListener var3) {
      this.zzbSX = var3;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      zzfw var3 = (zzfw)var1;
      var3.zza(this, (NodeApi.NodeListener)this.zzbSX);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
