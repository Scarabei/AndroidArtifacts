package com.google.android.gms.wearable;

final class zzs implements Runnable {
   // $FF: synthetic field
   private com.google.android.gms.wearable.internal.zzi zzbRG;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzs(WearableListenerService.zzc var1, com.google.android.gms.wearable.internal.zzi var2) {
      this.zzbRA = var1;
      this.zzbRG = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onEntityUpdate(this.zzbRG);
   }
}
