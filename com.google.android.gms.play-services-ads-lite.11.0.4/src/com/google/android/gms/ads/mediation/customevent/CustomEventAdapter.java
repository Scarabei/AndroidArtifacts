package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzajc;

@KeepName
@KeepForSdkWithMembers
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
   private View zzdf;
   private CustomEventBanner zzacO;
   private CustomEventInterstitial zzacP;
   private CustomEventNative zzacQ;

   private static Object zzh(String var0) {
      try {
         return Class.forName(var0).newInstance();
      } catch (Throwable var2) {
         String var1 = String.valueOf(var2.getMessage());
         zzajc.zzaT((new StringBuilder(46 + String.valueOf(var0).length() + String.valueOf(var1).length())).append("Could not instantiate custom event adapter: ").append(var0).append(". ").append(var1).toString());
         return null;
      }
   }

   public final void onDestroy() {
      if (this.zzacO != null) {
         this.zzacO.onDestroy();
      }

      if (this.zzacP != null) {
         this.zzacP.onDestroy();
      }

      if (this.zzacQ != null) {
         this.zzacQ.onDestroy();
      }

   }

   public final void onPause() {
      if (this.zzacO != null) {
         this.zzacO.onPause();
      }

      if (this.zzacP != null) {
         this.zzacP.onPause();
      }

      if (this.zzacQ != null) {
         this.zzacQ.onPause();
      }

   }

   public final void onResume() {
      if (this.zzacO != null) {
         this.zzacO.onResume();
      }

      if (this.zzacP != null) {
         this.zzacP.onResume();
      }

      if (this.zzacQ != null) {
         this.zzacQ.onResume();
      }

   }

   public final View getBannerView() {
      return this.zzdf;
   }

   public final void requestBannerAd(Context var1, MediationBannerListener var2, Bundle var3, AdSize var4, MediationAdRequest var5, Bundle var6) {
      this.zzacO = (CustomEventBanner)zzh(var3.getString("class_name"));
      if (this.zzacO == null) {
         var2.onAdFailedToLoad(this, 0);
      } else {
         Bundle var7 = var6 == null ? null : var6.getBundle(var3.getString("class_name"));
         this.zzacO.requestBannerAd(var1, new CustomEventAdapter.zza(this, var2), var3.getString("parameter"), var4, var5, var7);
      }
   }

   public final void requestInterstitialAd(Context var1, MediationInterstitialListener var2, Bundle var3, MediationAdRequest var4, Bundle var5) {
      this.zzacP = (CustomEventInterstitial)zzh(var3.getString("class_name"));
      if (this.zzacP == null) {
         var2.onAdFailedToLoad(this, 0);
      } else {
         Bundle var6 = var5 == null ? null : var5.getBundle(var3.getString("class_name"));
         this.zzacP.requestInterstitialAd(var1, new CustomEventAdapter.zzb(this, var2), var3.getString("parameter"), var4, var6);
      }
   }

   public final void requestNativeAd(Context var1, MediationNativeListener var2, Bundle var3, NativeMediationAdRequest var4, Bundle var5) {
      this.zzacQ = (CustomEventNative)zzh(var3.getString("class_name"));
      if (this.zzacQ == null) {
         var2.onAdFailedToLoad(this, 0);
      } else {
         Bundle var6 = var5 == null ? null : var5.getBundle(var3.getString("class_name"));
         this.zzacQ.requestNativeAd(var1, new CustomEventAdapter.zzc(this, var2), var3.getString("parameter"), var4, var6);
      }
   }

   public final void showInterstitial() {
      this.zzacP.showInterstitial();
   }

   private final void zza(View var1) {
      this.zzdf = var1;
   }

   static class zzc implements CustomEventNativeListener {
      private final CustomEventAdapter zzacR;
      private final MediationNativeListener zzcZ;

      public zzc(CustomEventAdapter var1, MediationNativeListener var2) {
         this.zzacR = var1;
         this.zzcZ = var2;
      }

      public final void onAdLoaded(NativeAdMapper var1) {
         zzajc.zzaC("Custom event adapter called onAdLoaded.");
         this.zzcZ.onAdLoaded(this.zzacR, var1);
      }

      public final void onAdFailedToLoad(int var1) {
         zzajc.zzaC("Custom event adapter called onAdFailedToLoad.");
         this.zzcZ.onAdFailedToLoad(this.zzacR, var1);
      }

      public final void onAdOpened() {
         zzajc.zzaC("Custom event adapter called onAdOpened.");
         this.zzcZ.onAdOpened(this.zzacR);
      }

      public final void onAdClicked() {
         zzajc.zzaC("Custom event adapter called onAdClicked.");
         this.zzcZ.onAdClicked(this.zzacR);
      }

      public final void onAdClosed() {
         zzajc.zzaC("Custom event adapter called onAdClosed.");
         this.zzcZ.onAdClosed(this.zzacR);
      }

      public final void onAdLeftApplication() {
         zzajc.zzaC("Custom event adapter called onAdLeftApplication.");
         this.zzcZ.onAdLeftApplication(this.zzacR);
      }

      public final void onAdImpression() {
         zzajc.zzaC("Custom event adapter called onAdImpression.");
         this.zzcZ.onAdImpression(this.zzacR);
      }
   }

   class zzb implements CustomEventInterstitialListener {
      private final CustomEventAdapter zzacR;
      private final MediationInterstitialListener zzcY;
      // $FF: synthetic field
      private CustomEventAdapter zzacS;

      public zzb(CustomEventAdapter var2, MediationInterstitialListener var3) {
         this.zzacS = CustomEventAdapter.this;
         super();
         this.zzacR = var2;
         this.zzcY = var3;
      }

      public final void onAdLoaded() {
         zzajc.zzaC("Custom event adapter called onReceivedAd.");
         this.zzcY.onAdLoaded(this.zzacS);
      }

      public final void onAdFailedToLoad(int var1) {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzcY.onAdFailedToLoad(this.zzacR, var1);
      }

      public final void onAdClicked() {
         zzajc.zzaC("Custom event adapter called onAdClicked.");
         this.zzcY.onAdClicked(this.zzacR);
      }

      public final void onAdOpened() {
         zzajc.zzaC("Custom event adapter called onAdOpened.");
         this.zzcY.onAdOpened(this.zzacR);
      }

      public final void onAdClosed() {
         zzajc.zzaC("Custom event adapter called onAdClosed.");
         this.zzcY.onAdClosed(this.zzacR);
      }

      public final void onAdLeftApplication() {
         zzajc.zzaC("Custom event adapter called onAdLeftApplication.");
         this.zzcY.onAdLeftApplication(this.zzacR);
      }
   }

   static final class zza implements CustomEventBannerListener {
      private final CustomEventAdapter zzacR;
      private final MediationBannerListener zzcX;

      public zza(CustomEventAdapter var1, MediationBannerListener var2) {
         this.zzacR = var1;
         this.zzcX = var2;
      }

      public final void onAdLoaded(View var1) {
         zzajc.zzaC("Custom event adapter called onAdLoaded.");
         this.zzacR.zza(var1);
         this.zzcX.onAdLoaded(this.zzacR);
      }

      public final void onAdFailedToLoad(int var1) {
         zzajc.zzaC("Custom event adapter called onAdFailedToLoad.");
         this.zzcX.onAdFailedToLoad(this.zzacR, var1);
      }

      public final void onAdClicked() {
         zzajc.zzaC("Custom event adapter called onAdClicked.");
         this.zzcX.onAdClicked(this.zzacR);
      }

      public final void onAdOpened() {
         zzajc.zzaC("Custom event adapter called onAdOpened.");
         this.zzcX.onAdOpened(this.zzacR);
      }

      public final void onAdClosed() {
         zzajc.zzaC("Custom event adapter called onAdClosed.");
         this.zzcX.onAdClosed(this.zzacR);
      }

      public final void onAdLeftApplication() {
         zzajc.zzaC("Custom event adapter called onAdLeftApplication.");
         this.zzcX.onAdLeftApplication(this.zzacR);
      }
   }
}
