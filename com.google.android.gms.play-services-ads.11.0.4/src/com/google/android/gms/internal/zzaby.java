package com.google.android.gms.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaby {
   private String zzUS;
   private String zzUT;
   private String zzHD;
   private List zzUU;
   private String zzUV;
   private String zzUW;
   private String zzUX;
   private List zzUY;
   private long zzUZ = -1L;
   private boolean zzVa = false;
   private final long zzVb = -1L;
   private List zzRy;
   private long zzVc = -1L;
   private int mOrientation = -1;
   private boolean zzVd = false;
   private boolean zzVe = false;
   private boolean zzVf = false;
   private boolean zzVg = true;
   private boolean zzVh = true;
   private String zzVi = "";
   private boolean zzVj = false;
   private boolean zzMy = false;
   private zzaee zzVk;
   private List zzVl;
   private List zzVm;
   private boolean zzVn = false;
   private zzaak zzVo;
   private boolean zzVp = false;
   private String zzVq;
   private List zzVr;
   private boolean zzVs;
   private String zzVt;
   private zzaeq zzVu;
   private boolean zzVv;
   private final zzaae zzMM;

   private static String zza(Map var0, String var1) {
      List var2;
      return (var2 = (List)var0.get(var1)) != null && !var2.isEmpty() ? (String)var2.get(0) : null;
   }

   private static long zzb(Map var0, String var1) {
      List var2;
      if ((var2 = (List)var0.get(var1)) != null && !var2.isEmpty()) {
         String var3 = (String)var2.get(0);

         try {
            return (long)(Float.parseFloat(var3) * 1000.0F);
         } catch (NumberFormatException var4) {
            zzafr.zzaT((new StringBuilder(36 + String.valueOf(var1).length() + String.valueOf(var3).length())).append("Could not parse float from ").append(var1).append(" header: ").append(var3).toString());
         }
      }

      return -1L;
   }

   private static List zzc(Map var0, String var1) {
      List var2;
      String var3;
      return (var2 = (List)var0.get(var1)) != null && !var2.isEmpty() && (var3 = (String)var2.get(0)) != null ? Arrays.asList(var3.trim().split("\\s+")) : null;
   }

   private static boolean zzd(Map var0, String var1) {
      List var2;
      return (var2 = (List)var0.get(var1)) != null && !var2.isEmpty() && Boolean.valueOf((String)var2.get(0)).booleanValue();
   }

   public zzaby(zzaae var1, String var2) {
      this.zzUT = var2;
      this.zzMM = var1;
   }

   public final zzaai zze(long var1) {
      return new zzaai(this.zzMM, this.zzUT, this.zzHD, this.zzUU, this.zzUY, this.zzUZ, this.zzVa, -1L, this.zzRy, this.zzVc, this.mOrientation, this.zzUS, var1, this.zzUW, this.zzUX, this.zzVd, this.zzVe, this.zzVf, this.zzVg, false, this.zzVi, this.zzVj, this.zzMy, this.zzVk, this.zzVl, this.zzVm, this.zzVn, this.zzVo, this.zzVp, this.zzVq, this.zzVr, this.zzVs, this.zzVt, this.zzVu, this.zzUV, this.zzVh, this.zzVv);
   }

   public final void zza(String var1, Map var2, String var3) {
      this.zzHD = var3;
      this.zzh(var2);
   }

   public final void zzh(Map var1) {
      this.zzUS = zza(var1, "X-Afma-Ad-Size");
      this.zzVt = zza(var1, "X-Afma-Ad-Slot-Size");
      List var4;
      if ((var4 = zzc(var1, "X-Afma-Click-Tracking-Urls")) != null) {
         this.zzUU = var4;
      }

      this.zzUV = zza(var1, "X-Afma-Debug-Signals");
      if ((var4 = (List)var1.get("X-Afma-Debug-Dialog")) != null && !var4.isEmpty()) {
         this.zzUW = (String)var4.get(0);
      }

      if ((var4 = zzc(var1, "X-Afma-Tracking-Urls")) != null) {
         this.zzUY = var4;
      }

      long var12;
      if ((var12 = zzb(var1, "X-Afma-Interstitial-Timeout")) != -1L) {
         this.zzUZ = var12;
      }

      this.zzVa |= zzd(var1, "X-Afma-Mediation");
      if ((var4 = zzc(var1, "X-Afma-Manual-Tracking-Urls")) != null) {
         this.zzRy = var4;
      }

      if ((var12 = zzb(var1, "X-Afma-Refresh-Rate")) != -1L) {
         this.zzVc = var12;
      }

      if ((var4 = (List)var1.get("X-Afma-Orientation")) != null && !var4.isEmpty()) {
         String var5 = (String)var4.get(0);
         if ("portrait".equalsIgnoreCase(var5)) {
            this.mOrientation = com.google.android.gms.ads.internal.zzbs.zzbB().zzhU();
         } else if ("landscape".equalsIgnoreCase(var5)) {
            this.mOrientation = com.google.android.gms.ads.internal.zzbs.zzbB().zzhT();
         }
      }

      this.zzUX = zza(var1, "X-Afma-ActiveView");
      if ((var4 = (List)var1.get("X-Afma-Use-HTTPS")) != null && !var4.isEmpty()) {
         this.zzVf = Boolean.valueOf((String)var4.get(0)).booleanValue();
      }

      this.zzVd |= zzd(var1, "X-Afma-Custom-Rendering-Allowed");
      String var15 = zza(var1, "X-Afma-Ad-Format");
      this.zzVe = "native".equals(var15);
      if ((var4 = (List)var1.get("X-Afma-Content-Url-Opted-Out")) != null && !var4.isEmpty()) {
         this.zzVg = Boolean.valueOf((String)var4.get(0)).booleanValue();
      }

      if ((var4 = (List)var1.get("X-Afma-Content-Vertical-Opted-Out")) != null && !var4.isEmpty()) {
         this.zzVh = Boolean.valueOf((String)var4.get(0)).booleanValue();
      }

      if ((var4 = (List)var1.get("X-Afma-Gws-Query-Id")) != null && !var4.isEmpty()) {
         this.zzVi = (String)var4.get(0);
      }

      if ((var15 = zza(var1, "X-Afma-Fluid")) != null && var15.equals("height")) {
         this.zzVj = true;
      }

      var15 = zza(var1, "X-Afma-Ad-Format");
      this.zzMy = "native_express".equals(var15);
      var15 = zza(var1, "X-Afma-Rewards");
      this.zzVk = zzaee.zzaz(var15);
      if (this.zzVl == null) {
         this.zzVl = zzc(var1, "X-Afma-Reward-Video-Start-Urls");
      }

      if (this.zzVm == null) {
         this.zzVm = zzc(var1, "X-Afma-Reward-Video-Complete-Urls");
      }

      this.zzVn |= zzd(var1, "X-Afma-Use-Displayed-Impression");
      this.zzVp |= zzd(var1, "X-Afma-Auto-Collect-Location");
      this.zzVq = zza(var1, "Set-Cookie");
      zzaby var2 = this;
      JSONObject var14;
      if ((var15 = zza(var1, "X-Afma-Auto-Protection-Configuration")) != null && !TextUtils.isEmpty(var15)) {
         try {
            var14 = new JSONObject(var15);
            var2.zzVo = zzaak.zze(var14);
         } catch (JSONException var10) {
            zzafr.zzc("Error parsing configuration JSON", var10);
            this.zzVo = new zzaak();
         }
      } else {
         Builder var13;
         (var13 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon()).appendQueryParameter("id", "gmob-apps-blocked-navigation");
         if (!TextUtils.isEmpty(this.zzUW)) {
            var13.appendQueryParameter("debugDialog", this.zzUW);
         }

         zzme var8 = zzmo.zzCf;
         boolean var10003 = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).booleanValue();
         String[] var10004 = new String[1];
         String var6 = String.valueOf(var13.toString());
         String var7 = String.valueOf("navigationURL");
         var10004[0] = (new StringBuilder(18 + String.valueOf(var6).length() + String.valueOf(var7).length())).append(var6).append("&").append(var7).append("={NAVIGATION_URL}").toString();
         this.zzVo = new zzaak(var10003, Arrays.asList(var10004));
      }

      if ((var4 = zzc(var1, "X-Afma-Remote-Ping-Urls")) != null) {
         this.zzVr = var4;
      }

      var2 = this;
      if (!TextUtils.isEmpty(var15 = zza(var1, "X-Afma-Safe-Browsing"))) {
         try {
            var14 = new JSONObject(var15);
            var2.zzVu = zzaeq.zzf(var14);
         } catch (JSONException var9) {
            zzafr.zzc("Error parsing safe browsing header", var9);
         }
      }

      this.zzVs |= zzd(var1, "X-Afma-Render-In-Browser");
      var2 = this;
      if (!TextUtils.isEmpty(var15 = zza(var1, "X-Afma-Pool"))) {
         try {
            var14 = new JSONObject(var15);
            var2.zzVv = var14.getBoolean("never_pool");
            return;
         } catch (JSONException var11) {
            zzafr.zzc("Error parsing interstitial pool header", var11);
         }
      }

   }
}
