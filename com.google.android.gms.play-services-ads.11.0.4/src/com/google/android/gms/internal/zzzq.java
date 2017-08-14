package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzzq extends zzafp implements zzzw {
   private final zzzp zzSm;
   private final zzaaf zzSn;
   private final Object zzQT = new Object();
   private final Context mContext;
   private final zzij zzSo;
   private zzaae zzMM;
   private Runnable zzQS;
   zzahp zzSp;
   private zzaai zzQR;
   private zzub zzMu;

   public zzzq(Context var1, zzaaf var2, zzzp var3, zzij var4) {
      this.zzSm = var3;
      this.mContext = var1;
      this.zzSn = var2;
      this.zzSo = var4;
   }

   final zzahp zza(zzaje var1, zzajp var2) {
      Context var3;
      Context var10000 = var3 = this.mContext;
      zzzv var11 = new zzzv(var3);
      Context var7 = var10000;
      if (var11.zza(var1)) {
         zzafr.zzaC("Fetching ad response from local ad request service.");
         zzaab var15;
         (var15 = new zzaab(var7, var2, this)).zzgp();
         return var15;
      } else {
         zzafr.zzaC("Fetching ad response from remote ad request service.");
         zzji.zzds();
         if (!zzaiy.zzX(var7)) {
            zzafr.zzaT("Failed to connect to remote ad request service.");
            return null;
         } else {
            return new zzaac(var7, var1, var2, this);
         }
      }
   }

   public final void zzbd() {
      zzafr.zzaC("AdLoaderBackgroundTask started.");
      this.zzQS = new zzzr(this);
      zzme var7 = zzmo.zzEK;
      zzagz.zzZr.postDelayed(this.zzQS, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).longValue());
      long var1 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
      var7 = zzmo.zzEI;
      String var3;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).booleanValue() && this.zzSn.zzSz.extras != null && (var3 = this.zzSn.zzSz.extras.getString("_ad")) != null) {
         this.zzMM = new zzaae(this.zzSn, var1, (String)null, (String)null, (String)null);
         this.zza(zzabt.zza(this.mContext, this.zzMM, var3));
      } else {
         zzajt var8 = new zzajt();
         zzagt.zza((Runnable)(new zzzs(this, var8)));
         String var4 = com.google.android.gms.ads.internal.zzbs.zzbY().zzu(this.mContext);
         String var5 = com.google.android.gms.ads.internal.zzbs.zzbY().zzv(this.mContext);
         String var6 = com.google.android.gms.ads.internal.zzbs.zzbY().zzw(this.mContext);
         com.google.android.gms.ads.internal.zzbs.zzbY().zzh(this.mContext, var6);
         this.zzMM = new zzaae(this.zzSn, var1, var4, var5, var6);
         var8.zzf(this.zzMM);
      }
   }

   private final void zzd(int var1, String var2) {
      if (var1 != 3 && var1 != -1) {
         zzafr.zzaT(var2);
      } else {
         zzafr.zzaS(var2);
      }

      if (this.zzQR == null) {
         this.zzQR = new zzaai(var1);
      } else {
         this.zzQR = new zzaai(var1, this.zzQR.zzMg);
      }

      zzaae var3;
      if (this.zzMM != null) {
         var3 = this.zzMM;
      } else {
         var3 = new zzaae(this.zzSn, -1L, (String)null, (String)null, (String)null);
      }

      zzafg var4 = new zzafg(var3, this.zzQR, this.zzMu, (zziv)null, var1, -1L, this.zzQR.zzTs, (JSONObject)null, this.zzSo);
      this.zzSm.zza(var4);
   }

   public final void zza(@NonNull zzaai var1) {
      zzafr.zzaC("Received ad response.");
      this.zzQR = var1;
      long var2 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
      Object var4 = this.zzQT;
      synchronized(this.zzQT) {
         this.zzSp = null;
      }

      com.google.android.gms.ads.internal.zzbs.zzbD().zzf(this.mContext, this.zzQR.zzSV);
      zzme var8 = zzmo.zzDY;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).booleanValue()) {
         String var9;
         String var11;
         SharedPreferences var12;
         Set var13;
         Set var14;
         HashSet var15;
         Editor var16;
         if (this.zzQR.zzTh) {
            com.google.android.gms.ads.internal.zzbs.zzbD();
            var9 = this.zzMM.zzvR;
            var11 = this.zzMM.zzvR;
            var12 = this.mContext.getSharedPreferences("admob", 0);
            var13 = Collections.emptySet();
            if (!(var14 = var12.getStringSet("never_pool_slots", var13)).contains(var11)) {
               (var15 = new HashSet(var14)).add(var11);
               (var16 = var12.edit()).putStringSet("never_pool_slots", var15);
               var16.apply();
            }
         } else {
            com.google.android.gms.ads.internal.zzbs.zzbD();
            var9 = this.zzMM.zzvR;
            var11 = this.zzMM.zzvR;
            var12 = this.mContext.getSharedPreferences("admob", 0);
            var13 = Collections.emptySet();
            if ((var14 = var12.getStringSet("never_pool_slots", var13)).contains(var11)) {
               (var15 = new HashSet(var14)).remove(var11);
               (var16 = var12.edit()).putStringSet("never_pool_slots", var15);
               var16.apply();
            }
         }
      }

      zziv var5 = null;

      try {
         if (this.zzQR.errorCode != -2 && this.zzQR.errorCode != -3) {
            int var21 = this.zzQR.errorCode;
            throw new zzzt((new StringBuilder(66)).append("There was a problem getting an ad response. ErrorCode: ").append(var21).toString(), this.zzQR.errorCode);
         }

         zzzq var22 = this;
         if (this.zzQR.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzQR.body)) {
               throw new zzzt("No fill from ad server.", 3);
            }

            com.google.android.gms.ads.internal.zzbs.zzbD().zze(this.mContext, this.zzQR.zzSH);
            if (this.zzQR.zzTo) {
               try {
                  var22.zzMu = new zzub(var22.zzQR.body);
                  com.google.android.gms.ads.internal.zzbs.zzbD().zzz(var22.zzMu.zzMe);
               } catch (JSONException var19) {
                  zzafr.zzb("Could not parse mediation config.", var19);
                  zzzt var10000 = new zzzt;
                  String var10003 = String.valueOf(this.zzQR.body);
                  String var10002;
                  if (var10003.length() != 0) {
                     var10002 = "Could not parse mediation config: ".concat(var10003);
                  } else {
                     String var10004 = new String;
                     var10002 = var10004;
                     var10004.<init>("Could not parse mediation config: ");
                  }

                  var10000.<init>(var10002, 0);
                  throw var10000;
               }
            } else {
               com.google.android.gms.ads.internal.zzbs.zzbD().zzz(this.zzQR.zzMe);
            }

            if (!TextUtils.isEmpty(this.zzQR.zzSW)) {
               zzme var23 = zzmo.zzGf;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var23)).booleanValue()) {
                  zzafr.zzaC("Received cookie from server. Setting webview cookie in CookieManager.");
                  CookieManager var10;
                  if ((var10 = com.google.android.gms.ads.internal.zzbs.zzbB().zzS(this.mContext)) != null) {
                     var10.setCookie("googleads.g.doubleclick.net", this.zzQR.zzSW);
                  }
               }
            }
         }

         if (this.zzMM.zzvX.zzAu != null) {
            var5 = this.zzb(this.zzMM);
         }
      } catch (zzzt var20) {
         this.zzd(var20.getErrorCode(), var20.getMessage());
         zzagz.zzZr.removeCallbacks(this.zzQS);
         return;
      }

      com.google.android.gms.ads.internal.zzbs.zzbD().zzx(this.zzQR.zzTy);
      com.google.android.gms.ads.internal.zzbs.zzbD().zzy(this.zzQR.zzTL);
      JSONObject var6 = null;
      if (!TextUtils.isEmpty(this.zzQR.zzTw)) {
         try {
            var6 = new JSONObject(this.zzQR.zzTw);
         } catch (Exception var17) {
            zzafr.zzb("Error parsing the JSON for Active View.", var17);
         }
      }

      zzafg var7 = new zzafg(this.zzMM, this.zzQR, this.zzMu, var5, -2, var2, this.zzQR.zzTs, var6, this.zzSo);
      this.zzSm.zza(var7);
      zzagz.zzZr.removeCallbacks(this.zzQS);
   }

   public final void onStop() {
      Object var1 = this.zzQT;
      synchronized(this.zzQT) {
         if (this.zzSp != null) {
            this.zzSp.cancel();
         }

      }
   }

   private final zziv zzb(zzaae var1) throws zzzt {
      if (this.zzMM != null && this.zzMM.zzwn != null && this.zzMM.zzwn.size() > 1 && this.zzMu != null && !this.zzMu.zzMp) {
         return null;
      } else {
         int var3;
         if (this.zzQR.zzAw) {
            zziv[] var2 = var1.zzvX.zzAu;
            var3 = var1.zzvX.zzAu.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               zziv var5;
               if ((var5 = var2[var4]).zzAw) {
                  return new zziv(var5, var1.zzvX.zzAu);
               }
            }
         }

         if (this.zzQR.zzTr == null) {
            throw new zzzt("The ad response must specify one of the supported ad sizes.", 0);
         } else {
            String[] var15;
            zzzt var10000;
            String var10002;
            String var10003;
            String var10004;
            if ((var15 = this.zzQR.zzTr.split("x")).length != 2) {
               var10000 = new zzzt;
               var10003 = String.valueOf(this.zzQR.zzTr);
               if (var10003.length() != 0) {
                  var10002 = "Invalid ad size format from the ad response: ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Invalid ad size format from the ad response: ");
               }

               var10000.<init>(var10002, 0);
               throw var10000;
            } else {
               int var14;
               try {
                  var14 = Integer.parseInt(var15[0]);
                  var3 = Integer.parseInt(var15[1]);
               } catch (NumberFormatException var13) {
                  var10000 = new zzzt;
                  var10003 = String.valueOf(this.zzQR.zzTr);
                  if (var10003.length() != 0) {
                     var10002 = "Invalid ad size number from the ad response: ".concat(var10003);
                  } else {
                     var10004 = new String;
                     var10002 = var10004;
                     var10004.<init>("Invalid ad size number from the ad response: ");
                  }

                  var10000.<init>(var10002, 0);
                  throw var10000;
               }

               zziv[] var16 = var1.zzvX.zzAu;
               int var6 = var1.zzvX.zzAu.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  zziv var8 = var16[var7];
                  float var9 = this.mContext.getResources().getDisplayMetrics().density;
                  int var10 = var8.width == -1 ? (int)((float)var8.widthPixels / var9) : var8.width;
                  int var11 = var8.height == -2 ? (int)((float)var8.heightPixels / var9) : var8.height;
                  if (var14 == var10 && var3 == var11 && !var8.zzAw) {
                     return new zziv(var8, var1.zzvX.zzAu);
                  }
               }

               var10000 = new zzzt;
               var10003 = String.valueOf(this.zzQR.zzTr);
               if (var10003.length() != 0) {
                  var10002 = "The ad size from the ad response was not one of the requested sizes: ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("The ad size from the ad response was not one of the requested sizes: ");
               }

               var10000.<init>(var10002, 0);
               throw var10000;
            }
         }
      }
   }

   // $FF: synthetic method
   static Object zza(zzzq var0) {
      return var0.zzQT;
   }

   // $FF: synthetic method
   static void zza(zzzq var0, int var1, String var2) {
      var0.zzd(var1, var2);
   }

   // $FF: synthetic method
   static zzaaf zzb(zzzq var0) {
      return var0.zzSn;
   }

   // $FF: synthetic method
   static Runnable zzc(zzzq var0) {
      return var0.zzQS;
   }
}
