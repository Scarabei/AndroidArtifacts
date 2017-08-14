package com.google.android.gms.ads.internal.overlay;

final class zzl implements Runnable {
   // $FF: synthetic field
   private zzd zzOA;

   zzl(zzd var1) {
      this.zzOA = var1;
      super();
   }

   public final void run() {
      if (zzd.zza(this.zzOA) != null) {
         zzd.zza(this.zzOA).onPaused();
      }

   }
}
