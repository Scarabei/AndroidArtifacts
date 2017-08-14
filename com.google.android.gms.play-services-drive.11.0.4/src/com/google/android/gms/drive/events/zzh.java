package com.google.android.gms.drive.events;

import android.os.Looper;
import com.google.android.gms.internal.zzbng;
import java.util.concurrent.CountDownLatch;

final class zzh extends Thread {
   // $FF: synthetic field
   private CountDownLatch zzaNh;
   // $FF: synthetic field
   private DriveEventService zzaNi;

   zzh(DriveEventService var1, CountDownLatch var2) {
      this.zzaNi = var1;
      this.zzaNh = var2;
      super();
   }

   public final void run() {
      try {
         Looper.prepare();
         this.zzaNi.zzaNf = this.zzaNi.new zza();
         this.zzaNi.zzaNg = false;
         this.zzaNh.countDown();
         zzbng.zzx("DriveEventService", "Bound and starting loop");
         Looper.loop();
         zzbng.zzx("DriveEventService", "Finished loop");
      } finally {
         if (DriveEventService.zzb(this.zzaNi) != null) {
            DriveEventService.zzb(this.zzaNi).countDown();
         }

      }

   }
}
