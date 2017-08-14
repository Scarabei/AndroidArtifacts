package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzdx;

final class zzm implements Runnable {
   // $FF: synthetic field
   private zzdx zzbRB;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzm(WearableListenerService.zzc var1, zzdx var2) {
      this.zzbRA = var1;
      this.zzbRB = var2;
      super();
   }

   public final void run() {
      this.zzbRA.zzbRx.onMessageReceived(this.zzbRB);
   }
}
