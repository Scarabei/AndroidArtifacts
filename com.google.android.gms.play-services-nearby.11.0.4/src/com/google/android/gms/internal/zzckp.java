package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzckp extends zzclf {
   // $FF: synthetic field
   private zzcno zzbwM;

   zzckp(zzcko var1, zzcno var2) {
      this.zzbwM = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      ConnectionLifecycleCallback var3 = (ConnectionLifecycleCallback)var1;
      var3.onConnectionInitiated(this.zzbwM.zzzF(), new ConnectionInfo(this.zzbwM.zzzG(), this.zzbwM.getAuthenticationToken(), this.zzbwM.zzzH()));
   }
}
