package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

public final class zzdw implements MessageApi.SendMessageResult {
   private final Status mStatus;
   private final int zzaLT;

   public zzdw(Status var1, int var2) {
      this.mStatus = var1;
      this.zzaLT = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final int getRequestId() {
      return this.zzaLT;
   }
}
