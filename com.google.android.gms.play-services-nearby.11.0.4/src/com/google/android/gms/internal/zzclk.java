package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzclk implements Connections.StartAdvertisingResult {
   private final Status zzajl;
   private final String zzbwY;

   zzclk(Status var1, String var2) {
      this.zzajl = var1;
      this.zzbwY = var2;
   }

   public final Status getStatus() {
      return this.zzajl;
   }

   public final String getLocalEndpointName() {
      return this.zzbwY;
   }
}
