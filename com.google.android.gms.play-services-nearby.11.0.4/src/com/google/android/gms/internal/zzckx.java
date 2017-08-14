package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzckx extends zzclf {
   // $FF: synthetic field
   private zzcny zzbwS;

   zzckx(zzckw var1, zzcny var2) {
      this.zzbwS = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      EndpointDiscoveryCallback var3 = (EndpointDiscoveryCallback)var1;
      var3.onEndpointFound(this.zzbwS.zzzJ(), new DiscoveredEndpointInfo(this.zzbwS.getServiceId(), this.zzbwS.getEndpointName()));
   }
}
