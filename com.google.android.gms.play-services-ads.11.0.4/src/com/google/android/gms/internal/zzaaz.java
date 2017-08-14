package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.js.zza;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.js.zzy;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaaz extends zzafp {
   private static long zzTW;
   private static final Object zzuF;
   private static boolean zzTX;
   private static zzl zzRl;
   private static zzre zzTY;
   private static zzrn zzTZ;
   private static zzrd zzUa;
   private final zzzp zzSm;
   private final zzaaf zzSn;
   private final Object zzQT = new Object();
   private final Context mContext;
   private zzy zzUb;
   private zzij zzSo;

   public zzaaz(Context var1, zzaaf var2, zzzp var3, zzij var4) {
      super(true);
      this.zzSm = var3;
      this.mContext = var1;
      this.zzSn = var2;
      this.zzSo = var4;
      Object var5 = zzuF;
      synchronized(zzuF) {
         if (!zzTX) {
            zzTZ = new zzrn();
            zzTY = new zzre(var1.getApplicationContext(), var2.zzvT);
            zzUa = new zzabh();
            Context var10002 = this.mContext.getApplicationContext();
            zzme var7 = zzmo.zzBX;
            zzRl = new zzl(var10002, this.zzSn.zzvT, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7), new zzabg(), new zzabf());
            zzTX = true;
         }

      }
   }

   protected static void zza(zza var0) {
      var0.zza("/loadAd", zzTZ);
      var0.zza("/fetchHttpRequest", zzTY);
      var0.zza("/invalidRequest", zzUa);
   }

   protected static void zzb(zza var0) {
      var0.zzb("/loadAd", zzTZ);
      var0.zzb("/fetchHttpRequest", zzTY);
      var0.zzb("/invalidRequest", zzUa);
   }

   public final void zzbd() {
      zzafr.zzaC("SdkLessAdLoaderBackgroundTask started.");
      String var1 = com.google.android.gms.ads.internal.zzbs.zzbY().zzw(this.mContext);
      zzaae var2 = new zzaae(this.zzSn, -1L, com.google.android.gms.ads.internal.zzbs.zzbY().zzu(this.mContext), com.google.android.gms.ads.internal.zzbs.zzbY().zzv(this.mContext), var1);
      com.google.android.gms.ads.internal.zzbs.zzbY().zzh(this.mContext, var1);
      zzaai var3 = this.zzd(var2);
      long var4 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
      zzafg var6 = new zzafg(var2, var3, (zzub)null, (zziv)null, var3.errorCode, var4, var3.zzTs, (JSONObject)null, this.zzSo);
      zzaiy.zzaaH.post(new zzaba(this, var6));
   }

   private final zzaai zzd(zzaae var1) {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      String var2 = zzagz.zzhO();
      JSONObject var3;
      if ((var3 = this.zza(var1, var2)) == null) {
         return new zzaai(0);
      } else {
         long var4 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
         Future var6 = zzTZ.zzS(var2);
         zzaiy.zzaaH.post(new zzabb(this, var3, var2));
         long var8 = zzTW - (com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() - var4);

         JSONObject var7;
         try {
            var7 = (JSONObject)var6.get(var8, TimeUnit.MILLISECONDS);
         } catch (InterruptedException | CancellationException var11) {
            return new zzaai(-1);
         } catch (TimeoutException var12) {
            return new zzaai(2);
         } catch (ExecutionException var13) {
            return new zzaai(0);
         }

         if (var7 == null) {
            return new zzaai(-1);
         } else {
            zzaai var10;
            return (var10 = zzabt.zza(this.mContext, var1, var7.toString())).errorCode != -3 && TextUtils.isEmpty(var10.body) ? new zzaai(3) : var10;
         }
      }
   }

   private final JSONObject zza(zzaae var1, String var2) {
      Bundle var3;
      if ((var3 = var1.zzSz.extras.getBundle("sdk_less_server_data")) == null) {
         return null;
      } else {
         zzacb var4 = null;

         try {
            var4 = (zzacb)com.google.android.gms.ads.internal.zzbs.zzbI().zzn(this.mContext).get();
         } catch (Exception var12) {
            zzafr.zzc("Error grabbing device info: ", var12);
         }

         Context var10000 = this.mContext;
         zzabk var8;
         (var8 = new zzabk()).zzUj = var1;
         (var8 = var8).zzUk = var4;
         JSONObject var5;
         if ((var5 = zzabt.zza(var10000, var8)) == null) {
            return null;
         } else {
            Info var6 = null;

            try {
               var6 = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
            } catch (IllegalStateException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException var11) {
               zzafr.zzc("Cannot get advertising id info", var11);
            }

            HashMap var7;
            (var7 = new HashMap()).put("request_id", var2);
            var7.put("request_param", var5);
            var7.put("data", var3);
            if (var6 != null) {
               var7.put("adid", var6.getId());
               var7.put("lat", var6.isLimitAdTrackingEnabled() ? 1 : 0);
            }

            try {
               return com.google.android.gms.ads.internal.zzbs.zzbz().zzj(var7);
            } catch (JSONException var10) {
               return null;
            }
         }
      }
   }

   public final void onStop() {
      Object var1 = this.zzQT;
      synchronized(this.zzQT) {
         zzaiy.zzaaH.post(new zzabe(this));
      }
   }

   // $FF: synthetic method
   static zzrn zzgD() {
      return zzTZ;
   }

   // $FF: synthetic method
   static zzzp zza(zzaaz var0) {
      return var0.zzSm;
   }

   // $FF: synthetic method
   static zzy zzb(zzaaz var0) {
      return var0.zzUb;
   }

   // $FF: synthetic method
   static zzy zza(zzaaz var0, zzy var1) {
      return var0.zzUb = var1;
   }

   // $FF: synthetic method
   static zzl zzgE() {
      return zzRl;
   }

   static {
      zzTW = TimeUnit.SECONDS.toMillis(10L);
      zzuF = new Object();
      zzTX = false;
      zzRl = null;
      zzTY = null;
      zzTZ = null;
      zzUa = null;
   }
}
