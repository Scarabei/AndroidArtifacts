package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

public final class FreezableUtils {
   public static ArrayList freeze(ArrayList var0) {
      ArrayList var1 = new ArrayList(var0.size());
      int var2 = 0;

      for(int var3 = var0.size(); var2 < var3; ++var2) {
         var1.add(((Freezable)var0.get(var2)).freeze());
      }

      return var1;
   }

   public static ArrayList freeze(Freezable[] var0) {
      ArrayList var1 = new ArrayList(var0.length);

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1.add(var0[var2].freeze());
      }

      return var1;
   }

   public static ArrayList freezeIterable(Iterable var0) {
      ArrayList var1 = new ArrayList();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         Freezable var3 = (Freezable)var2.next();
         var1.add(var3.freeze());
      }

      return var1;
   }
}
