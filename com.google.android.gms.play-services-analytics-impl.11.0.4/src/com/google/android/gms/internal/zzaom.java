package com.google.android.gms.internal;

final class zzaom implements Runnable {
   // $FF: synthetic field
   private zzaol zzaiG;

   zzaom(zzaol var1) {
      this.zzaiG = var1;
      super();
   }

   public final void run() {
      if (zzaok.zza(this.zzaiG.zzaiF).callServiceStopSelfResult(this.zzaiG.zzadw)) {
         this.zzaiG.zzadv.zzbo("Local AnalyticsService processed last dispatch request");
      }

   }
}
