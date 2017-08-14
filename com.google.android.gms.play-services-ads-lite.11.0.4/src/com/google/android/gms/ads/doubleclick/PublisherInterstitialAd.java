package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzle;

public final class PublisherInterstitialAd {
   private final zzle zzsb;

   public PublisherInterstitialAd(Context var1) {
      this.zzsb = new zzle(var1, this);
      zzbo.zzb(var1, "Context cannot be null");
   }

   public final AdListener getAdListener() {
      return this.zzsb.getAdListener();
   }

   public final String getAdUnitId() {
      return this.zzsb.getAdUnitId();
   }

   public final AppEventListener getAppEventListener() {
      return this.zzsb.getAppEventListener();
   }

   public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
      return this.zzsb.getOnCustomRenderedAdLoadedListener();
   }

   public final boolean isLoaded() {
      return this.zzsb.isLoaded();
   }

   public final boolean isLoading() {
      return this.zzsb.isLoading();
   }

   @RequiresPermission("android.permission.INTERNET")
   public final void loadAd(PublisherAdRequest var1) {
      this.zzsb.zza(var1.zzab());
   }

   public final void setAdListener(AdListener var1) {
      this.zzsb.setAdListener(var1);
   }

   public final void setAdUnitId(String var1) {
      this.zzsb.setAdUnitId(var1);
   }

   public final void setAppEventListener(AppEventListener var1) {
      this.zzsb.setAppEventListener(var1);
   }

   public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener var1) {
      this.zzsb.setOnCustomRenderedAdLoadedListener(var1);
   }

   public final void setCorrelator(Correlator var1) {
      this.zzsb.setCorrelator(var1);
   }

   public final String getMediationAdapterClassName() {
      return this.zzsb.getMediationAdapterClassName();
   }

   public final void show() {
      this.zzsb.show();
   }

   public final void setImmersiveMode(boolean var1) {
      this.zzsb.setImmersiveMode(var1);
   }
}
