package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzclb extends zzclf {
   // $FF: synthetic field
   private zzcoa zzbwT;

   zzclb(zzckz var1, zzcoa var2) {
      this.zzbwT = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.EndpointDiscoveryListener var3 = (Connections.EndpointDiscoveryListener)var1;
      var3.onEndpointLost(this.zzbwT.zzzJ());
   }
}
