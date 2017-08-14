package com.google.android.gms.internal;

import java.util.Map;

final class zzabw implements zzrd {
   // $FF: synthetic field
   private zzabu zzUR;

   zzabw(zzabu var1) {
      this.zzUR = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      synchronized(zzabu.zza(this.zzUR)) {
         if (!zzabu.zzb(this.zzUR).isDone()) {
            zzaca var4 = new zzaca(-2, var2);
            if (zzabu.zzc(this.zzUR).equals(var4.getRequestId())) {
               String var5;
               if ((var5 = var4.getUrl()) == null) {
                  zzafr.zzaT("URL missing in loadAdUrl GMSG.");
               } else {
                  if (var5.contains("%40mediation_adapters%40")) {
                     String var6 = zzafo.zzb(var1.getContext(), (String)var2.get("check_adapters"), zzabu.zzd(this.zzUR));
                     var5 = var5.replaceAll("%40mediation_adapters%40", var6);
                     var4.setUrl(var5);
                     String var10001 = String.valueOf(var5);
                     String var10000;
                     if (var10001.length() != 0) {
                        var10000 = "Ad request URL modified to ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Ad request URL modified to ");
                     }

                     zzafr.v(var10000);
                  }

                  zzabu.zzb(this.zzUR).zzg(var4);
               }
            }
         }
      }
   }
}
