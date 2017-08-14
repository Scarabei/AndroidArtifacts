package com.google.android.gms.internal;

import java.util.Map;

final class zzrb implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var10001 = String.valueOf((String)var2.get("string"));
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Received log message: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Received log message: ");
      }

      zzafr.zzaS(var10000);
   }
}
