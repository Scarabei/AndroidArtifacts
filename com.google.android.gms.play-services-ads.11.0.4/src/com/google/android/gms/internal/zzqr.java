package com.google.android.gms.internal;

import java.util.Map;

final class zzqr implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("action");
      if ("pause".equals(var3)) {
         var1.zzaJ();
      } else {
         if ("resume".equals(var3)) {
            var1.zzaK();
         }

      }
   }
}
