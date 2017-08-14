package com.google.android.gms.internal;

import java.util.List;

public final class zzbuf {
   public static int zza(Object var0, List var1) {
      if (var0 == null) {
         return -1;
      } else {
         int var2;
         if ((var2 = var1.indexOf(var0)) >= 0) {
            return var2;
         } else {
            var1.add(var0);
            return var1.size() - 1;
         }
      }
   }
}
