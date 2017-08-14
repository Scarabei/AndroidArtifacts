package com.google.android.gms.internal;

final class zzaxv implements Runnable {
   // $FF: synthetic field
   private zzaxt zzaxJ;

   private zzaxv(zzaxt var1) {
      this.zzaxJ = var1;
      super();
   }

   public final void run() {
      this.zzaxJ.zzaxI = false;
      long var1 = this.zzaxJ.zzvw.elapsedRealtime();
      boolean var3 = this.zzaxJ.zzz(var1);
      this.zzaxJ.zzZ(var3);
   }

   // $FF: synthetic method
   zzaxv(zzaxt var1, zzaxu var2) {
      this(var1);
   }
}
