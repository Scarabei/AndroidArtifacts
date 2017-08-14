package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzajs;

final class zzu implements zzajs {
   // $FF: synthetic field
   private zzac zzLo;
   // $FF: synthetic field
   private zzl zzLh;

   zzu(zzl var1, zzac var2) {
      this.zzLh = var1;
      this.zzLo = var2;
      super();
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      zzu var2 = this;
      synchronized(zzl.zzc(this.zzLh)) {
         zzl.zza(var2.zzLh, 0);
         if (zzl.zzg(var2.zzLh) != null && var2.zzLo != zzl.zzg(var2.zzLh)) {
            zzafr.v("New JS engine is loaded, marking previous one as destroyable.");
            zzl.zzg(var2.zzLh).zzfc();
         }

         zzl.zza(var2.zzLh, var2.zzLo);
      }
   }
}
