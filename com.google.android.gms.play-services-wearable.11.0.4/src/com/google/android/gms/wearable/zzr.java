package com.google.android.gms.wearable;

final class zzr implements Runnable {
   // $FF: synthetic field
   private com.google.android.gms.wearable.internal.zzl zzbRF;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzr(WearableListenerService.zzc var1, com.google.android.gms.wearable.internal.zzl var2) {
      this.zzbRA = var1;
      this.zzbRF = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onNotificationReceived(this.zzbRF);
   }
}
