package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzm;
import java.util.Map;

final class zzqy implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      zzm var3;
      if ((var3 = var1.zziu()) != null) {
         var3.close();
      } else {
         zzm var4;
         if ((var4 = var1.zziv()) != null) {
            var4.close();
         } else {
            zzafr.zzaT("A GMSG tried to close something that wasn't an overlay.");
         }
      }
   }
}
