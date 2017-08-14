package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

public final class zzy implements CapabilityApi.GetCapabilityResult {
   private final CapabilityInfo zzbSc;
   private final Status mStatus;

   public zzy(Status var1, CapabilityInfo var2) {
      this.mStatus = var1;
      this.zzbSc = var2;
   }

   public final CapabilityInfo getCapability() {
      return this.zzbSc;
   }

   public final Status getStatus() {
      return this.mStatus;
   }
}
