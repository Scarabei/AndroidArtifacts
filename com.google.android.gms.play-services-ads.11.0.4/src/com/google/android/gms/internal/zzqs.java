package com.google.android.gms.internal;

import java.util.Map;

final class zzqs implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      if (var2.keySet().contains("start")) {
         var1.zziw().zziW();
      } else if (var2.keySet().contains("stop")) {
         var1.zziw().zziX();
      } else if (var2.keySet().contains("cancel")) {
         var1.zziw().zziY();
      }
   }
}
