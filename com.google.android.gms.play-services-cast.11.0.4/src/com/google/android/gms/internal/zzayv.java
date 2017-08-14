package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzayv {
   private static final zzayo zzapq = new zzayo("MetadataUtils");
   private static final String[] zzayU = new String[]{"Z", "+hh", "+hhmm", "+hh:mm"};
   private static final String zzayV;

   public static void zza(List var0, JSONObject var1) {
      try {
         var0.clear();
         JSONArray var2;
         int var3 = (var2 = var1.getJSONArray("images")).length();

         for(int var4 = 0; var4 < var3; ++var4) {
            JSONObject var5 = var2.getJSONObject(var4);

            try {
               var0.add(new WebImage(var5));
            } catch (IllegalArgumentException var6) {
               ;
            }
         }

      } catch (JSONException var7) {
         ;
      }
   }

   public static void zza(JSONObject var0, List var1) {
      if (var1 != null && !var1.isEmpty()) {
         JSONArray var2 = new JSONArray();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            WebImage var4 = (WebImage)var3.next();
            var2.put(var4.toJson());
         }

         try {
            var0.put("images", var2);
            return;
         } catch (JSONException var5) {
            ;
         }
      }

   }

   public static String zza(Calendar var0) {
      if (var0 == null) {
         zzapq.zzb("Calendar object cannot be null");
         return null;
      } else {
         String var1 = zzayV;
         if (var0.get(11) == 0 && var0.get(12) == 0 && var0.get(13) == 0) {
            var1 = "yyyyMMdd";
         }

         SimpleDateFormat var2;
         (var2 = new SimpleDateFormat(var1)).setTimeZone(var0.getTimeZone());
         String var3;
         if ((var3 = var2.format(var0.getTime())).endsWith("+0000")) {
            var3 = var3.replace("+0000", zzayU[0]);
         }

         return var3;
      }
   }

   public static Calendar zzco(String var0) {
      if (TextUtils.isEmpty(var0)) {
         zzapq.zzb("Input string is empty or null");
         return null;
      } else {
         String var1;
         if (TextUtils.isEmpty(var1 = zzcp(var0))) {
            zzapq.zzb("Invalid date format");
            return null;
         } else {
            String var2 = zzcq(var0);
            String var3 = "yyyyMMdd";
            if (!TextUtils.isEmpty(var2)) {
               String var4 = String.valueOf(var1);
               var1 = (new StringBuilder(1 + String.valueOf(var4).length() + String.valueOf(var2).length())).append(var4).append("T").append(var2).toString();
               if (var2.length() == 6) {
                  var3 = "yyyyMMdd'T'HHmmss";
               } else {
                  var3 = zzayV;
               }
            }

            Calendar var8 = GregorianCalendar.getInstance();

            Date var5;
            try {
               var5 = (new SimpleDateFormat(var3)).parse(var1);
            } catch (ParseException var7) {
               zzapq.zzb("Error parsing string: %s", var7.getMessage());
               return null;
            }

            var8.setTime(var5);
            return var8;
         }
      }
   }

   private static String zzcp(String var0) {
      if (TextUtils.isEmpty(var0)) {
         zzapq.zzb("Input string is empty or null");
         return null;
      } else {
         try {
            return var0.substring(0, 8);
         } catch (IndexOutOfBoundsException var2) {
            zzapq.zze("Error extracting the date: %s", var2.getMessage());
            return null;
         }
      }
   }

   private static String zzcq(String var0) {
      if (TextUtils.isEmpty(var0)) {
         zzapq.zzb("string is empty or null");
         return null;
      } else {
         int var1;
         int var10000 = var1 = var0.indexOf(84);
         ++var1;
         if (var10000 != 8) {
            zzapq.zzb("T delimeter is not found");
            return null;
         } else {
            String var2;
            try {
               var2 = var0.substring(var1);
            } catch (IndexOutOfBoundsException var5) {
               zzapq.zzb("Error extracting the time substring: %s", var5.getMessage());
               return null;
            }

            if (var2.length() == 6) {
               return var2;
            } else {
               switch(var2.charAt(6)) {
               case '+':
               case '-':
                  int var4;
                  if ((var4 = var2.length()) == 6 + zzayU[1].length() || var4 == 6 + zzayU[2].length() || var4 == 6 + zzayU[3].length()) {
                     return var2.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                  }
               default:
                  return null;
               case 'Z':
                  if (var2.length() == 6 + zzayU[0].length()) {
                     String var6 = String.valueOf(var2.substring(0, var2.length() - 1));
                     String var10001 = String.valueOf("+0000");
                     return var10001.length() != 0 ? var6.concat(var10001) : new String(var6);
                  } else {
                     return null;
                  }
               }
            }
         }
      }
   }

   static {
      String var10000 = String.valueOf("yyyyMMdd'T'HHmmss");
      String var10001 = String.valueOf(zzayU[0]);
      if (var10001.length() != 0) {
         var10000 = var10000.concat(var10001);
      } else {
         String var10002 = new String;
         var10001 = var10000;
         var10000 = var10002;
         var10002.<init>(var10001);
      }

      zzayV = var10000;
   }
}
