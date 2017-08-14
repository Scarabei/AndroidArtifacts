package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcla extends zzclf {
   // $FF: synthetic field
   private zzcny zzbwS;

   zzcla(zzckz var1, zzcny var2) {
      this.zzbwS = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.EndpointDiscoveryListener var3 = (Connections.EndpointDiscoveryListener)var1;
      var3.onEndpointFound(this.zzbwS.zzzJ(), this.zzbwS.getServiceId(), this.zzbwS.getEndpointName());
   }
}
