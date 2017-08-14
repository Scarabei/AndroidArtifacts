package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzahw {
   private final Object mLock = new Object();
   private String zzZK = "";
   private String zzZL = "";
   private boolean zzZM = false;
   private String zzZN = "";

   public final void zzf(Context var1, String var2, String var3) {
      if (!this.zzh(var1, var2, var3)) {
         this.zza(var1, "In-app preview failed to load because of a system error. Please try again later.", true, true);
      } else if ("2".equals(this.zzZN)) {
         zzafr.zzaC("Creative is not pushed for this device.");
         this.zza(var1, "There was no creative pushed from DFP to the device.", false, false);
      } else if ("1".equals(this.zzZN)) {
         zzafr.zzaC("The app is not linked for creative preview.");
         this.zzk(var1, var2, var3);
      } else {
         if ("0".equals(this.zzZN)) {
            zzafr.zzaC("Device is linked for in app preview.");
            this.zza(var1, "The device is successfully linked for creative preview.", false, true);
         }

      }
   }

   public final void zzg(Context var1, String var2, String var3) {
      if (this.zzi(var1, var2, var3)) {
         zzafr.zzaC("Device is linked for debug signals.");
         this.zza(var1, "The device is successfully linked for troubleshooting.", false, true);
      } else {
         this.zzk(var1, var2, var3);
      }
   }

   private final boolean zzh(Context var1, String var2, String var3) {
      zzme var7 = zzmo.zzGu;
      String var4;
      if (TextUtils.isEmpty(var4 = zzj(var1, this.zzb(var1, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7), var2, var3).toString(), var3))) {
         zzafr.zzaC("Not linked for in app preview.");
         return false;
      } else {
         var4 = var4.trim();

         String var5;
         try {
            JSONObject var6;
            var5 = (var6 = new JSONObject(var4)).optString("gct");
            this.zzZN = var6.optString("status");
         } catch (JSONException var12) {
            zzafr.zzc("Fail to get in app preview response json.", var12);
            return false;
         }

         String var8 = var5;
         zzahw var13 = this;
         synchronized(this.mLock) {
            var13.zzZL = var8;
            return true;
         }
      }
   }

   private final boolean zzi(Context var1, String var2, String var3) {
      zzme var7 = zzmo.zzGv;
      String var4;
      if (TextUtils.isEmpty(var4 = zzj(var1, this.zzb(var1, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7), var2, var3).toString(), var3))) {
         zzafr.zzaC("Not linked for debug signals.");
         return false;
      } else {
         var4 = var4.trim();

         String var5;
         try {
            var5 = (new JSONObject(var4)).optString("debug_mode");
         } catch (JSONException var11) {
            zzafr.zzc("Fail to get debug mode response json.", var11);
            return false;
         }

         boolean var6 = "1".equals(var5);
         zzahw var12 = this;
         Object var8 = this.mLock;
         synchronized(this.mLock) {
            var12.zzZM = var6;
            return var6;
         }
      }
   }

   private static String zzj(Context var0, String var1, String var2) {
      HashMap var3;
      (var3 = new HashMap()).put("User-Agent", com.google.android.gms.ads.internal.zzbs.zzbz().zzs(var0, var2));
      zzajm var4 = (new zzaie(var0)).zzb(var1, var3);

      String var10000;
      String var10001;
      String var10002;
      try {
         zzme var6 = zzmo.zzGx;
         return (String)var4.get((long)((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).intValue(), TimeUnit.MILLISECONDS);
      } catch (TimeoutException var7) {
         var10001 = String.valueOf(var1);
         if (var10001.length() != 0) {
            var10000 = "Timeout while retriving a response from: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Timeout while retriving a response from: ");
         }

         zzafr.zzb(var10000, var7);
         var4.cancel(true);
      } catch (InterruptedException var8) {
         var10001 = String.valueOf(var1);
         if (var10001.length() != 0) {
            var10000 = "Interrupted while retriving a response from: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Interrupted while retriving a response from: ");
         }

         zzafr.zzb(var10000, var8);
         var4.cancel(true);
      } catch (Exception var9) {
         var10001 = String.valueOf(var1);
         if (var10001.length() != 0) {
            var10000 = "Error retriving a response from: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error retriving a response from: ");
         }

         zzafr.zzb(var10000, var9);
      }

      return null;
   }

   private final void zzk(Context var1, String var2, String var3) {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzme var4 = zzmo.zzGt;
      zzagz.zza(var1, this.zzb(var1, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4), var2, var3));
   }

   public final void zza(Context var1, String var2, String var3, String var4) {
      zzme var6 = zzmo.zzGw;
      Builder var5;
      (var5 = this.zzb(var1, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6), var4, var2).buildUpon()).appendQueryParameter("debugData", var3);
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.zzd(var1, var2, var5.build().toString());
   }

   private final Uri zzb(Context var1, String var2, String var3, String var4) {
      Builder var5;
      (var5 = Uri.parse(var2).buildUpon()).appendQueryParameter("linkedDeviceId", this.zzT(var1));
      var5.appendQueryParameter("adSlotPath", var3);
      var5.appendQueryParameter("afmaVersion", var4);
      return var5.build();
   }

   private final String zzT(Context var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (TextUtils.isEmpty(this.zzZK)) {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            this.zzZK = zzagz.zzt(var1, "debug_signals_id.txt");
            if (TextUtils.isEmpty(this.zzZK)) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               this.zzZK = zzagz.zzhO();
               com.google.android.gms.ads.internal.zzbs.zzbz();
               zzagz.zze(var1, "debug_signals_id.txt", this.zzZK);
            }
         }

         return this.zzZK;
      }
   }

   public final String zzib() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzZL;
      }
   }

   public final boolean zzic() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzZM;
      }
   }

   private final void zza(Context var1, String var2, boolean var3, boolean var4) {
      if (!(var1 instanceof Activity)) {
         zzafr.zzaS("Can not create dialog without Activity Context");
      } else {
         zzagz.zzZr.post(new zzahx(this, var1, var2, var3, var4));
      }
   }
}
