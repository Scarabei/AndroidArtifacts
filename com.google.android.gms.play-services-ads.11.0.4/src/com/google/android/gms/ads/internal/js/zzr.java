package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzais;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import java.util.Map;

final class zzr implements zzrd {
   // $FF: synthetic field
   private zza zzLi;
   // $FF: synthetic field
   private zzais zzLm;
   // $FF: synthetic field
   private zzm zzLj;

   zzr(zzm var1, zza var2, zzais var3) {
      this.zzLj = var1;
      this.zzLi = var2;
      this.zzLm = var3;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      synchronized(zzl.zzc(this.zzLj.zzLh)) {
         zzafr.zzaS("JS Engine is requesting an update");
         if (zzl.zze(this.zzLj.zzLh) == 0) {
            zzafr.zzaS("Starting reload.");
            zzl.zza(this.zzLj.zzLh, 2);
            this.zzLj.zzLh.zza(this.zzLj.zzLf);
         }

         this.zzLi.zzb("/requestReload", (zzrd)this.zzLm.get());
      }
   }
}
