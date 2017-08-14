package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Build.VERSION;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzafk implements zzags, zzgy {
   private final Object mLock = new Object();
   private final String mSessionId;
   private final zzafl zzYq;
   private zzfh zzsR;
   private BigInteger zzYr;
   private final HashSet zzYs;
   private final HashMap zzYt;
   private boolean zzYu;
   private boolean zzVf;
   private int zzYv;
   private boolean zzuH;
   private Context mContext;
   private zzaje zztW;
   private zzmr zzYw;
   private boolean zzVg;
   private boolean zzVh;
   private zzgz zzYx;
   private zzgu zzyy;
   private String zzYy;
   private String zzYz;
   private Boolean zzYA;
   private String zzJP;
   private boolean zzYB;
   private boolean zzYC;
   private boolean zzVp;
   private boolean zzYD;
   private String zzYE;
   private long zzYF;
   private long zzYG;
   private long zzYH;
   private int zzYI;
   private JSONObject zzYJ;
   private int zzYK;
   private final AtomicInteger zzYL;

   public final String getSessionId() {
      return this.mSessionId;
   }

   public final zzgz zzA(Context var1) {
      zzme var4 = zzmo.zzCT;
      if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
         return null;
      } else {
         var4 = zzmo.zzDb;
         if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
            var4 = zzmo.zzCZ;
            if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
               return null;
            }
         }

         if (this.zzhn() && this.zzho()) {
            return null;
         } else {
            Object var2 = this.mLock;
            synchronized(this.mLock) {
               if (Looper.getMainLooper() != null && var1 != null) {
                  if (this.zzyy == null) {
                     this.zzyy = new zzgu();
                  }

                  if (this.zzYx == null) {
                     this.zzYx = new zzgz(this.zzyy, zzzi.zzc(this.mContext, this.zztW));
                  }

                  this.zzYx.zzcM();
                  return this.zzYx;
               } else {
                  return null;
               }
            }
         }
      }
   }

   public final void zzx(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzVg != var1) {
            zzaft.zzg(this.mContext, var1);
         }

         this.zzVg = var1;
         zzgz var3;
         if ((var3 = this.zzA(this.mContext)) != null && !var3.isAlive()) {
            zzafr.zzaS("start fetching content...");
            var3.zzcM();
         }

      }
   }

   public final void zzy(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzVh != var1) {
            zzaft.zzg(this.mContext, var1);
         }

         zzaft.zzg(this.mContext, var1);
         this.zzVh = var1;
         zzgz var3;
         if ((var3 = this.zzA(this.mContext)) != null && !var3.isAlive()) {
            zzafr.zzaS("start fetching content...");
            var3.zzcM();
         }

      }
   }

   public final boolean zzhn() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzVg;
      }
   }

   public final boolean zzho() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzVh;
      }
   }

   public final String zzhp() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         String var2 = this.zzYr.toString();
         this.zzYr = this.zzYr.add(BigInteger.ONE);
         return var2;
      }
   }

   public final void zza(zzafh var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYs.add(var1);
      }
   }

   public final zzafl zzhq() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYq;
      }
   }

   public final zzmr zzhr() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYw;
      }
   }

   public final void zzb(HashSet var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYs.addAll(var1);
      }
   }

   public final void zza(String var1, zzafn var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzYt.put(var1, var2);
      }
   }

   public final Future zze(Context var1, boolean var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (var2 != this.zzVf) {
            this.zzVf = var2;
            return zzaft.zze(var1, var2);
         } else {
            return null;
         }
      }
   }

   public final boolean zzhs() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzVf || this.zzYC;
      }
   }

   public final String zzht() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzJP;
      }
   }

   public final Future zzaF(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (var1 != null && !var1.equals(this.zzYy)) {
            this.zzYy = var1;
            return zzaft.zzp(this.mContext, var1);
         } else {
            return null;
         }
      }
   }

   public final Future zzaG(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (var1 != null && !var1.equals(this.zzYz)) {
            this.zzYz = var1;
            return zzaft.zzq(this.mContext, var1);
         } else {
            return null;
         }
      }
   }

   public final String zzhu() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYy;
      }
   }

   public final String zzhv() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYz;
      }
   }

   public final void zza(Boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYA = var1;
      }
   }

   public final Boolean zzhw() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYA;
      }
   }

   public final Future zzf(Context var1, boolean var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (var2 != this.zzVp) {
            this.zzVp = var2;
            return zzaft.zzh(var1, var2);
         } else {
            return null;
         }
      }
   }

   public final boolean zzhx() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzVp;
      }
   }

   public final Future zzn(Context var1, String var2) {
      this.zzYF = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (var2 != null && !var2.equals(this.zzYE)) {
            this.zzYE = var2;
            return zzaft.zza(var1, var2, this.zzYF);
         } else {
            return null;
         }
      }
   }

   private final Future zzj(long var1) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzYG = var1;
         return zzaft.zza(this.mContext, var1);
      }
   }

   final long zzhy() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYG;
      }
   }

   final Future zzk(long var1) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzYH = var1;
         return zzaft.zzb(this.mContext, var1);
      }
   }

   final long zzhz() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYH;
      }
   }

   public final Future zzx(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYK = var1;
         return zzaft.zza(this.mContext, var1);
      }
   }

   public final int zzhA() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYK;
      }
   }

   private final Future zzy(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYI = var1;
         return zzaft.zzb(this.mContext, var1);
      }
   }

   final int zzhB() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYI;
      }
   }

   public final void zzz(boolean var1) {
      this.zzYD = var1;
   }

   public final boolean zzhC() {
      return this.zzYD;
   }

   public final zzafj zzhD() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return new zzafj(this.zzYE, this.zzYF);
      }
   }

   public final Future zzb(Context var1, String var2, String var3, boolean var4) {
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         JSONArray var6;
         if ((var6 = this.zzYJ.optJSONArray(var2)) == null) {
            var6 = new JSONArray();
         }

         int var7 = var6.length();

         for(int var8 = 0; var8 < var6.length(); ++var8) {
            JSONObject var9;
            if ((var9 = var6.optJSONObject(var8)) != null && var3.equals(var9.optString("template_id"))) {
               if (var4 && var9.optBoolean("uses_media_view", false) == var4) {
                  return null;
               }

               var7 = var8;
               break;
            }
         }

         try {
            JSONObject var13;
            (var13 = new JSONObject()).put("template_id", var3);
            var13.put("uses_media_view", var4);
            var13.put("timestamp_ms", com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis());
            var6.put(var7, var13);
            this.zzYJ.put(var2, var6);
         } catch (JSONException var11) {
            zzafr.zzc("Could not update native advanced settings", var11);
         }

         return zzaft.zzr(this.mContext, this.zzYJ.toString());
      }
   }

   public final JSONObject zzhE() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYJ;
      }
   }

   public final Future zzhF() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return zzaft.zzC(this.mContext);
      }
   }

   @TargetApi(23)
   public final void zzd(Context var1, zzaje var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzuH) {
            this.mContext = var1.getApplicationContext();
            this.zztW = var2;
            com.google.android.gms.ads.internal.zzbs.zzbC().zza(this);
            zzaft.zza(var1, this);
            zzaft.zzb(var1, this);
            zzaft.zzh(var1, this);
            zzaft.zzf(var1, this);
            zzaft.zzc(var1, this);
            zzaft.zzd(var1, this);
            zzaft.zze(var1, this);
            zzaft.zzg(var1, this);
            zzaft.zzi(var1, this);
            zzaft.zzj(var1, this);
            zzaft.zzk(var1, this);
            zzzi.zzc(this.mContext, this.zztW);
            this.zzJP = com.google.android.gms.ads.internal.zzbs.zzbz().zzs(var1, var2.zzaP);
            if (VERSION.SDK_INT >= 23 && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
               this.zzYC = true;
            }

            this.zzsR = new zzfh(var1.getApplicationContext(), this.zztW, com.google.android.gms.ads.internal.zzbs.zzbz().zze(var1, var2));
            zzafk var5 = this;
            zzmq var6 = new zzmq(this.mContext, this.zztW.zzaP);

            try {
               com.google.android.gms.ads.internal.zzbs.zzbG();
               var5.zzYw = zzmt.zza(var6);
            } catch (IllegalArgumentException var8) {
               zzafr.zzc("Cannot initialize CSI reporter.", var8);
            }

            this.zzuH = true;
         }

      }
   }

   public final zzfh zzhG() {
      return this.zzsR;
   }

   public final Bundle zza(Context var1, zzafm var2, String var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         Bundle var5;
         (var5 = new Bundle()).putBundle("app", this.zzYq.zzo(var1, var3));
         Bundle var6 = new Bundle();
         Iterator var7 = this.zzYt.keySet().iterator();

         while(var7.hasNext()) {
            String var8 = (String)var7.next();
            var6.putBundle(var8, ((zzafn)this.zzYt.get(var8)).toBundle());
         }

         var5.putBundle("slots", var6);
         ArrayList var12 = new ArrayList();
         Iterator var13 = this.zzYs.iterator();

         while(var13.hasNext()) {
            zzafh var9 = (zzafh)var13.next();
            var12.add(var9.toBundle());
         }

         var5.putParcelableArrayList("ads", var12);
         var2.zza(this.zzYs);
         this.zzYs.clear();
         return var5;
      }
   }

   public final Resources getResources() {
      if (this.zztW.zzaaQ) {
         return this.mContext.getResources();
      } else {
         try {
            DynamiteModule var1;
            return (var1 = DynamiteModule.zza(this.mContext, DynamiteModule.zzaSL, "com.google.android.gms.ads.dynamite")) != null ? var1.zztC().getResources() : null;
         } catch (zzc var2) {
            zzafr.zzc("Cannot load resource from dynamite apk or local jar", var2);
            return null;
         }
      }
   }

   public final void zza(Throwable var1, String var2) {
      zzzi.zzc(this.mContext, this.zztW).zza(var1, var2);
   }

   public zzafk(zzagz var1) {
      this.zzYr = BigInteger.ONE;
      this.zzYs = new HashSet();
      this.zzYt = new HashMap();
      this.zzYu = false;
      this.zzVf = true;
      this.zzYv = 0;
      this.zzuH = false;
      this.zzYw = null;
      this.zzVg = true;
      this.zzVh = true;
      this.zzYx = null;
      this.zzyy = null;
      this.zzYA = null;
      this.zzYB = false;
      this.zzYC = false;
      this.zzVp = false;
      this.zzYD = false;
      this.zzYE = "";
      this.zzYF = 0L;
      this.zzYG = 0L;
      this.zzYH = 0L;
      this.zzYI = -1;
      this.zzYJ = new JSONObject();
      this.zzYK = 0;
      this.zzYL = new AtomicInteger(0);
      this.mSessionId = zzagz.zzhP();
      this.zzYq = new zzafl(this.mSessionId);
   }

   public final void zzf(Bundle var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzVf = var1.getBoolean("use_https", this.zzVf);
         this.zzYv = var1.getInt("webview_cache_version", this.zzYv);
         if (var1.containsKey("content_url_opted_out")) {
            this.zzx(var1.getBoolean("content_url_opted_out"));
         }

         if (var1.containsKey("content_url_hashes")) {
            this.zzYy = var1.getString("content_url_hashes");
         }

         this.zzVp = var1.getBoolean("auto_collect_location", this.zzVp);
         if (var1.containsKey("content_vertical_opted_out")) {
            this.zzy(var1.getBoolean("content_vertical_opted_out"));
         }

         if (var1.containsKey("content_vertical_hashes")) {
            this.zzYz = var1.getString("content_vertical_hashes");
         }

         if (var1.containsKey("native_advanced_settings")) {
            try {
               this.zzYJ = new JSONObject(var1.getString("native_advanced_settings"));
            } catch (JSONException var5) {
               zzafr.zzc("Could not convert native advanced settings to json object", var5);
            }
         }

         if (var1.containsKey("version_code")) {
            this.zzYK = var1.getInt("version_code");
         }

         this.zzYE = var1.containsKey("app_settings_json") ? var1.getString("app_settings_json") : this.zzYE;
         this.zzYF = var1.getLong("app_settings_last_update_ms", this.zzYF);
         this.zzYG = var1.getLong("app_last_background_time_ms", this.zzYG);
         this.zzYI = var1.getInt("request_in_session_count", this.zzYI);
         this.zzYH = var1.getLong("first_ad_req_time_ms", this.zzYH);
      }
   }

   public final void zzf(boolean var1) {
      long var2 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
      if (var1) {
         long var10000 = var2 - this.zzYG;
         zzme var4 = zzmo.zzDL;
         if (var10000 > ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).longValue()) {
            this.zzYq.zzYI = -1;
         } else {
            int var5 = this.zzYI;
            this.zzYq.zzYI = this.zzYI;
         }
      } else {
         this.zzj(var2);
         this.zzy(this.zzYq.zzYI);
      }
   }

   public final void zzhH() {
      this.zzYL.incrementAndGet();
   }

   public final void zzhI() {
      this.zzYL.decrementAndGet();
   }

   public final int zzhJ() {
      return this.zzYL.get();
   }
}
