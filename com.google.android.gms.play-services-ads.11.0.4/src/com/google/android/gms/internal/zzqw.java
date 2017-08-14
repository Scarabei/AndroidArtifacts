package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzqw implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      PackageManager var3 = var1.getContext().getPackageManager();
      String var4 = (String)var2.get("data");

      JSONObject var5;
      try {
         var5 = new JSONObject(var4);
      } catch (JSONException var22) {
         var1.zzb("openableIntents", new JSONObject());
         return;
      }

      JSONArray var6;
      try {
         var6 = var5.getJSONArray("intents");
      } catch (JSONException var21) {
         var1.zzb("openableIntents", new JSONObject());
         return;
      }

      JSONObject var7 = new JSONObject();

      for(int var8 = 0; var8 < var6.length(); ++var8) {
         JSONObject var9;
         try {
            var9 = var6.getJSONObject(var8);
         } catch (JSONException var23) {
            zzafr.zzb("Error parsing the intent data.", var23);
            continue;
         }

         String var10 = var9.optString("id");
         String var11 = var9.optString("u");
         String var12 = var9.optString("i");
         String var13 = var9.optString("m");
         String var14 = var9.optString("p");
         String var15 = var9.optString("c");
         var9.optString("f");
         var9.optString("e");
         Intent var16 = new Intent();
         if (!TextUtils.isEmpty(var11)) {
            var16.setData(Uri.parse(var11));
         }

         if (!TextUtils.isEmpty(var12)) {
            var16.setAction(var12);
         }

         if (!TextUtils.isEmpty(var13)) {
            var16.setType(var13);
         }

         if (!TextUtils.isEmpty(var14)) {
            var16.setPackage(var14);
         }

         String[] var17;
         if (!TextUtils.isEmpty(var15) && (var17 = var15.split("/", 2)).length == 2) {
            var16.setComponent(new ComponentName(var17[0], var17[1]));
         }

         boolean var18 = var3.resolveActivity(var16, 65536) != null;

         try {
            var7.put(var10, var18);
         } catch (JSONException var20) {
            zzafr.zzb("Error constructing openable urls response.", var20);
         }
      }

      var1.zzb("openableIntents", var7);
   }
}
