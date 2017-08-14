package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Iterator;

public final class zzp {
   public static void zza(StringBuilder var0, HashMap var1) {
      var0.append("{");
      boolean var2 = true;
      Iterator var3 = var1.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         if (!var2) {
            var0.append(",");
         } else {
            var2 = false;
         }

         String var5 = (String)var1.get(var4);
         var0.append("\"").append(var4).append("\":");
         if (var5 == null) {
            var0.append("null");
         } else {
            var0.append("\"").append(var5).append("\"");
         }
      }

      var0.append("}");
   }
}
