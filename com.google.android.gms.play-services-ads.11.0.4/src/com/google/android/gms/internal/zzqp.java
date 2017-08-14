package com.google.android.gms.internal;

import java.util.Map;

final class zzqp implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("tx");
      String var4 = (String)var2.get("ty");
      String var5 = (String)var2.get("td");

      try {
         int var6 = Integer.parseInt(var3);
         int var7 = Integer.parseInt(var4);
         int var8 = Integer.parseInt(var5);
         zzcu var9;
         if ((var9 = var1.zziy()) != null) {
            var9.zzB().zza(var6, var7, var8);
         }

      } catch (NumberFormatException var10) {
         zzafr.zzaT("Could not parse touch parameters from gmsg.");
      }
   }
}
