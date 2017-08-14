package com.google.android.gms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class zzm {
   private static final Pattern zzaJR = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
   private static final Pattern zzaJS = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
   private static final Pattern zzaJT = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

   public static Map zza(URI var0, String var1) {
      Object var2 = Collections.emptyMap();
      String var3;
      if ((var3 = var0.getRawQuery()) != null && var3.length() > 0) {
         var2 = new HashMap();
         Scanner var4;
         (var4 = new Scanner(var3)).useDelimiter("&");

         String var6;
         String var7;
         for(; var4.hasNext(); ((Map)var2).put(var6, var7)) {
            String[] var5;
            if ((var5 = var4.next().split("=")).length == 0 || var5.length > 2) {
               throw new IllegalArgumentException("bad parameter");
            }

            var6 = decode(var5[0], var1);
            var7 = null;
            if (var5.length == 2) {
               var7 = decode(var5[1], var1);
            }
         }
      }

      return (Map)var2;
   }

   private static String decode(String var0, String var1) {
      try {
         return URLDecoder.decode(var0, var1 != null ? var1 : "ISO-8859-1");
      } catch (UnsupportedEncodingException var3) {
         throw new IllegalArgumentException(var3);
      }
   }
}
