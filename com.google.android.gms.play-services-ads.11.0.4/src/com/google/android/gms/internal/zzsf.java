package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzc;
import java.util.Map;

@zzzn
public final class zzsf implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      com.google.android.gms.ads.internal.zzbs.zzbW();
      if (var2.containsKey("abort")) {
         if (!zzsa.zze(var1)) {
            zzafr.zzaT("Precache abort but no preload task running.");
         }

      } else {
         String var3;
         if ((var3 = (String)var2.get("src")) == null) {
            zzafr.zzaT("Precache video action is missing the src parameter.");
         } else {
            int var4;
            try {
               var4 = Integer.parseInt((String)var2.get("player"));
            } catch (NumberFormatException var7) {
               var4 = 0;
            }

            String var5 = var2.containsKey("mimetype") ? (String)var2.get("mimetype") : "";
            if (zzsa.zzf(var1)) {
               zzafr.zzaT("Precache task already running.");
            } else {
               zzc.zzr(var1.zzak());
               zzsb var6 = var1.zzak().zztm.zza(var1, var4, var5);
               (new zzry(var1, var6, var3)).zzhL();
            }
         }
      }
   }
}
