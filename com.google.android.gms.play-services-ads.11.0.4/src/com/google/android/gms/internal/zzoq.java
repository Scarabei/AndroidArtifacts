package com.google.android.gms.internal;

final class zzoq implements Runnable {
   // $FF: synthetic field
   private zzoc zzIA;
   // $FF: synthetic field
   private zzop zzIB;

   zzoq(zzop var1, zzoc var2) {
      this.zzIB = var1;
      this.zzIA = var2;
      super();
   }

   public final void run() {
      zzaka var1 = null;

      try {
         var1 = this.zzIA.zzes();
      } catch (Exception var3) {
         zzafr.zzb("Error obtaining overlay.", var3);
      }

      if (var1 != null && this.zzIB.zzss != null) {
         this.zzIB.zzss.addView(var1.getView());
      }

      if (!(this.zzIA instanceof zznx)) {
         zzop.zza(this.zzIB, this.zzIA);
      }

   }
}
