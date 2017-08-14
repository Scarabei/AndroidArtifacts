package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzo {
   private static final Pattern zzaJU = Pattern.compile("\\\\.");
   private static final Pattern zzaJV = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

   public static String zzcK(String var0) {
      if (!TextUtils.isEmpty(var0)) {
         Matcher var1 = zzaJV.matcher(var0);
         StringBuffer var2 = null;

         while(var1.find()) {
            if (var2 == null) {
               var2 = new StringBuffer();
            }

            switch(var1.group().charAt(0)) {
            case '\b':
               var1.appendReplacement(var2, "\\\\b");
               break;
            case '\t':
               var1.appendReplacement(var2, "\\\\t");
               break;
            case '\n':
               var1.appendReplacement(var2, "\\\\n");
               break;
            case '\f':
               var1.appendReplacement(var2, "\\\\f");
               break;
            case '\r':
               var1.appendReplacement(var2, "\\\\r");
               break;
            case '"':
               var1.appendReplacement(var2, "\\\\\\\"");
               break;
            case '/':
               var1.appendReplacement(var2, "\\\\/");
               break;
            case '\\':
               var1.appendReplacement(var2, "\\\\\\\\");
            }
         }

         if (var2 == null) {
            return var0;
         } else {
            var1.appendTail(var2);
            return var2.toString();
         }
      } else {
         return var0;
      }
   }

   public static boolean zzc(Object var0, Object var1) {
      if (var0 == null && var1 == null) {
         return true;
      } else if (var0 != null && var1 != null) {
         Object var6;
         if (var0 instanceof JSONObject && var1 instanceof JSONObject) {
            JSONObject var10 = (JSONObject)var0;
            JSONObject var11 = (JSONObject)var1;
            if (var10.length() != var11.length()) {
               return false;
            } else {
               Iterator var12 = var10.keys();

               while(var12.hasNext()) {
                  String var13 = (String)var12.next();
                  if (!var11.has(var13)) {
                     return false;
                  }

                  try {
                     var6 = var10.get(var13);
                     Object var7 = var11.get(var13);
                     if (!zzc(var6, var7)) {
                        return false;
                     }
                  } catch (JSONException var8) {
                     return false;
                  }
               }

               return true;
            }
         } else if (var0 instanceof JSONArray && var1 instanceof JSONArray) {
            JSONArray var2 = (JSONArray)var0;
            JSONArray var3 = (JSONArray)var1;
            if (var2.length() != var3.length()) {
               return false;
            } else {
               for(int var4 = 0; var4 < var2.length(); ++var4) {
                  try {
                     Object var5 = var2.get(var4);
                     var6 = var3.get(var4);
                     if (!zzc(var5, var6)) {
                        return false;
                     }
                  } catch (JSONException var9) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return var0.equals(var1);
         }
      } else {
         return false;
      }
   }
}
