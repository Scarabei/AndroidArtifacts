package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzre implements zzrd {
   private final Context mContext;
   private final zzaje zztW;

   public zzre(Context var1, zzaje var2) {
      this.mContext = var1;
      this.zztW = var2;
   }

   private static zzri zzc(JSONObject var0) {
      String var1 = var0.optString("http_request_id");
      String var2 = var0.optString("url");
      String var3 = var0.optString("post_body", (String)null);
      URL var4 = null;

      try {
         var4 = new URL(var2);
      } catch (MalformedURLException var11) {
         zzafr.zzb("Error constructing http request.", var11);
      }

      ArrayList var5 = new ArrayList();
      JSONArray var6;
      if ((var6 = var0.optJSONArray("headers")) == null) {
         var6 = new JSONArray();
      }

      for(int var7 = 0; var7 < var6.length(); ++var7) {
         JSONObject var8;
         if ((var8 = var6.optJSONObject(var7)) != null) {
            String var9 = var8.optString("key");
            String var10 = var8.optString("value");
            var5.add(new zzrh(var9, var10));
         }
      }

      return new zzri(var1, var4, var5, var3);
   }

   private static JSONObject zza(zzrk var0) {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("http_request_id", var0.zzey());
         if (var0.getBody() != null) {
            var1.put("body", var0.getBody());
         }

         JSONArray var2 = new JSONArray();
         Iterator var3 = var0.zzeD().iterator();

         while(var3.hasNext()) {
            zzrh var4 = (zzrh)var3.next();
            var2.put((new JSONObject()).put("key", var4.getKey()).put("value", var4.getValue()));
         }

         var1.put("headers", var2);
         var1.put("response_code", var0.getResponseCode());
      } catch (JSONException var5) {
         zzafr.zzb("Error constructing JSON for http response.", var5);
      }

      return var1;
   }

   private final zzrj zza(zzri var1) {
      HttpURLConnection var2 = null;

      zzrj var4;
      try {
         var2 = (HttpURLConnection)var1.zzez().openConnection();
         com.google.android.gms.ads.internal.zzbs.zzbz().zza(this.mContext, this.zztW.zzaP, false, var2);
         ArrayList var9;
         int var10 = (var9 = (ArrayList)var1.zzeA()).size();
         int var11 = 0;

         while(var11 < var10) {
            Object var10000 = var9.get(var11);
            ++var11;
            zzrh var17 = (zzrh)var10000;
            var2.addRequestProperty(var17.getKey(), var17.getValue());
         }

         if (!TextUtils.isEmpty(var1.zzeB())) {
            var2.setDoOutput(true);
            byte[] var3 = var1.zzeB().getBytes();
            var2.setFixedLengthStreamingMode(var3.length);
            BufferedOutputStream var18;
            (var18 = new BufferedOutputStream(var2.getOutputStream())).write(var3);
            var18.close();
         }

         ArrayList var16 = new ArrayList();
         if (var2.getHeaderFields() != null) {
            Iterator var19 = var2.getHeaderFields().entrySet().iterator();

            while(var19.hasNext()) {
               Entry var5;
               Iterator var6 = ((List)(var5 = (Entry)var19.next()).getValue()).iterator();

               while(var6.hasNext()) {
                  String var7 = (String)var6.next();
                  var16.add(new zzrh((String)var5.getKey(), var7));
               }
            }
         }

         String var10002 = var1.zzey();
         int var10003 = var2.getResponseCode();
         com.google.android.gms.ads.internal.zzbs.zzbz();
         zzrk var21 = new zzrk(var10002, var10003, var16, zzagz.zza(new InputStreamReader(var2.getInputStream())));
         zzrj var20 = new zzrj(this, true, var21, (String)null);
         return var20;
      } catch (Exception var14) {
         var4 = new zzrj(this, false, (zzrk)null, var14.toString());
      } finally {
         if (var2 != null) {
            var2.disconnect();
         }

      }

      return var4;
   }

   public final JSONObject zzR(String var1) {
      JSONObject var2;
      try {
         var2 = new JSONObject(var1);
      } catch (JSONException var11) {
         zzafr.e("The request is not a valid JSON.");

         try {
            return (new JSONObject()).put("success", false);
         } catch (JSONException var8) {
            return new JSONObject();
         }
      }

      JSONObject var3 = new JSONObject();
      String var4 = "";

      try {
         var4 = var2.optString("http_request_id");
         zzri var12 = zzc(var2);
         zzrj var6;
         if ((var6 = this.zza(var12)).isSuccess()) {
            JSONObject var7 = zza(var6.zzeC());
            var3.put("response", var7);
            var3.put("success", true);
         } else {
            var3.put("response", (new JSONObject()).put("http_request_id", var4));
            var3.put("success", false);
            var3.put("reason", var6.getReason());
         }
      } catch (Exception var10) {
         Exception var5 = var10;

         try {
            var3.put("response", (new JSONObject()).put("http_request_id", var4));
            var3.put("success", false);
            var3.put("reason", var5.toString());
         } catch (JSONException var9) {
            ;
         }
      }

      return var3;
   }

   public final void zza(zzaka var1, Map var2) {
      zzagt.zza((Runnable)(new zzrf(this, var2, var1)));
   }
}
