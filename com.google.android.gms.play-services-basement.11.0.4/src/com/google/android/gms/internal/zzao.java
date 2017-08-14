package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public final class zzao implements zzan {
   private final zzap zzaB;
   private final SSLSocketFactory zzaC;

   public zzao() {
      this((zzap)null);
   }

   private zzao(zzap var1) {
      this((zzap)null, (SSLSocketFactory)null);
   }

   private zzao(zzap var1, SSLSocketFactory var2) {
      this.zzaB = null;
      this.zzaC = null;
   }

   public final HttpResponse zza(zzp var1, Map var2) throws IOException, zza {
      String var3 = var1.getUrl();
      HashMap var4;
      (var4 = new HashMap()).putAll(var1.getHeaders());
      var4.putAll(var2);
      IOException var10000;
      if (this.zzaB != null) {
         String var5;
         if ((var5 = this.zzaB.zzg(var3)) == null) {
            var10000 = new IOException;
            String var10003 = String.valueOf(var3);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "URL blocked by rewriter: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("URL blocked by rewriter: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         var3 = var5;
      }

      URL var15;
      HttpURLConnection var19;
      (var19 = (HttpURLConnection)(var15 = new URL(var3)).openConnection()).setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
      int var18 = var1.zzi();
      var19.setConnectTimeout(var18);
      var19.setReadTimeout(var18);
      var19.setUseCaches(false);
      var19.setDoInput(true);
      "https".equals(var15.getProtocol());
      HttpURLConnection var6 = var19;
      Iterator var7 = var4.keySet().iterator();

      while(var7.hasNext()) {
         String var8 = (String)var7.next();
         var6.addRequestProperty(var8, (String)var4.get(var8));
      }

      switch(var1.getMethod()) {
      case -1:
         var10000 = null;
         break;
      case 0:
         var6.setRequestMethod("GET");
         break;
      case 1:
         var6.setRequestMethod("POST");
         zza(var6, var1);
         break;
      case 2:
         var6.setRequestMethod("PUT");
         zza(var6, var1);
         break;
      case 3:
         var6.setRequestMethod("DELETE");
         break;
      case 4:
         var6.setRequestMethod("HEAD");
         break;
      case 5:
         var6.setRequestMethod("OPTIONS");
         break;
      case 6:
         var6.setRequestMethod("TRACE");
         break;
      case 7:
         var6.setRequestMethod("PATCH");
         zza(var6, var1);
         break;
      default:
         throw new IllegalStateException("Unknown method type.");
      }

      ProtocolVersion var21 = new ProtocolVersion("HTTP", 1, 1);
      if (var6.getResponseCode() == -1) {
         throw new IOException("Could not retrieve response code from HttpUrlConnection.");
      } else {
         BasicStatusLine var9 = new BasicStatusLine(var21, var6.getResponseCode(), var6.getResponseMessage());
         BasicHttpResponse var10 = new BasicHttpResponse(var9);
         int var23 = var1.getMethod();
         int var22 = var9.getStatusCode();
         if (var23 != 4 && (100 > var22 || var22 >= 200) && var22 != 204 && var22 != 304) {
            var10.setEntity(zza(var6));
         }

         Iterator var11 = var6.getHeaderFields().entrySet().iterator();

         while(var11.hasNext()) {
            Entry var12;
            if ((var12 = (Entry)var11.next()).getKey() != null) {
               BasicHeader var13 = new BasicHeader((String)var12.getKey(), (String)((List)var12.getValue()).get(0));
               var10.addHeader(var13);
            }
         }

         return var10;
      }
   }

   private static HttpEntity zza(HttpURLConnection var0) {
      BasicHttpEntity var1 = new BasicHttpEntity();

      InputStream var2;
      try {
         var2 = var0.getInputStream();
      } catch (IOException var3) {
         var2 = var0.getErrorStream();
      }

      var1.setContent(var2);
      var1.setContentLength((long)var0.getContentLength());
      var1.setContentEncoding(var0.getContentEncoding());
      var1.setContentType(var0.getContentType());
      return var1;
   }

   private static void zza(HttpURLConnection var0, zzp var1) throws IOException, zza {
      byte[] var2;
      if ((var2 = var1.zzg()) != null) {
         var0.setDoOutput(true);
         var0.addRequestProperty("Content-Type", zzp.zzf());
         DataOutputStream var3;
         (var3 = new DataOutputStream(var0.getOutputStream())).write(var2);
         var3.close();
      }

   }
}
