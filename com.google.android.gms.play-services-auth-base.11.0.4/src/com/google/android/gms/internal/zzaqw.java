package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaqw implements Result {
   private final Status mStatus;

   public zzaqw(Status var1) {
      this.mStatus = var1;
   }

   public final Status getStatus() {
      return this.mStatus;
   }
}
