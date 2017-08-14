package com.google.android.gms.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@zzzn
public final class zzajo {
   public static HttpURLConnection zzb(String var0, int var1) throws IOException {
      URL var2 = new URL(var0);
      int var3 = 0;

      while(true) {
         ++var3;
         if (var3 > 20) {
            throw new IOException("Too many redirects (20)");
         }

         URLConnection var4;
         (var4 = var2.openConnection()).setConnectTimeout(var1);
         var4.setReadTimeout(var1);
         if (!(var4 instanceof HttpURLConnection)) {
            throw new IOException("Invalid protocol.");
         }

         HttpURLConnection var5;
         (var5 = (HttpURLConnection)var4).setInstanceFollowRedirects(false);
         if (var5.getResponseCode() / 100 != 3) {
            return var5;
         }

         String var6;
         if ((var6 = var5.getHeaderField("Location")) == null) {
            throw new IOException("Missing Location header in redirect");
         }

         String var7;
         if ((var7 = (var2 = new URL(var2, var6)).getProtocol()) == null) {
            throw new IOException("Protocol is null");
         }

         String var10002;
         if (!var7.equals("http") && !var7.equals("https")) {
            IOException var8 = new IOException;
            String var10003 = String.valueOf(var7);
            if (var10003.length() != 0) {
               var10002 = "Unsupported scheme: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Unsupported scheme: ");
            }

            var8.<init>(var10002);
            throw var8;
         }

         String var10001 = String.valueOf(var6);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Redirecting to ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Redirecting to ");
         }

         zzafr.zzaC(var10000);
         var5.disconnect();
      }
   }
}
