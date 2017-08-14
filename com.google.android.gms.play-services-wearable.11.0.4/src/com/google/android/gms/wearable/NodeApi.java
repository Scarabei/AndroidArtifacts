package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.util.List;

public interface NodeApi {
   PendingResult getLocalNode(GoogleApiClient var1);

   PendingResult getConnectedNodes(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   PendingResult addListener(GoogleApiClient var1, NodeApi.NodeListener var2);

   /** @deprecated */
   @Deprecated
   PendingResult removeListener(GoogleApiClient var1, NodeApi.NodeListener var2);

   /** @deprecated */
   @Deprecated
   public interface NodeListener {
      /** @deprecated */
      @Deprecated
      void onPeerConnected(Node var1);

      /** @deprecated */
      @Deprecated
      void onPeerDisconnected(Node var1);
   }

   public interface GetConnectedNodesResult extends Result {
      List getNodes();
   }

   public interface GetLocalNodeResult extends Result {
      Node getNode();
   }
}
