package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import java.util.Map;

final class zzad implements zzrd {
   // $FF: synthetic field
   private Runnable zzty;
   // $FF: synthetic field
   private zzac zztz;

   zzad(zzac var1, Runnable var2) {
      this.zztz = var1;
      this.zzty = var2;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      var1.zzb("/appSettingsFetched", this);
      synchronized(zzac.zza(this.zztz)) {
         if (var2 != null && "true".equalsIgnoreCase((String)var2.get("isSuccessful"))) {
            String var4 = (String)var2.get("appSettingsJson");
            zzbs.zzbD().zzn(zzac.zzb(this.zztz), var4);

            try {
               if (this.zzty != null) {
                  this.zzty.run();
               }
            } catch (Throwable var7) {
               zzbs.zzbD().zza(var7, "ConfigLoader.maybeFetchNewAppSettings");
               zzafr.zzc("ConfigLoader post task failed.", var7);
            }
         }

      }
   }
}
