package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import java.util.Map;

public final class zzx implements CapabilityApi.GetAllCapabilitiesResult {
   private final Status mStatus;
   private final Map zzbSb;

   public zzx(Status var1, Map var2) {
      this.mStatus = var1;
      this.zzbSb = var2;
   }

   public final Map getAllCapabilities() {
      return this.zzbSb;
   }

   public final Status getStatus() {
      return this.mStatus;
   }
}
