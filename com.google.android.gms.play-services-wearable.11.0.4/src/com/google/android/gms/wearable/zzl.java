package com.google.android.gms.wearable;

import com.google.android.gms.common.data.DataHolder;

final class zzl implements Runnable {
   // $FF: synthetic field
   private DataHolder zzbRz;
   // $FF: synthetic field
   private WearableListenerService.zzc zzbRA;

   zzl(WearableListenerService.zzc var1, DataHolder var2) {
      this.zzbRA = var1;
      this.zzbRz = var2;
      super();
   }

   public final void run() {
      DataEventBuffer var1 = new DataEventBuffer(this.zzbRz);

      try {
         this.zzbRA.zzbRx.onDataChanged(var1);
      } finally {
         var1.release();
      }

   }
}
