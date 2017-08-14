package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItem;

public final class zzbs implements DataApi.DataItemResult {
   private final Status mStatus;
   private final DataItem zzbSB;

   public zzbs(Status var1, DataItem var2) {
      this.mStatus = var1;
      this.zzbSB = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final DataItem getDataItem() {
      return this.zzbSB;
   }
}
