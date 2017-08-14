package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzzh implements zzyv {
   private final boolean zzSe;

   public zzzh(boolean var1) {
      this.zzSe = var1;
   }

   private static SimpleArrayMap zza(SimpleArrayMap var0) throws InterruptedException, ExecutionException {
      SimpleArrayMap var1 = new SimpleArrayMap();

      for(int var2 = 0; var2 < var0.size(); ++var2) {
         var1.put(var0.keyAt(var2), ((Future)var0.valueAt(var2)).get());
      }

      return var1;
   }

   // $FF: synthetic method
   public final zzoa zza(zzyn var1, JSONObject var2) throws JSONException, InterruptedException, ExecutionException {
      zzyn var4 = var1;
      zzzh var3 = this;
      SimpleArrayMap var6 = new SimpleArrayMap();
      SimpleArrayMap var7 = new SimpleArrayMap();
      zzajm var8 = var1.zzd(var2);
      zzajm var9 = var1.zzc(var2, "video");
      JSONArray var10 = var2.getJSONArray("custom_assets");

      for(int var11 = 0; var11 < var10.length(); ++var11) {
         JSONObject var12;
         String var13 = (var12 = var10.getJSONObject(var11)).getString("type");
         String var18;
         if ("string".equals(var13)) {
            String var17 = var12.getString("name");
            var18 = var12.getString("string_value");
            var7.put(var17, var18);
         } else if ("image".equals(var13)) {
            var18 = var12.getString("name");
            var6.put(var18, var4.zza(var12, "image_value", var3.zzSe));
         } else {
            String var10001 = String.valueOf(var13);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Unknown custom asset type: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Unknown custom asset type: ");
            }

            zzafr.zzaT(var10000);
         }
      }

      zzaka var19 = zzyn.zzb(var9);
      return new zznu(var2.getString("custom_template_id"), zza(var6), var7, (zznn)var8.get(), var19 != null ? var19.zziH() : null, var19 != null ? var19.getView() : null);
   }
}
