package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzcky extends zzclf {
   // $FF: synthetic field
   private zzcoa zzbwT;

   zzcky(zzckw var1, zzcoa var2) {
      this.zzbwT = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      EndpointDiscoveryCallback var3 = (EndpointDiscoveryCallback)var1;
      var3.onEndpointLost(this.zzbwT.zzzJ());
   }
}
