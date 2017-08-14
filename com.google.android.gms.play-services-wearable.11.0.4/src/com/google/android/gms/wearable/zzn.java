package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzeg;

final class zzn implements Runnable {
   // $FF: synthetic field
   private zzeg zzbRC;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzn(WearableListenerService.zzc var1, zzeg var2) {
      this.zzbRA = var1;
      this.zzbRC = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onPeerConnected(this.zzbRC);
   }
}
