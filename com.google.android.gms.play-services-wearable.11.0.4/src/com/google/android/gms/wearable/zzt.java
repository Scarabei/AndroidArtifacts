package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzai;

final class zzt implements Runnable {
   // $FF: synthetic field
   private zzai zzbRH;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzt(WearableListenerService.zzc var1, zzai var2) {
      this.zzbRA = var1;
      this.zzbRH = var2;
      super();
   }

   public final void run() {
      this.zzbRH.zza(this.zzbRA.zzbRx);
   }
}
