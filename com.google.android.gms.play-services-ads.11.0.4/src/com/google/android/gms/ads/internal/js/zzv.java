package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzajq;

final class zzv implements zzajq {
   // $FF: synthetic field
   private zzac zzLo;
   // $FF: synthetic field
   private zzl zzLh;

   zzv(zzl var1, zzac var2) {
      this.zzLh = var1;
      this.zzLo = var2;
      super();
   }

   public final void run() {
      synchronized(zzl.zzc(this.zzLh)) {
         zzl.zza(this.zzLh, 1);
         zzafr.v("Failed loading new engine. Marking new engine destroyable.");
         this.zzLo.zzfc();
      }
   }
}
