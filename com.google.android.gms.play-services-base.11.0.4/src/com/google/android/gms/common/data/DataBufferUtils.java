package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

public final class DataBufferUtils {
   public static ArrayList freezeAndClose(DataBuffer var0) {
      ArrayList var1 = new ArrayList(var0.getCount());

      try {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            Freezable var3 = (Freezable)var2.next();
            var1.add(var3.freeze());
         }
      } finally {
         var0.close();
      }

      return var1;
   }

   public static boolean hasNextPage(DataBuffer var0) {
      Bundle var1;
      return (var1 = var0.zzqN()) != null && var1.getString("next_page_token") != null;
   }

   public static boolean hasPrevPage(DataBuffer var0) {
      Bundle var1;
      return (var1 = var0.zzqN()) != null && var1.getString("prev_page_token") != null;
   }

   public static boolean hasData(DataBuffer var0) {
      return var0 != null && var0.getCount() > 0;
   }
}
