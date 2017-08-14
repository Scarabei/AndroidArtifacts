package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.Payload;

final class zzcld extends zzclf {
   // $FF: synthetic field
   private zzcoc zzbwV;

   zzcld(zzclc var1, zzcoc var2) {
      this.zzbwV = var2;
      super((zzckn)null);
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      Connections.MessageListener var3 = (Connections.MessageListener)var1;
      Payload var4;
      if ((var4 = zzcoq.zza(this.zzbwV.zzzK())) == null) {
         Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", this.zzbwV.zzzK().getId()));
      } else {
         if (var4.getType() == 1) {
            var3.onMessageReceived(this.zzbwV.zzzF(), var4.asBytes(), this.zzbwV.zzzL());
         }

      }
   }
}
