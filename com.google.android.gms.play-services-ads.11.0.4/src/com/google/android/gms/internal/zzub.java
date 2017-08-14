package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzub {
   public final List zzLY;
   public final long zzLZ;
   public final List zzMa;
   public final List zzMb;
   public final List zzMc;
   public final List zzMd;
   public final boolean zzMe;
   public final String zzMf;
   public final long zzMg;
   public final String zzMh;
   public final int zzMi;
   public final int zzMj;
   public final long zzMk;
   public final boolean zzMl;
   public final boolean zzMm;
   public int zzMn;
   public int zzMo;
   public boolean zzMp;

   public zzub(List var1, long var2, List var4, List var5, List var6, List var7, boolean var8, String var9, long var10, int var12, int var13, String var14, int var15, int var16, long var17, boolean var19) {
      this.zzLY = var1;
      this.zzLZ = var2;
      this.zzMa = var4;
      this.zzMb = var5;
      this.zzMc = var6;
      this.zzMd = var7;
      this.zzMe = var8;
      this.zzMf = var9;
      this.zzMg = -1L;
      this.zzMn = 0;
      this.zzMo = 1;
      this.zzMh = null;
      this.zzMi = 0;
      this.zzMj = -1;
      this.zzMk = -1L;
      this.zzMl = false;
      this.zzMm = false;
      this.zzMp = false;
   }

   public zzub(String var1) throws JSONException {
      this(new JSONObject(var1));
   }

   public zzub(JSONObject var1) throws JSONException {
      if (zzafr.zzz(2)) {
         String var10001 = String.valueOf(var1.toString(2));
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Mediation Response JSON: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Mediation Response JSON: ");
         }

         zzafr.v(var10000);
      }

      JSONArray var2 = var1.getJSONArray("ad_networks");
      ArrayList var3 = new ArrayList(var2.length());
      int var4 = -1;

      for(int var5 = 0; var5 < var2.length(); ++var5) {
         zzua var6;
         if ((var6 = new zzua(var2.getJSONObject(var5))).zzfh()) {
            this.zzMp = true;
         }

         var3.add(var6);
         if (var4 < 0 && zza(var6)) {
            var4 = var5;
         }
      }

      this.zzMn = var4;
      this.zzMo = var2.length();
      this.zzLY = Collections.unmodifiableList(var3);
      this.zzMf = var1.optString("qdata");
      this.zzMj = var1.optInt("fs_model_type", -1);
      this.zzMk = var1.optLong("timeout_ms", -1L);
      JSONObject var9;
      if ((var9 = var1.optJSONObject("settings")) != null) {
         this.zzLZ = var9.optLong("ad_network_timeout_millis", -1L);
         com.google.android.gms.ads.internal.zzbs.zzbS();
         this.zzMa = zzuj.zza(var9, "click_urls");
         com.google.android.gms.ads.internal.zzbs.zzbS();
         this.zzMb = zzuj.zza(var9, "imp_urls");
         com.google.android.gms.ads.internal.zzbs.zzbS();
         this.zzMc = zzuj.zza(var9, "nofill_urls");
         com.google.android.gms.ads.internal.zzbs.zzbS();
         this.zzMd = zzuj.zza(var9, "remote_ping_urls");
         this.zzMe = var9.optBoolean("render_in_browser", false);
         long var10 = var9.optLong("refresh", -1L);
         this.zzMg = var10 > 0L ? var10 * 1000L : -1L;
         zzaee var8;
         if ((var8 = zzaee.zza(var9.optJSONArray("rewards"))) == null) {
            this.zzMh = null;
            this.zzMi = 0;
         } else {
            this.zzMh = var8.type;
            this.zzMi = var8.zzWW;
         }

         this.zzMl = var9.optBoolean("use_displayed_impression", false);
         this.zzMm = var9.optBoolean("allow_pub_rendered_attribution", false);
      } else {
         this.zzLZ = -1L;
         this.zzMa = null;
         this.zzMb = null;
         this.zzMc = null;
         this.zzMd = null;
         this.zzMg = -1L;
         this.zzMh = null;
         this.zzMi = 0;
         this.zzMl = false;
         this.zzMe = false;
         this.zzMm = false;
      }
   }

   private static boolean zza(zzua var0) {
      Iterator var1 = var0.zzLJ.iterator();

      do {
         if (!var1.hasNext()) {
            return false;
         }
      } while(!((String)var1.next()).equals("com.google.ads.mediation.admob.AdMobAdapter"));

      return true;
   }
}
