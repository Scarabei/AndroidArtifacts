package com.google.android.gms.internal;

import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public final class zzam {
   public static zzc zzb(zzn var0) {
      long var1 = System.currentTimeMillis();
      Map var3 = var0.zzy;
      long var4 = 0L;
      long var6 = 0L;
      long var8 = 0L;
      long var10 = 0L;
      long var12 = 0L;
      long var14 = 0L;
      long var16 = 0L;
      boolean var18 = false;
      boolean var19 = false;
      String var21;
      if ((var21 = (String)var3.get("Date")) != null) {
         var4 = zzf(var21);
      }

      if ((var21 = (String)var3.get("Cache-Control")) != null) {
         var18 = true;
         String[] var22 = var21.split(",");

         for(int var23 = 0; var23 < var22.length; ++var23) {
            String var24;
            if ((var24 = var22[var23].trim()).equals("no-cache") || var24.equals("no-store")) {
               return null;
            }

            if (var24.startsWith("max-age=")) {
               try {
                  var14 = Long.parseLong(var24.substring(8));
               } catch (Exception var26) {
                  ;
               }
            } else if (var24.startsWith("stale-while-revalidate=")) {
               try {
                  var16 = Long.parseLong(var24.substring(23));
               } catch (Exception var25) {
                  ;
               }
            } else if (var24.equals("must-revalidate") || var24.equals("proxy-revalidate")) {
               var19 = true;
            }
         }
      }

      if ((var21 = (String)var3.get("Expires")) != null) {
         var8 = zzf(var21);
      }

      if ((var21 = (String)var3.get("Last-Modified")) != null) {
         var6 = zzf(var21);
      }

      String var20 = (String)var3.get("ETag");
      if (var18) {
         var10 = var1 + var14 * 1000L;
         var12 = var19 ? var10 : var10 + var16 * 1000L;
      } else if (var4 > 0L && var8 >= var4) {
         var12 = var10 = var1 + (var8 - var4);
      }

      zzc var27;
      (var27 = new zzc()).data = var0.data;
      var27.zza = var20;
      var27.zze = var10;
      var27.zzd = var12;
      var27.zzb = var4;
      var27.zzc = var6;
      var27.zzf = var3;
      return var27;
   }

   private static long zzf(String var0) {
      try {
         return DateUtils.parseDate(var0).getTime();
      } catch (DateParseException var1) {
         return 0L;
      }
   }

   public static String zza(Map var0) {
      String var1 = "ISO-8859-1";
      String var2;
      if ((var2 = (String)var0.get("Content-Type")) != null) {
         String[] var3 = var2.split(";");

         for(int var4 = 1; var4 < var3.length; ++var4) {
            String[] var5;
            if ((var5 = var3[var4].trim().split("=")).length == 2 && var5[0].equals("charset")) {
               return var5[1];
            }
         }
      }

      return var1;
   }
}
