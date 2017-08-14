package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;

public final class zzbt implements DataApi.DeleteDataItemsResult {
   private final Status mStatus;
   private final int zzbSC;

   public zzbt(Status var1, int var2) {
      this.mStatus = var1;
      this.zzbSC = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final int getNumDeleted() {
      return this.zzbSC;
   }
}
