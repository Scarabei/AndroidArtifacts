package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzckq extends zzclf {
   // $FF: synthetic field
   private zzcnu zzbwN;

   zzckq(zzcko var1, zzcnu var2) {
      this.zzbwN = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      ConnectionLifecycleCallback var3 = (ConnectionLifecycleCallback)var1;
      var3.onConnectionResult(this.zzbwN.zzzF(), new ConnectionResolution(new Status(this.zzbwN.getStatusCode())));
   }
}
