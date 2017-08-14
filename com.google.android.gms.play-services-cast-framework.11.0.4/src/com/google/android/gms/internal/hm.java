package com.google.android.gms.internal;

final class hm extends hp {
   // $FF: synthetic field
   private hl zzbUr;

   hm(hl var1) {
      this.zzbUr = var1;
      super();
   }

   public final void doFrame(long var1) {
      if (!this.zzbUr.zzb(this.zzbUr.zzbUn) && !this.zzbUr.zzbUn.isStarted()) {
         if (hl.zza(this.zzbUr) != null) {
            hl.zza(this.zzbUr).run();
         }

         this.zzbUr.zzbUn.start();
      }

   }
}
