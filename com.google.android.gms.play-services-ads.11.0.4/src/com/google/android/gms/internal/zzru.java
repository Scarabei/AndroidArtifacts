package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

@zzzn
public final class zzru implements zzrd {
   private final zzrv zzJJ;

   public zzru(zzrv var1) {
      this.zzJJ = var1;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("action");
      if ("grant".equals(var3)) {
         Map var5 = var2;
         zzaee var6 = null;

         try {
            int var7 = Integer.parseInt((String)var5.get("amount"));
            String var8;
            if (!TextUtils.isEmpty(var8 = (String)var5.get("type"))) {
               var6 = new zzaee(var8, var7);
            }
         } catch (NumberFormatException var9) {
            zzafr.zzc("Unable to parse reward amount.", var9);
         }

         this.zzJJ.zzb(var6);
      } else {
         if ("video_start".equals(var3)) {
            this.zzJJ.zzbc();
         }

      }
   }
}
