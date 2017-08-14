package com.google.android.gms.ads.internal.overlay;

final class zzi implements Runnable {
   // $FF: synthetic field
   private int zzOD;
   // $FF: synthetic field
   private int zzOE;
   // $FF: synthetic field
   private zzd zzOA;

   zzi(zzd var1, int var2, int var3) {
      this.zzOA = var1;
      this.zzOD = var2;
      this.zzOE = var3;
      super();
   }

   public final void run() {
      if (zzd.zza(this.zzOA) != null) {
         zzd.zza(this.zzOA).zzd(this.zzOD, this.zzOE);
      }

   }
}
