package com.google.android.gms.nearby.connection;

public abstract class EndpointDiscoveryCallback {
   public abstract void onEndpointFound(String var1, DiscoveredEndpointInfo var2);

   public abstract void onEndpointLost(String var1);
}
