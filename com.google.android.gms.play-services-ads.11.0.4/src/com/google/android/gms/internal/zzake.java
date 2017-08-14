package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzm;

final class zzake implements Runnable {
   // $FF: synthetic field
   private zzakb zzabJ;

   zzake(zzakb var1) {
      this.zzabJ = var1;
      super();
   }

   public final void run() {
      this.zzabJ.zzJH.zziJ();
      zzm var1;
      if ((var1 = this.zzabJ.zzJH.zziu()) != null) {
         var1.zzfL();
      }

      if (zzakb.zza(this.zzabJ) != null) {
         zzakb.zza(this.zzabJ).zzaT();
         zzakb.zza((zzakb)this.zzabJ, (zzakh)null);
      }

   }
}
