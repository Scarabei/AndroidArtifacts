package com.google.android.gms.wearable.internal;

import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.wearable.NodeApi;

final class zzge implements zzbdz {
   // $FF: synthetic field
   private zzeg zzbRC;

   zzge(zzeg var1) {
      this.zzbRC = var1;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      NodeApi.NodeListener var3 = (NodeApi.NodeListener)var1;
      var3.onPeerDisconnected(this.zzbRC);
   }
}
