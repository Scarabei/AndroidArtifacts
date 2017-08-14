package com.google.android.gms.nearby.connection;

public final class DiscoveredEndpointInfo {
   private final String zzbwr;
   private final String zzbwo;

   public DiscoveredEndpointInfo(String var1, String var2) {
      this.zzbwr = var1;
      this.zzbwo = var2;
   }

   public final String getServiceId() {
      return this.zzbwr;
   }

   public final String getEndpointName() {
      return this.zzbwo;
   }
}
