package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzua {
   public final String zzLH;
   public final String zzLI;
   public final List zzLJ;
   public final String zzLK;
   public final String zzLL;
   public final List zzLM;
   public final List zzLN;
   public final List zzLO;
   public final String zzLP;
   public final List zzLQ;
   public final List zzLR;
   @Nullable
   public final String zzLS;
   @Nullable
   public final String zzLT;
   public final String zzLU;
   @Nullable
   public final List zzLV;
   public final String zzLW;
   @Nullable
   private String zzLX;

   public zzua(String var1, String var2, List var3, String var4, String var5, List var6, List var7, String var8, String var9, List var10, List var11, String var12, String var13, String var14, List var15, String var16, List var17, String var18) {
      this.zzLH = var1;
      this.zzLI = null;
      this.zzLJ = var3;
      this.zzLK = null;
      this.zzLL = null;
      this.zzLM = var6;
      this.zzLN = var7;
      this.zzLP = var8;
      this.zzLQ = var10;
      this.zzLR = var11;
      this.zzLS = null;
      this.zzLT = null;
      this.zzLU = null;
      this.zzLV = null;
      this.zzLW = null;
      this.zzLO = var17;
      this.zzLX = null;
   }

   public zzua(JSONObject var1) throws JSONException {
      this.zzLI = var1.optString("id");
      JSONArray var2 = var1.getJSONArray("adapters");
      ArrayList var3 = new ArrayList(var2.length());

      for(int var4 = 0; var4 < var2.length(); ++var4) {
         var3.add(var2.getString(var4));
      }

      this.zzLJ = Collections.unmodifiableList(var3);
      this.zzLK = var1.optString("allocation_id", (String)null);
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLM = zzuj.zza(var1, "clickurl");
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLN = zzuj.zza(var1, "imp_urls");
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLO = zzuj.zza(var1, "fill_urls");
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLQ = zzuj.zza(var1, "video_start_urls");
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLR = zzuj.zza(var1, "video_complete_urls");
      JSONObject var8 = var1.optJSONObject("ad");
      this.zzLH = var8 != null ? var8.toString() : null;
      JSONObject var5 = var1.optJSONObject("data");
      this.zzLP = var5 != null ? var5.toString() : null;
      this.zzLL = var5 != null ? var5.optString("class_name") : null;
      this.zzLS = var1.optString("html_template", (String)null);
      this.zzLT = var1.optString("ad_base_url", (String)null);
      JSONObject var6 = var1.optJSONObject("assets");
      this.zzLU = var6 != null ? var6.toString() : null;
      com.google.android.gms.ads.internal.zzbs.zzbS();
      this.zzLV = zzuj.zza(var1, "template_ids");
      JSONObject var7 = var1.optJSONObject("ad_loader_options");
      this.zzLW = var7 != null ? var7.toString() : null;
      this.zzLX = var1.optString("response_type", (String)null);
   }

   public final boolean zzfh() {
      return "banner".equalsIgnoreCase(this.zzLX);
   }

   public final boolean zzfi() {
      return "native".equalsIgnoreCase(this.zzLX);
   }
}
