package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.NodeApi;
import java.util.List;

public final class zzee implements NodeApi.GetConnectedNodesResult {
   private final Status mStatus;
   private final List zzbSY;

   public zzee(Status var1, List var2) {
      this.mStatus = var1;
      this.zzbSY = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final List getNodes() {
      return this.zzbSY;
   }
}
