package com.google.android.gms.nearby.connection;

public abstract class ConnectionLifecycleCallback {
   public abstract void onConnectionInitiated(String var1, ConnectionInfo var2);

   public abstract void onConnectionResult(String var1, ConnectionResolution var2);

   public abstract void onDisconnected(String var1);
}
