package com.google.android.gms.internal;

import java.util.Map;

final class zzabx implements zzrd {
   // $FF: synthetic field
   private zzabu zzUR;

   zzabx(zzabu var1) {
      this.zzUR = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      synchronized(zzabu.zza(this.zzUR)) {
         if (!zzabu.zzb(this.zzUR).isDone()) {
            zzaca var4 = new zzaca(-2, var2);
            if (zzabu.zzc(this.zzUR).equals(var4.getRequestId())) {
               zzabu.zzb(this.zzUR).zzg(var4);
            }
         }
      }
   }
}
