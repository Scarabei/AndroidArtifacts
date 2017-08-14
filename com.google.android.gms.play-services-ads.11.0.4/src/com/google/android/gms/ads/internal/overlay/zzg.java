package com.google.android.gms.ads.internal.overlay;

final class zzg implements Runnable {
   // $FF: synthetic field
   private String zzOB;
   // $FF: synthetic field
   private String zzOC;
   // $FF: synthetic field
   private zzd zzOA;

   zzg(zzd var1, String var2, String var3) {
      this.zzOA = var1;
      this.zzOB = var2;
      this.zzOC = var3;
      super();
   }

   public final void run() {
      if (zzd.zza(this.zzOA) != null) {
         zzd.zza(this.zzOA).zzj(this.zzOB, this.zzOC);
      }

   }
}
