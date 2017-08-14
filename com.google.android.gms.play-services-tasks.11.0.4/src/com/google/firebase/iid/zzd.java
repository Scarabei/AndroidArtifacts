package com.google.firebase.iid;

import android.content.Intent;
import android.content.BroadcastReceiver.PendingResult;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzd {
   final Intent intent;
   private final PendingResult zzcki;
   private boolean zzckj = false;
   private final ScheduledFuture zzckk;

   zzd(Intent var1, PendingResult var2, ScheduledExecutorService var3) {
      this.intent = var1;
      this.zzcki = var2;
      this.zzckk = var3.schedule(new zze(this, var1), 9500L, TimeUnit.MILLISECONDS);
   }

   final synchronized void finish() {
      if (!this.zzckj) {
         this.zzcki.finish();
         this.zzckk.cancel(false);
         this.zzckj = true;
      }

   }
}
