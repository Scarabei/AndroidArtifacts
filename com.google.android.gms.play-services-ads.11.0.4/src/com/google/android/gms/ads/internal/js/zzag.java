package com.google.android.gms.ads.internal.js;

final class zzag implements Runnable {
   // $FF: synthetic field
   private zza zzLy;
   // $FF: synthetic field
   private zzaf zzLz;

   zzag(zzaf var1, zza var2) {
      this.zzLz = var1;
      this.zzLy = var2;
      super();
   }

   public final void run() {
      zzac.zza(this.zzLz.zzLx).zzc(this.zzLy);
      this.zzLy.destroy();
   }
}
