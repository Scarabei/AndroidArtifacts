package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzzn
@TargetApi(11)
public class zzalg extends zzakb {
   public zzalg(zzaka var1, boolean var2) {
      super(var1, var2);
   }

   protected final WebResourceResponse zza(WebView var1, String var2, @Nullable Map var3) {
      if (!(var1 instanceof zzaka)) {
         zzafr.zzaT("Tried to intercept request from a WebView that wasn't an AdWebView.");
         return null;
      } else {
         zzaka var4 = (zzaka)var1;
         if (this.zztr != null) {
            this.zztr.zza(var2, var3, 1);
         }

         String var5 = (new File(var2)).getName();
         if (!"mraid.js".equalsIgnoreCase(var5)) {
            return super.shouldInterceptRequest(var1, var2);
         } else {
            if (var4.zziw() != null) {
               var4.zziw().zzfL();
            }

            String var6;
            zzme var8;
            if (var4.zzam().zzAt) {
               var8 = zzmo.zzCP;
               var6 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8);
            } else if (var4.zziA()) {
               var8 = zzmo.zzCO;
               var6 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8);
            } else {
               var8 = zzmo.zzCN;
               var6 = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8);
            }

            try {
               Context var15 = var4.getContext();
               String var9 = var4.zziz().zzaP;
               Context var14 = var15;
               HashMap var11;
               (var11 = new HashMap()).put("User-Agent", com.google.android.gms.ads.internal.zzbs.zzbz().zzs(var14, var9));
               var11.put("Cache-Control", "max-stale=3600");
               String var12;
               return (var12 = (String)(new zzaie(var14)).zzb(var6, var11).get(60L, TimeUnit.SECONDS)) == null ? null : new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(var12.getBytes("UTF-8")));
            } catch (ExecutionException | InterruptedException | TimeoutException | IOException var13) {
               String var10001 = String.valueOf(var13.getMessage());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Could not fetch MRAID JS. ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Could not fetch MRAID JS. ");
               }

               zzafr.zzaT(var10000);
               return null;
            }
         }
      }
   }
}
