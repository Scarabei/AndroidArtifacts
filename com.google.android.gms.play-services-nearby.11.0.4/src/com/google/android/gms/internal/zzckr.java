package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzckr extends zzclf {
   // $FF: synthetic field
   private zzcnw zzbwO;

   zzckr(zzcko var1, zzcnw var2) {
      this.zzbwO = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      ConnectionLifecycleCallback var3 = (ConnectionLifecycleCallback)var1;
      var3.onDisconnected(this.zzbwO.zzzF());
   }
}
