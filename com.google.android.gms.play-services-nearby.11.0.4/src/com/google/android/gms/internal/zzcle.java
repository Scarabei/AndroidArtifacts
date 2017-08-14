package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcle extends zzclf {
   // $FF: synthetic field
   private zzcnw zzbwO;

   zzcle(zzclc var1, zzcnw var2) {
      this.zzbwO = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.MessageListener var3 = (Connections.MessageListener)var1;
      var3.onDisconnected(this.zzbwO.zzzF());
   }
}
