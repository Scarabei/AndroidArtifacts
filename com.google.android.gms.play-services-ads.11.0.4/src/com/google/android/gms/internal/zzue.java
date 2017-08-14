package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzue implements zzui {
   private final String zzMs;
   private final zzuq zzsX;
   private final long zzMt;
   private final zzub zzMu;
   private final zzua zzMv;
   private zzir zzuT;
   private final zziv zzuZ;
   private final Context mContext;
   private final Object mLock = new Object();
   private final zzaje zztW;
   private final boolean zzwJ;
   private final zzon zztS;
   private final List zztT;
   private final List zzMw;
   private final List zzMx;
   private final boolean zzMy;
   private zzut zzMz;
   private int zzMA = -2;
   private zzuz zzMB;

   public zzue(Context var1, String var2, zzuq var3, zzub var4, zzua var5, zzir var6, zziv var7, zzaje var8, boolean var9, boolean var10, zzon var11, List var12, List var13, List var14) {
      this.mContext = var1;
      this.zzsX = var3;
      this.zzMv = var5;
      if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(var2)) {
         this.zzMs = this.zzfj();
      } else {
         this.zzMs = var2;
      }

      this.zzMu = var4;
      this.zzMt = var4.zzLZ != -1L ? var4.zzLZ : 10000L;
      this.zzuT = var6;
      this.zzuZ = var7;
      this.zztW = var8;
      this.zzwJ = var9;
      this.zzMy = var10;
      this.zztS = var11;
      this.zztT = var12;
      this.zzMw = var13;
      this.zzMx = var14;
   }

   public final void cancel() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         try {
            if (this.zzMz != null) {
               this.zzMz.destroy();
            }
         } catch (RemoteException var4) {
            zzafr.zzc("Could not destroy mediation adapter.", var4);
         }

         this.zzMA = -1;
         this.mLock.notify();
      }
   }

   private final String zzfj() {
      try {
         if (!TextUtils.isEmpty(this.zzMv.zzLL)) {
            if (this.zzsX.zzai(this.zzMv.zzLL)) {
               return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
            }

            return "com.google.ads.mediation.customevent.CustomEventAdapter";
         }
      } catch (RemoteException var1) {
         zzafr.zzaT("Fail to determine the custom event's version, assuming the old one.");
      }

      return "com.google.ads.mediation.customevent.CustomEventAdapter";
   }

   public final void zzo(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzMA = var1;
         this.mLock.notify();
      }
   }

   public final void zza(int var1, zzuz var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzMA = 0;
         this.zzMB = var2;
         this.mLock.notify();
      }
   }

   private static zzut zza(MediationAdapter var0) {
      return new zzvj(var0);
   }

   public final zzuh zza(long var1, long var3) {
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         long var6 = SystemClock.elapsedRealtime();
         zzud var8 = new zzud();
         zzagz.zzZr.post(new zzuf(this, var8));
         long var17 = var3;
         long var15 = var1;
         long var13 = this.zzMt;
         zzue var12 = this;

         while(true) {
            while(var12.zzMA == -2) {
               zzue var19 = var12;
               long var28 = SystemClock.elapsedRealtime();
               long var30 = var13 - (var28 - var6);
               long var32 = var17 - (var28 - var15);
               if (var30 > 0L && var32 > 0L) {
                  try {
                     var19.mLock.wait(Math.min(var30, var32));
                  } catch (InterruptedException var34) {
                     var12.zzMA = -1;
                  }
               } else {
                  zzafr.zzaS("Timed out waiting for adapter.");
                  var12.zzMA = 3;
               }
            }

            long var9 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() - var6;
            return new zzuh(this.zzMv, this.zzMz, this.zzMs, var8, this.zzMA, this.zzfk(), var9);
         }
      }
   }

   private final zzuz zzfk() {
      if (this.zzMA == 0 && this.zzfm()) {
         try {
            if (this.zzp(4) && this.zzMB != null && this.zzMB.zzfo() != 0) {
               return this.zzMB;
            }
         } catch (RemoteException var2) {
            zzafr.zzaT("Could not get cpm value from MediationResponseMetadata");
         }

         int var1 = this.zzfn();
         return new zzug(var1);
      } else {
         return null;
      }
   }

   private final zzut zzfl() {
      String var10001 = String.valueOf(this.zzMs);
      String var10000;
      String var10002;
      if (var10001.length() != 0) {
         var10000 = "Instantiating mediation adapter: ".concat(var10001);
      } else {
         var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Instantiating mediation adapter: ");
      }

      zzafr.zzaS(var10000);
      if (!this.zzwJ && !this.zzMv.zzfi()) {
         zzme var2 = zzmo.zzEG;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
            return zza((MediationAdapter)(new AdMobAdapter()));
         }

         var2 = zzmo.zzEH;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzMs)) {
            return zza((MediationAdapter)(new AdUrlAdapter()));
         }

         if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzMs)) {
            return new zzvj(new zzwd());
         }
      }

      try {
         return this.zzsX.zzah(this.zzMs);
      } catch (RemoteException var3) {
         var10001 = String.valueOf(this.zzMs);
         if (var10001.length() != 0) {
            var10000 = "Could not instantiate mediation adapter: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Could not instantiate mediation adapter: ");
         }

         zzafr.zza(var10000, var3);
         return null;
      }
   }

   private final void zza(zzud var1) {
      String var2 = this.zzaf(this.zzMv.zzLP);

      try {
         if (this.zztW.zzaaP < 4100000) {
            if (this.zzuZ.zzAt) {
               this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, var2, var1);
            } else {
               this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, var2, var1);
            }
         } else if (!this.zzwJ && !this.zzMv.zzfi()) {
            if (this.zzuZ.zzAt) {
               this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, var2, this.zzMv.zzLH, var1);
            } else if (this.zzMy) {
               if (this.zzMv.zzLS != null) {
                  this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, var2, this.zzMv.zzLH, var1, new zzon(zzag(this.zzMv.zzLW)), this.zzMv.zzLV);
               } else {
                  this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, var2, this.zzMv.zzLH, var1);
               }
            } else {
               this.zzMz.zza(zzn.zzw(this.mContext), this.zzuZ, this.zzuT, var2, this.zzMv.zzLH, var1);
            }
         } else {
            ArrayList var3 = new ArrayList(this.zztT);
            String var5;
            String var6;
            if (this.zzMw != null) {
               for(Iterator var4 = this.zzMw.iterator(); var4.hasNext(); var3.add((new StringBuilder(7 + String.valueOf(var5).length() + String.valueOf(var6).length())).append("custom:").append(var5).append(var6).toString())) {
                  var5 = (String)var4.next();
                  var6 = ":false";
                  if (this.zzMx != null && this.zzMx.contains(var5)) {
                     var6 = ":true";
                  }
               }
            }

            this.zzMz.zza(zzn.zzw(this.mContext), this.zzuT, var2, this.zzMv.zzLH, var1, this.zztS, var3);
         }
      } catch (RemoteException var8) {
         zzafr.zzc("Could not request ad from mediation adapter.", var8);
         this.zzo(5);
      }
   }

   private final boolean zzp(int var1) {
      Bundle var2;
      try {
         if (this.zzwJ) {
            var2 = this.zzMz.zzft();
         } else if (this.zzuZ.zzAt) {
            var2 = this.zzMz.getInterstitialAdapterInfo();
         } else {
            var2 = this.zzMz.zzfs();
         }
      } catch (RemoteException var4) {
         zzafr.zzaT("Could not get adapter info. Returning false");
         return false;
      }

      if (var2 != null) {
         int var3 = var2.getInt("capabilities", 0);
         return (var1 & var3) == var1;
      } else {
         return false;
      }
   }

   private final boolean zzfm() {
      return this.zzMu.zzMj != -1;
   }

   private final String zzaf(String var1) {
      if (var1 != null && this.zzfm() && !this.zzp(2)) {
         try {
            JSONObject var2;
            (var2 = new JSONObject(var1)).remove("cpm_floor_cents");
            return var2.toString();
         } catch (JSONException var3) {
            zzafr.zzaT("Could not remove field. Returning the original value");
            return var1;
         }
      } else {
         return var1;
      }
   }

   private final int zzfn() {
      if (this.zzMv.zzLP == null) {
         return 0;
      } else {
         JSONObject var1;
         try {
            var1 = new JSONObject(this.zzMv.zzLP);
         } catch (JSONException var3) {
            zzafr.zzaT("Could not convert to json. Returning 0");
            return 0;
         }

         if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzMs)) {
            return var1.optInt("cpm_cents", 0);
         } else {
            int var2 = 0;
            if (this.zzp(2)) {
               var2 = var1.optInt("cpm_floor_cents", 0);
            }

            if (var2 == 0) {
               var2 = var1.optInt("penalized_average_cpm_cents", 0);
            }

            return var2;
         }
      }
   }

   private static NativeAdOptions zzag(String var0) {
      Builder var1 = new Builder();
      if (var0 == null) {
         return var1.build();
      } else {
         try {
            JSONObject var2 = new JSONObject(var0);
            var1.setRequestMultipleImages(var2.optBoolean("multiple_images", false));
            var1.setReturnUrlsForImageAssets(var2.optBoolean("only_urls", false));
            String var3 = var2.optString("native_image_orientation", "any");
            var1.setImageOrientation("landscape".equals(var3) ? 2 : ("portrait".equals(var3) ? 1 : ("any".equals(var3) ? 0 : -1)));
         } catch (JSONException var5) {
            zzafr.zzc("Exception occurred when creating native ad options", var5);
         }

         return var1.build();
      }
   }

   // $FF: synthetic method
   static Object zza(zzue var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static int zzb(zzue var0) {
      return var0.zzMA;
   }

   // $FF: synthetic method
   static zzut zza(zzue var0, zzut var1) {
      return var0.zzMz = var1;
   }

   // $FF: synthetic method
   static zzut zzc(zzue var0) {
      return var0.zzfl();
   }

   // $FF: synthetic method
   static zzut zzd(zzue var0) {
      return var0.zzMz;
   }

   // $FF: synthetic method
   static boolean zze(zzue var0) {
      return var0.zzfm();
   }

   // $FF: synthetic method
   static boolean zza(zzue var0, int var1) {
      return var0.zzp(1);
   }

   // $FF: synthetic method
   static String zzf(zzue var0) {
      return var0.zzMs;
   }

   // $FF: synthetic method
   static void zza(zzue var0, zzud var1) {
      var0.zza(var1);
   }
}
