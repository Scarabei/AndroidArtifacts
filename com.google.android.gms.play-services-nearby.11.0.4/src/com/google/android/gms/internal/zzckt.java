package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzckt extends zzclf {
   // $FF: synthetic field
   private zzcnq zzbwP;

   zzckt(zzcks var1, zzcnq var2) {
      this.zzbwP = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.ConnectionRequestListener var3 = (Connections.ConnectionRequestListener)var1;
      var3.onConnectionRequest(this.zzbwP.zzzF(), this.zzbwP.zzzG(), this.zzbwP.zzzI());
   }
}
