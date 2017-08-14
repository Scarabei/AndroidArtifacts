package com.google.android.gms.internal;

import java.util.Map;

public final class zzabh implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("request_id");
      String var4 = (String)var2.get("errors");
      String var10001 = String.valueOf(var4);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Invalid request: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Invalid request: ");
      }

      zzafr.zzaT(var10000);
      zzaaz.zzgD().zzT(var3);
   }
}
