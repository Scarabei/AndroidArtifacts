package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzdj;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class eq implements er {
   private HttpURLConnection zzbKR;
   private InputStream zzbKS = null;

   public final InputStream zzfU(String var1) throws IOException {
      HttpURLConnection var4;
      (var4 = (HttpURLConnection)(new URL(var1)).openConnection()).setReadTimeout(20000);
      var4.setConnectTimeout(20000);
      this.zzbKR = var4;
      HttpURLConnection var2 = this.zzbKR;
      int var3;
      if ((var3 = this.zzbKR.getResponseCode()) == 200) {
         this.zzbKS = var2.getInputStream();
         return this.zzbKS;
      } else {
         String var5 = (new StringBuilder(25)).append("Bad response: ").append(var3).toString();
         if (var3 == 404) {
            throw new FileNotFoundException(var5);
         } else if (var3 == 503) {
            throw new et(var5);
         } else {
            throw new IOException(var5);
         }
      }
   }

   public final void close() {
      HttpURLConnection var2 = this.zzbKR;
      eq var1 = this;

      try {
         if (var1.zzbKS != null) {
            var1.zzbKS.close();
         }
      } catch (IOException var4) {
         String var10001 = String.valueOf(var4.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "HttpUrlConnectionNetworkClient: Error when closing http input stream: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("HttpUrlConnectionNetworkClient: Error when closing http input stream: ");
         }

         zzdj.zzb(var10000, var4);
      }

      if (var2 != null) {
         var2.disconnect();
      }

   }
}
