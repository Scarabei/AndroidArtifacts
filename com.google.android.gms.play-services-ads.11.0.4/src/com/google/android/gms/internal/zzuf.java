package com.google.android.gms.internal;

final class zzuf implements Runnable {
   // $FF: synthetic field
   private zzud zzMC;
   // $FF: synthetic field
   private zzue zzMD;

   zzuf(zzue var1, zzud var2) {
      this.zzMD = var1;
      this.zzMC = var2;
      super();
   }

   public final void run() {
      synchronized(zzue.zza(this.zzMD)) {
         if (zzue.zzb(this.zzMD) == -2) {
            zzue.zza(this.zzMD, zzue.zzc(this.zzMD));
            if (zzue.zzd(this.zzMD) == null) {
               this.zzMD.zzo(4);
            } else if (zzue.zze(this.zzMD) && !zzue.zza(this.zzMD, 1)) {
               String var2 = zzue.zzf(this.zzMD);
               zzafr.zzaT((new StringBuilder(56 + String.valueOf(var2).length())).append("Ignoring adapter ").append(var2).append(" as delayed impression is not supported").toString());
               this.zzMD.zzo(2);
            } else {
               this.zzMC.zza((zzui)this.zzMD);
               zzue.zza(this.zzMD, this.zzMC);
            }
         }
      }
   }
}
