package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzajc;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
   private View zzdf;
   private CustomEventBanner zzdg;
   private CustomEventInterstitial zzdh;

   private static Object zzh(String var0) {
      try {
         return Class.forName(var0).newInstance();
      } catch (Throwable var2) {
         String var1 = String.valueOf(var2.getMessage());
         zzajc.zzaT((new StringBuilder(46 + String.valueOf(var0).length() + String.valueOf(var1).length())).append("Could not instantiate custom event adapter: ").append(var0).append(". ").append(var1).toString());
         return null;
      }
   }

   public final void destroy() {
      if (this.zzdg != null) {
         this.zzdg.destroy();
      }

      if (this.zzdh != null) {
         this.zzdh.destroy();
      }

   }

   public final Class getAdditionalParametersType() {
      return CustomEventExtras.class;
   }

   public final View getBannerView() {
      return this.zzdf;
   }

   public final Class getServerParametersType() {
      return CustomEventServerParameters.class;
   }

   public final void requestBannerAd(MediationBannerListener var1, Activity var2, CustomEventServerParameters var3, AdSize var4, MediationAdRequest var5, CustomEventExtras var6) {
      this.zzdg = (CustomEventBanner)zzh(var3.className);
      if (this.zzdg == null) {
         var1.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      } else {
         Object var7 = var6 == null ? null : var6.getExtra(var3.label);
         this.zzdg.requestBannerAd(new CustomEventAdapter.zza(this, var1), var2, var3.label, var3.parameter, var4, var5, var7);
      }
   }

   public final void requestInterstitialAd(MediationInterstitialListener var1, Activity var2, CustomEventServerParameters var3, MediationAdRequest var4, CustomEventExtras var5) {
      this.zzdh = (CustomEventInterstitial)zzh(var3.className);
      if (this.zzdh == null) {
         var1.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      } else {
         Object var6 = var5 == null ? null : var5.getExtra(var3.label);
         this.zzdh.requestInterstitialAd(new CustomEventAdapter.zzb(this, var1), var2, var3.label, var3.parameter, var4, var6);
      }
   }

   public final void showInterstitial() {
      this.zzdh.showInterstitial();
   }

   private final void zza(View var1) {
      this.zzdf = var1;
   }

   class zzb implements CustomEventInterstitialListener {
      private final CustomEventAdapter zzdi;
      private final MediationInterstitialListener zzdk;
      // $FF: synthetic field
      private CustomEventAdapter zzdl;

      public zzb(CustomEventAdapter var2, MediationInterstitialListener var3) {
         this.zzdl = CustomEventAdapter.this;
         super();
         this.zzdi = var2;
         this.zzdk = var3;
      }

      public final void onReceivedAd() {
         zzajc.zzaC("Custom event adapter called onReceivedAd.");
         this.zzdk.onReceivedAd(this.zzdl);
      }

      public final void onFailedToReceiveAd() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdk.onFailedToReceiveAd(this.zzdi, AdRequest.ErrorCode.NO_FILL);
      }

      public final void onPresentScreen() {
         zzajc.zzaC("Custom event adapter called onPresentScreen.");
         this.zzdk.onPresentScreen(this.zzdi);
      }

      public final void onDismissScreen() {
         zzajc.zzaC("Custom event adapter called onDismissScreen.");
         this.zzdk.onDismissScreen(this.zzdi);
      }

      public final void onLeaveApplication() {
         zzajc.zzaC("Custom event adapter called onLeaveApplication.");
         this.zzdk.onLeaveApplication(this.zzdi);
      }
   }

   static final class zza implements CustomEventBannerListener {
      private final CustomEventAdapter zzdi;
      private final MediationBannerListener zzdj;

      public zza(CustomEventAdapter var1, MediationBannerListener var2) {
         this.zzdi = var1;
         this.zzdj = var2;
      }

      public final void onReceivedAd(View var1) {
         zzajc.zzaC("Custom event adapter called onReceivedAd.");
         this.zzdi.zza(var1);
         this.zzdj.onReceivedAd(this.zzdi);
      }

      public final void onFailedToReceiveAd() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdj.onFailedToReceiveAd(this.zzdi, AdRequest.ErrorCode.NO_FILL);
      }

      public final void onClick() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdj.onClick(this.zzdi);
      }

      public final void onPresentScreen() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdj.onPresentScreen(this.zzdi);
      }

      public final void onDismissScreen() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdj.onDismissScreen(this.zzdi);
      }

      public final void onLeaveApplication() {
         zzajc.zzaC("Custom event adapter called onFailedToReceiveAd.");
         this.zzdj.onLeaveApplication(this.zzdi);
      }
   }
}
