package com.google.android.gms.wearable;

import java.util.List;

final class zzp implements Runnable {
   // $FF: synthetic field
   private List zzbRD;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzp(WearableListenerService.zzc var1, List var2) {
      this.zzbRA = var1;
      this.zzbRD = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onConnectedNodes(this.zzbRD);
   }
}
