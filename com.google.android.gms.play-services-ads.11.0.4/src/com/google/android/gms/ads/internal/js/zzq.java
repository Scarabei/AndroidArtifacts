package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import java.util.Map;

final class zzq implements zzrd {
   // $FF: synthetic field
   private zza zzLi;
   // $FF: synthetic field
   private zzm zzLj;

   zzq(zzm var1, zza var2) {
      this.zzLj = var1;
      this.zzLi = var2;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      synchronized(zzl.zzc(this.zzLj.zzLh)) {
         if (this.zzLj.zzLg.getStatus() != -1 && this.zzLj.zzLg.getStatus() != 1) {
            zzl.zza(this.zzLj.zzLh, 0);
            zzl.zzd(this.zzLj.zzLh).zzc(this.zzLi);
            this.zzLj.zzLg.zzf(this.zzLi);
            zzl.zza(this.zzLj.zzLh, this.zzLj.zzLg);
            zzafr.v("Successfully loaded JS Engine.");
         }
      }
   }
}
