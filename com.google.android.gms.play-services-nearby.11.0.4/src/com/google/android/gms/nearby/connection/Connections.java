package com.google.android.gms.nearby.connection;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public interface Connections {
   /** @deprecated */
   @Deprecated
   long DURATION_INDEFINITE = 0L;
   /** @deprecated */
   @Deprecated
   int MAX_UNRELIABLE_MESSAGE_LEN = 1168;
   /** @deprecated */
   @Deprecated
   int MAX_RELIABLE_MESSAGE_LEN = 4096;
   int MAX_BYTES_DATA_SIZE = 32768;

   PendingResult startAdvertising(GoogleApiClient var1, String var2, String var3, ConnectionLifecycleCallback var4, AdvertisingOptions var5);

   void stopAdvertising(GoogleApiClient var1);

   PendingResult startDiscovery(GoogleApiClient var1, String var2, EndpointDiscoveryCallback var3, DiscoveryOptions var4);

   void stopDiscovery(GoogleApiClient var1);

   PendingResult requestConnection(GoogleApiClient var1, @Nullable String var2, String var3, ConnectionLifecycleCallback var4);

   PendingResult acceptConnection(GoogleApiClient var1, String var2, PayloadCallback var3);

   PendingResult rejectConnection(GoogleApiClient var1, String var2);

   PendingResult sendPayload(GoogleApiClient var1, String var2, Payload var3);

   PendingResult sendPayload(GoogleApiClient var1, List var2, Payload var3);

   void disconnectFromEndpoint(GoogleApiClient var1, String var2);

   void stopAllEndpoints(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult startAdvertising(GoogleApiClient var1, String var2, AppMetadata var3, long var4, Connections.ConnectionRequestListener var6);

   /** @deprecated */
   @Deprecated
   PendingResult startDiscovery(GoogleApiClient var1, String var2, long var3, Connections.EndpointDiscoveryListener var5);

   /** @deprecated */
   @Deprecated
   void stopDiscovery(GoogleApiClient var1, String var2);

   /** @deprecated */
   @Deprecated
   PendingResult sendConnectionRequest(GoogleApiClient var1, String var2, String var3, byte[] var4, Connections.ConnectionResponseCallback var5, Connections.MessageListener var6);

   /** @deprecated */
   @Deprecated
   PendingResult acceptConnectionRequest(GoogleApiClient var1, String var2, byte[] var3, Connections.MessageListener var4);

   /** @deprecated */
   @Deprecated
   PendingResult rejectConnectionRequest(GoogleApiClient var1, String var2);

   /** @deprecated */
   @Deprecated
   void sendReliableMessage(GoogleApiClient var1, String var2, byte[] var3);

   /** @deprecated */
   @Deprecated
   void sendReliableMessage(GoogleApiClient var1, List var2, byte[] var3);

   /** @deprecated */
   @Deprecated
   void sendUnreliableMessage(GoogleApiClient var1, String var2, byte[] var3);

   /** @deprecated */
   @Deprecated
   void sendUnreliableMessage(GoogleApiClient var1, List var2, byte[] var3);

   /** @deprecated */
   @Deprecated
   public abstract static class ConnectionRequestListener {
      public void onConnectionRequest(String var1, String var2, byte[] var3) {
      }
   }

   /** @deprecated */
   @Deprecated
   public interface MessageListener {
      /** @deprecated */
      @Deprecated
      void onMessageReceived(String var1, byte[] var2, boolean var3);

      /** @deprecated */
      @Deprecated
      void onDisconnected(String var1);
   }

   /** @deprecated */
   @Deprecated
   public interface ConnectionResponseCallback {
      void onConnectionResponse(String var1, Status var2, byte[] var3);
   }

   /** @deprecated */
   @Deprecated
   public abstract static class EndpointDiscoveryListener {
      public void onEndpointFound(String var1, String var2, String var3) {
      }

      public abstract void onEndpointLost(String var1);
   }

   public interface StartAdvertisingResult extends Result {
      String getLocalEndpointName();
   }
}
