package com.google.android.gms.internal;

final class zzamb implements Runnable {
   // $FF: synthetic field
   private String zzafF;
   // $FF: synthetic field
   private Runnable zzafG;
   // $FF: synthetic field
   private zzaly zzafD;

   zzamb(zzaly var1, String var2, Runnable var3) {
      this.zzafD = var1;
      this.zzafF = var2;
      this.zzafG = var3;
      super();
   }

   public final void run() {
      zzaly.zza(this.zzafD).zzbw(this.zzafF);
      if (this.zzafG != null) {
         this.zzafG.run();
      }

   }
}
