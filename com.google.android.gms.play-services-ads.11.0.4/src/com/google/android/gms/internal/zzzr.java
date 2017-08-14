package com.google.android.gms.internal;

final class zzzr implements Runnable {
   // $FF: synthetic field
   private zzzq zzSq;

   zzzr(zzzq var1) {
      this.zzSq = var1;
      super();
   }

   public final void run() {
      synchronized(zzzq.zza(this.zzSq)) {
         if (this.zzSq.zzSp != null) {
            this.zzSq.onStop();
            zzzq.zza(this.zzSq, 2, "Timed out waiting for ad response.");
         }
      }
   }
}
