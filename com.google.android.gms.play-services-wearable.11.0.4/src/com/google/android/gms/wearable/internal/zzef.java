package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;

public final class zzef implements NodeApi.GetLocalNodeResult {
   private final Status mStatus;
   private final Node zzbSZ;

   public zzef(Status var1, Node var2) {
      this.mStatus = var1;
      this.zzbSZ = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Node getNode() {
      return this.zzbSZ;
   }
}
