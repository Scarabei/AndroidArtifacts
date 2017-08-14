package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzcvj {
   private static String zzbEX;
   private static Map zzbEY = new HashMap();

   public static String zzL(Context var0, String var1) {
      if (zzbEX == null) {
         Class var2 = zzcvj.class;
         synchronized(zzcvj.class) {
            if (zzbEX == null) {
               SharedPreferences var3;
               if ((var3 = var0.getSharedPreferences("gtm_install_referrer", 0)) != null) {
                  zzbEX = var3.getString("referrer", "");
               } else {
                  zzbEX = "";
               }
            }
         }
      }

      return zzV(zzbEX, var1);
   }

   public static String zzV(String var0, String var1) {
      if (var1 == null) {
         return var0.length() > 0 ? var0 : null;
      } else {
         String var10001 = String.valueOf(var0);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "http://hostname/?".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("http://hostname/?");
         }

         return Uri.parse(var10000).getQueryParameter(var1);
      }
   }
}
