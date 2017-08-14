package com.google.android.gms.internal;

final class zzzs implements Runnable {
   // $FF: synthetic field
   private zzajp zzSr;
   // $FF: synthetic field
   private zzzq zzSq;

   zzzs(zzzq var1, zzajp var2) {
      this.zzSq = var1;
      this.zzSr = var2;
      super();
   }

   public final void run() {
      synchronized(zzzq.zza(this.zzSq)) {
         this.zzSq.zzSp = this.zzSq.zza(zzzq.zzb(this.zzSq).zzvT, this.zzSr);
         if (this.zzSq.zzSp == null) {
            zzzq.zza(this.zzSq, 0, "Could not start the ad request service.");
            zzagz.zzZr.removeCallbacks(zzzq.zzc(this.zzSq));
         }

      }
   }
}
