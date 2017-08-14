package com.google.android.gms.wearable.internal;

import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.wearable.CapabilityApi;

final class zzgg implements zzbdz {
   // $FF: synthetic field
   private zzaa zzbTB;

   zzgg(zzaa var1) {
      this.zzbTB = var1;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      CapabilityApi.CapabilityListener var3 = (CapabilityApi.CapabilityListener)var1;
      var3.onCapabilityChanged(this.zzbTB);
   }
}
