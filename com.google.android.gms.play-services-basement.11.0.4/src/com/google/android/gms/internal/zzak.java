package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class zzak implements zzan {
   private HttpClient zzaA;

   public zzak(HttpClient var1) {
      this.zzaA = var1;
   }

   private static void zza(HttpUriRequest var0, Map var1) {
      Iterator var2 = var1.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var0.setHeader(var3, (String)var1.get(var3));
      }

   }

   public final HttpResponse zza(zzp var1, Map var2) throws IOException, zza {
      Object var10000;
      switch(var1.getMethod()) {
      case -1:
         var10000 = null;
         var10000 = new HttpGet(var1.getUrl());
         break;
      case 0:
         var10000 = new HttpGet(var1.getUrl());
         break;
      case 1:
         HttpPost var10;
         (var10 = new HttpPost(var1.getUrl())).addHeader("Content-Type", zzp.zzf());
         zza((HttpEntityEnclosingRequestBase)var10, (zzp)var1);
         var10000 = var10;
         break;
      case 2:
         HttpPut var9;
         (var9 = new HttpPut(var1.getUrl())).addHeader("Content-Type", zzp.zzf());
         zza((HttpEntityEnclosingRequestBase)var9, (zzp)var1);
         var10000 = var9;
         break;
      case 3:
         var10000 = new HttpDelete(var1.getUrl());
         break;
      case 4:
         var10000 = new HttpHead(var1.getUrl());
         break;
      case 5:
         var10000 = new HttpOptions(var1.getUrl());
         break;
      case 6:
         var10000 = new HttpTrace(var1.getUrl());
         break;
      case 7:
         zzal var7;
         (var7 = new zzal(var1.getUrl())).addHeader("Content-Type", zzp.zzf());
         zza((HttpEntityEnclosingRequestBase)var7, (zzp)var1);
         var10000 = var7;
         break;
      default:
         throw new IllegalStateException("Unknown request method.");
      }

      Object var3 = var10000;
      zza((HttpUriRequest)var10000, (Map)var2);
      zza((HttpUriRequest)var3, (Map)var1.getHeaders());
      HttpParams var4 = ((HttpUriRequest)var3).getParams();
      int var5 = var1.zzi();
      HttpConnectionParams.setConnectionTimeout(var4, 5000);
      HttpConnectionParams.setSoTimeout(var4, var5);
      return this.zzaA.execute((HttpUriRequest)var3);
   }

   private static void zza(HttpEntityEnclosingRequestBase var0, zzp var1) throws zza {
      byte[] var2;
      if ((var2 = var1.zzg()) != null) {
         ByteArrayEntity var3 = new ByteArrayEntity(var2);
         var0.setEntity(var3);
      }

   }
}
