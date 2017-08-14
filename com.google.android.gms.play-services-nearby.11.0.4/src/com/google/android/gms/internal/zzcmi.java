package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzcmi implements Connections.StartAdvertisingResult {
   // $FF: synthetic field
   private Status zzakB;

   zzcmi(zzcmh var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final String getLocalEndpointName() {
      return null;
   }

   public final Status getStatus() {
      return this.zzakB;
   }
}
