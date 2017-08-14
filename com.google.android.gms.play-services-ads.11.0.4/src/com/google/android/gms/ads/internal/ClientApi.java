package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzacr;
import com.google.android.gms.internal.zzacy;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzki;
import com.google.android.gms.internal.zzkn;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzop;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zztq;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzwx;
import com.google.android.gms.internal.zzxj;
import com.google.android.gms.internal.zzzn;

@zzzn
@Keep
@DynamiteApi
@KeepForSdkWithMembers
public class ClientApi extends zzki {
   public zzjz createBannerAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException {
      Context var6 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1);
      zzbs.zzbz();
      zzaje var7 = new zzaje(11020000, var5, true, zzagz.zzO(var6));
      return new zzx(var6, var2, var3, var4, var7, zzv.zzaQ());
   }

   public zzjz createSearchAdManager(IObjectWrapper var1, zziv var2, String var3, int var4) throws RemoteException {
      Context var5 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1);
      zzbs.zzbz();
      zzaje var6 = new zzaje(11020000, var4, true, zzagz.zzO(var5));
      return new zzbm(var5, var2, var3, var6);
   }

   public zzjz createInterstitialAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException {
      boolean var10000;
      Context var6;
      zzaje var7;
      label33: {
         zzmo.initialize(var6 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1));
         zzbs.zzbz();
         var7 = new zzaje(11020000, var5, true, zzagz.zzO(var6));
         boolean var8;
         zzme var9;
         if (!(var8 = "reward_mb".equals(var2.zzAs))) {
            var9 = zzmo.zzDY;
            if (((Boolean)zzbs.zzbL().zzd(var9)).booleanValue()) {
               break label33;
            }
         }

         if (var8) {
            var9 = zzmo.zzDZ;
            if (((Boolean)zzbs.zzbL().zzd(var9)).booleanValue()) {
               break label33;
            }
         }

         var10000 = false;
         return (zzjz)(var10000 ? new zztq(var6, var3, var4, var7, zzv.zzaQ()) : new zzal(var6, var2, var3, var4, var7, zzv.zzaQ()));
      }

      var10000 = true;
      return (zzjz)(var10000 ? new zztq(var6, var3, var4, var7, zzv.zzaQ()) : new zzal(var6, var2, var3, var4, var7, zzv.zzaQ()));
   }

   public zzju createAdLoaderBuilder(IObjectWrapper var1, String var2, zzuq var3, int var4) {
      Context var5 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1);
      zzbs.zzbz();
      zzaje var6 = new zzaje(11020000, var4, true, zzagz.zzO(var5));
      return new zzak(var5, var2, var3, var6, zzv.zzaQ());
   }

   @Nullable
   public zzkn getMobileAdsSettingsManager(IObjectWrapper var1) {
      return null;
   }

   public zzkn getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper var1, int var2) {
      Context var3 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1);
      zzbs.zzbz();
      zzaje var4 = new zzaje(11020000, var2, true, zzagz.zzO(var3));
      return zzax.zza(var3, var4);
   }

   public zzow createNativeAdViewDelegate(IObjectWrapper var1, IObjectWrapper var2) {
      FrameLayout var3 = (FrameLayout)com.google.android.gms.dynamic.zzn.zzE(var1);
      FrameLayout var4 = (FrameLayout)com.google.android.gms.dynamic.zzn.zzE(var2);
      return new zzop(var3, var4);
   }

   public zzacy createRewardedVideoAd(IObjectWrapper var1, zzuq var2, int var3) {
      Context var4 = (Context)com.google.android.gms.dynamic.zzn.zzE(var1);
      zzbs.zzbz();
      zzaje var5 = new zzaje(11020000, var3, true, zzagz.zzO(var4));
      return new zzacr(var4, zzv.zzaQ(), var2, var5);
   }

   public zzxj createInAppPurchaseManager(IObjectWrapper var1) {
      return null;
   }

   public zzwx createAdOverlay(IObjectWrapper var1) {
      Activity var2 = (Activity)com.google.android.gms.dynamic.zzn.zzE(var1);
      return new com.google.android.gms.ads.internal.overlay.zzm(var2);
   }
}
