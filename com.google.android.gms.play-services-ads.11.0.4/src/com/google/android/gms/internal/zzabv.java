package com.google.android.gms.internal;

import java.util.Map;

final class zzabv implements zzrd {
   // $FF: synthetic field
   private zzabu zzUR;

   zzabv(zzabu var1) {
      this.zzUR = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      synchronized(zzabu.zza(this.zzUR)) {
         if (!zzabu.zzb(this.zzUR).isDone()) {
            if (zzabu.zzc(this.zzUR).equals(var2.get("request_id"))) {
               zzaca var4;
               String var5 = String.valueOf((var4 = new zzaca(1, var2)).getType());
               String var6 = String.valueOf(var4.zzgH());
               zzafr.zzaT((new StringBuilder(24 + String.valueOf(var5).length() + String.valueOf(var6).length())).append("Invalid ").append(var5).append(" request error: ").append(var6).toString());
               zzabu.zzb(this.zzUR).zzg(var4);
            }
         }
      }
   }
}
