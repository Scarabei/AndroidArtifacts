package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzcli extends zzclf {
   // $FF: synthetic field
   private zzcoe zzbwX;

   zzcli(zzclg var1, zzcoe var2) {
      this.zzbwX = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      PayloadCallback var3 = (PayloadCallback)var1;
      var3.onPayloadTransferUpdate(this.zzbwX.zzzF(), this.zzbwX.zzzM());
   }
}
