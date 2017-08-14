package com.google.android.gms.internal;

@zzzn
final class zzih implements Runnable {
   private final int zzzI;
   // $FF: synthetic field
   private zzig zzzJ;

   public zzih(zzig var1, int var2) {
      this.zzzJ = var1;
      super();
      this.zzzI = var2;
   }

   public final void run() {
      zzig var1 = this.zzzJ;
      synchronized(this.zzzJ) {
         if (zzig.zza(this.zzzJ) == this.zzzI) {
            zzig.zzb(this.zzzJ);
         }

      }
   }
}
