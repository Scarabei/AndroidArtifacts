package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public abstract class zzj {
   public abstract void zzb(zzj var1);

   public static String zzk(Map var0) {
      return zza(var0, 1);
   }

   public static String zzh(Object var0) {
      return zza(var0, 0);
   }

   private static String zza(Object var0, int var1) {
      if (var1 > 10) {
         return "ERROR: Recursive toString calls";
      } else if (var0 == null) {
         return "";
      } else if (var0 instanceof String) {
         return TextUtils.isEmpty((String)var0) ? "" : var0.toString();
      } else if (var0 instanceof Integer) {
         return ((Integer)var0).intValue() == 0 ? "" : var0.toString();
      } else if (var0 instanceof Long) {
         return ((Long)var0).longValue() == 0L ? "" : var0.toString();
      } else if (var0 instanceof Double) {
         return ((Double)var0).doubleValue() == 0.0D ? "" : var0.toString();
      } else if (var0 instanceof Boolean) {
         return !((Boolean)var0).booleanValue() ? "" : var0.toString();
      } else {
         StringBuffer var2;
         if (var0 instanceof List) {
            var2 = new StringBuffer();
            if (var1 > 0) {
               var2.append("[");
            }

            List var10 = (List)var0;
            int var11 = var2.length();

            Object var13;
            for(Iterator var12 = var10.iterator(); var12.hasNext(); var2.append(zza(var13, var1 + 1))) {
               var13 = var12.next();
               if (var2.length() > var11) {
                  var2.append(", ");
               }
            }

            if (var1 > 0) {
               var2.append("]");
            }

            return var2.toString();
         } else if (var0 instanceof Map) {
            var2 = new StringBuffer();
            TreeMap var3 = new TreeMap((Map)var0);
            boolean var4 = false;
            int var5 = 0;
            Iterator var6 = var3.entrySet().iterator();

            while(var6.hasNext()) {
               Entry var7;
               String var8;
               if (!TextUtils.isEmpty(var8 = zza((var7 = (Entry)var6.next()).getValue(), var1 + 1))) {
                  if (var1 > 0 && !var4) {
                     var2.append("{");
                     var4 = true;
                     var5 = var2.length();
                  }

                  if (var2.length() > var5) {
                     var2.append(", ");
                  }

                  String var9 = (String)var7.getKey();
                  var2.append(var9);
                  var2.append('=');
                  var2.append(var8);
               }
            }

            if (var4) {
               var2.append("}");
            }

            return var2.toString();
         } else {
            return var0.toString();
         }
      }
   }
}
