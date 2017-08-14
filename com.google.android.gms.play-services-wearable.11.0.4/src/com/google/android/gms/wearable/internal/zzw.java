package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityInfo;
import java.util.Set;

public final class zzw implements CapabilityInfo {
   private final String mName;
   private final Set zzbSa;

   private zzw(String var1, Set var2) {
      this.mName = var1;
      this.zzbSa = var2;
   }

   public zzw(CapabilityInfo var1) {
      this(var1.getName(), var1.getNodes());
   }

   public final String getName() {
      return this.mName;
   }

   public final Set getNodes() {
      return this.zzbSa;
   }
}
