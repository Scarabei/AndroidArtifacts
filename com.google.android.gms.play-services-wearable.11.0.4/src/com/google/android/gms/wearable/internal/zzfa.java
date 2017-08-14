package com.google.android.gms.wearable.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzfa {
   private static Map zzN(List var0) {
      HashMap var1 = new HashMap(var0.size() << 1);
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         zzaa var3 = (zzaa)var2.next();
         var1.put(var3.getName(), new zzw(var3));
      }

      return var1;
   }

   // $FF: synthetic method
   static Map zzO(List var0) {
      return zzN(var0);
   }
}
