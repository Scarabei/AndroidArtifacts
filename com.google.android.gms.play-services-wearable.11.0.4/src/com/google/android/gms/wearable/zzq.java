package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzaa;

final class zzq implements Runnable {
   // $FF: synthetic field
   private zzaa zzbRE;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzq(WearableListenerService.zzc var1, zzaa var2) {
      this.zzbRA = var1;
      this.zzbRE = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onCapabilityChanged(this.zzbRE);
   }
}
