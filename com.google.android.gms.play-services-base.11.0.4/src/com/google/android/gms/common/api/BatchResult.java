package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
   private final Status mStatus;
   private final PendingResult[] zzaAF;

   BatchResult(Status var1, PendingResult[] var2) {
      this.mStatus = var1;
      this.zzaAF = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Result take(BatchResultToken var1) {
      zzbo.zzb(var1.mId < this.zzaAF.length, "The result token does not belong to this batch");
      return this.zzaAF[var1.mId].await(0L, TimeUnit.MILLISECONDS);
   }
}
