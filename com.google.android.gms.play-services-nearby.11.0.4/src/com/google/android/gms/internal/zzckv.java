package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzckv extends zzclf {
   // $FF: synthetic field
   private zzcns zzbwR;

   zzckv(zzcku var1, zzcns var2) {
      this.zzbwR = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.ConnectionResponseCallback var3 = (Connections.ConnectionResponseCallback)var1;
      var3.onConnectionResponse(this.zzbwR.zzzF(), new Status(this.zzbwR.getStatusCode()), this.zzbwR.zzzI());
   }
}
