package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;

public final class zzbud {
   public static boolean zza(List var0, List var1) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         Iterator var2 = var0.iterator();

         Object var3;
         do {
            if (!var2.hasNext()) {
               return true;
            }

            var3 = var2.next();
         } while(var1.contains(var3));

         return false;
      }
   }
}
