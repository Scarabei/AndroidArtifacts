package com.google.android.gms.nearby.connection;

public final class ConnectionInfo {
   private final String zzbwo;
   private final String zzbwp;
   private final boolean zzbwq;

   public ConnectionInfo(String var1, String var2, boolean var3) {
      this.zzbwo = var1;
      this.zzbwp = var2;
      this.zzbwq = var3;
   }

   public final String getEndpointName() {
      return this.zzbwo;
   }

   public final String getAuthenticationToken() {
      return this.zzbwp;
   }

   public final boolean isIncomingConnection() {
      return this.zzbwq;
   }
}
