package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzafj {
   private final long zzYk;
   private final List zzYl = new ArrayList();
   private final List zzYm = new ArrayList();
   private final Map zzYn = new HashMap();
   private String mAppId;
   private String zzYo;
   private boolean zzYp = false;

   public zzafj(String var1, long var2) {
      this.zzYo = var1;
      this.zzYk = var2;
      this.zzaE(var1);
   }

   public final long zzhi() {
      return this.zzYk;
   }

   public final boolean zzhj() {
      return this.zzYp;
   }

   public final String zzhk() {
      return this.zzYo;
   }

   public final String zzhl() {
      return this.mAppId;
   }

   public final Map zzhm() {
      return this.zzYn;
   }

   private final void zzaE(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         try {
            JSONObject var2;
            if ((var2 = new JSONObject(var1)).optInt("status", -1) != 1) {
               this.zzYp = false;
               zzafr.zzaT("App settings could not be fetched successfully.");
            } else {
               this.zzYp = true;
               this.mAppId = var2.optString("app_id");
               JSONArray var3;
               if ((var3 = var2.optJSONArray("ad_unit_id_settings")) != null) {
                  for(int var4 = 0; var4 < var3.length(); ++var4) {
                     JSONObject var5 = var3.getJSONObject(var4);
                     String var8 = var5.optString("format");
                     String var9 = var5.optString("ad_unit_id");
                     if (!TextUtils.isEmpty(var8) && !TextUtils.isEmpty(var9)) {
                        if ("interstitial".equalsIgnoreCase(var8)) {
                           this.zzYm.add(var9);
                        } else {
                           JSONObject var10;
                           if ("rewarded".equalsIgnoreCase(var8) && (var10 = var5.optJSONObject("mediation_config")) != null) {
                              zzub var11 = new zzub(var10);
                              this.zzYn.put(var9, var11);
                           }
                        }
                     }
                  }
               }

               this.zzg(var2);
            }
         } catch (JSONException var12) {
            zzafr.zzc("Exception occurred while processing app setting json", var12);
            com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var12, (String)"AppSettings.parseAppSettingsJson");
         }
      }
   }

   private final void zzg(JSONObject var1) {
      JSONArray var2;
      if ((var2 = var1.optJSONArray("persistable_banner_ad_unit_ids")) != null) {
         for(int var3 = 0; var3 < var2.length(); ++var3) {
            String var4 = var2.optString(var3);
            this.zzYl.add(var4);
         }
      }

   }
}
