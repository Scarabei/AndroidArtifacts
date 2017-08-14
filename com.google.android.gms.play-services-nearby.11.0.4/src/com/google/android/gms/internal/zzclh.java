package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzclh extends zzclf {
   // $FF: synthetic field
   private zzcoc zzbwV;

   zzclh(zzclg var1, zzcoc var2) {
      this.zzbwV = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      PayloadCallback var3 = (PayloadCallback)var1;
      Payload var4;
      if ((var4 = zzcoq.zza(this.zzbwV.zzzK())) == null) {
         Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", this.zzbwV.zzzK().getId()));
      } else {
         var3.onPayloadReceived(this.zzbwV.zzzF(), var4);
      }
   }
}
