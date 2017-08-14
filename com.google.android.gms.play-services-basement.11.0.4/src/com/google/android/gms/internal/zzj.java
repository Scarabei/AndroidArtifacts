package com.google.android.gms.internal;

final class zzj implements Runnable {
   private final zzp zzt;
   private final zzt zzu;
   private final Runnable zzv;

   public zzj(zzh var1, zzp var2, zzt var3, Runnable var4) {
      this.zzt = var2;
      this.zzu = var3;
      this.zzv = var4;
   }

   public final void run() {
      if (this.zzu.zzaf == null) {
         this.zzt.zza(this.zzu.result);
      } else {
         this.zzt.zzb(this.zzu.zzaf);
      }

      if (this.zzu.zzag) {
         this.zzt.zzb("intermediate-response");
      } else {
         this.zzt.zzc("done");
      }

      if (this.zzv != null) {
         this.zzv.run();
      }

   }
}
