package com.google.android.gms.internal;

final class zzafq implements Runnable {
   // $FF: synthetic field
   private zzafp zzYX;

   zzafq(zzafp var1) {
      this.zzYX = var1;
      super();
   }

   public final void run() {
      zzafp.zza(this.zzYX, Thread.currentThread());
      this.zzYX.zzbd();
   }
}
