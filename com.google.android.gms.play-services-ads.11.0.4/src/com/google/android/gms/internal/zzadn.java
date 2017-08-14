package com.google.android.gms.internal;

final class zzadn implements Runnable {
   // $FF: synthetic field
   private zzir zztY;
   // $FF: synthetic field
   private zzut zzWF;
   // $FF: synthetic field
   private zzadm zzWG;

   zzadn(zzadm var1, zzir var2, zzut var3) {
      this.zzWG = var1;
      this.zztY = var2;
      this.zzWF = var3;
      super();
   }

   public final void run() {
      zzadm.zza(this.zzWG, this.zztY, this.zzWF);
   }
}
