package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class cu {
   private String zzbEa = "https://www.google-analytics.com";

   public final String zzb(bz var1) {
      String var2 = this.zzbEa;
      String var3 = String.valueOf("/gtm/android?");
      String var10000;
      if (var1.zzCL()) {
         var10000 = var1.zzCM();
      } else if (var1 == null) {
         var10000 = "";
      } else {
         String var6 = !var1.zzCN().trim().equals("") ? var1.zzCN().trim() : "-1";
         StringBuilder var7 = new StringBuilder();
         if (var1.zzCJ() != null) {
            var7.append(var1.zzCJ());
         } else {
            var7.append("id");
         }

         var7.append("=").append(zzfC(var1.getContainerId())).append("&pv=").append(zzfC(var6)).append("&rv=5.0");
         if (var1.zzCL()) {
            var7.append("&gtm_debug=x");
         }

         var10000 = var7.toString();
      }

      String var4 = var10000;
      return (new StringBuilder(String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length())).append(var2).append(var3).append(var4).toString();
   }

   private static String zzfC(String var0) {
      try {
         return URLEncoder.encode(var0, "UTF-8").replaceAll("\\+", "%20");
      } catch (UnsupportedEncodingException var1) {
         String var10001 = String.valueOf(var0);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Cannot encode the string: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Cannot encode the string: ");
         }

         zzcvk.e(var10000);
         return "";
      }
   }
}
