package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzlc;

public final class PublisherAdView extends ViewGroup {
   private final zzlc zzrZ;

   public PublisherAdView(Context var1) {
      super(var1);
      this.zzrZ = new zzlc(this);
   }

   public PublisherAdView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.zzrZ = new zzlc(this, var2, true);
      zzbo.zzb(var1, "Context cannot be null");
   }

   public PublisherAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzrZ = new zzlc(this, var2, true);
   }

   public final VideoController getVideoController() {
      return this.zzrZ.getVideoController();
   }

   public final void setVideoOptions(VideoOptions var1) {
      this.zzrZ.setVideoOptions(var1);
   }

   public final VideoOptions getVideoOptions() {
      return this.zzrZ.getVideoOptions();
   }

   public final void destroy() {
      this.zzrZ.destroy();
   }

   public final AdListener getAdListener() {
      return this.zzrZ.getAdListener();
   }

   public final AdSize getAdSize() {
      return this.zzrZ.getAdSize();
   }

   public final AdSize[] getAdSizes() {
      return this.zzrZ.getAdSizes();
   }

   public final String getAdUnitId() {
      return this.zzrZ.getAdUnitId();
   }

   public final AppEventListener getAppEventListener() {
      return this.zzrZ.getAppEventListener();
   }

   public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
      return this.zzrZ.getOnCustomRenderedAdLoadedListener();
   }

   @RequiresPermission("android.permission.INTERNET")
   public final void loadAd(PublisherAdRequest var1) {
      this.zzrZ.zza(var1.zzab());
   }

   public final void pause() {
      this.zzrZ.pause();
   }

   public final void setManualImpressionsEnabled(boolean var1) {
      this.zzrZ.setManualImpressionsEnabled(var1);
   }

   public final void recordManualImpression() {
      this.zzrZ.recordManualImpression();
   }

   public final void resume() {
      this.zzrZ.resume();
   }

   public final void setAdListener(AdListener var1) {
      this.zzrZ.setAdListener(var1);
   }

   public final void setAdSizes(AdSize... var1) {
      if (var1 != null && var1.length > 0) {
         this.zzrZ.zza(var1);
      } else {
         throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
      }
   }

   public final void setAdUnitId(String var1) {
      this.zzrZ.setAdUnitId(var1);
   }

   public final void setAppEventListener(AppEventListener var1) {
      this.zzrZ.setAppEventListener(var1);
   }

   public final void setCorrelator(Correlator var1) {
      this.zzrZ.setCorrelator(var1);
   }

   public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener var1) {
      this.zzrZ.setOnCustomRenderedAdLoadedListener(var1);
   }

   public final String getMediationAdapterClassName() {
      return this.zzrZ.getMediationAdapterClassName();
   }

   public final boolean isLoading() {
      return this.zzrZ.isLoading();
   }

   public final boolean zza(zzjz var1) {
      return this.zzrZ.zza(var1);
   }

   protected final void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      View var6;
      if ((var6 = this.getChildAt(0)) != null && var6.getVisibility() != 8) {
         int var7 = var6.getMeasuredWidth();
         int var8 = var6.getMeasuredHeight();
         int var9 = (var4 - var2 - var7) / 2;
         int var10 = (var5 - var3 - var8) / 2;
         var6.layout(var9, var10, var9 + var7, var10 + var8);
      }

   }

   protected final void onMeasure(int var1, int var2) {
      int var3 = 0;
      int var4 = 0;
      View var5;
      if ((var5 = this.getChildAt(0)) != null && var5.getVisibility() != 8) {
         this.measureChild(var5, var1, var2);
         var3 = var5.getMeasuredWidth();
         var4 = var5.getMeasuredHeight();
      } else {
         AdSize var6 = null;

         try {
            var6 = this.getAdSize();
         } catch (NullPointerException var8) {
            zzajc.zzb("Unable to retrieve ad size.", var8);
         }

         if (var6 != null) {
            Context var7 = this.getContext();
            var3 = var6.getWidthInPixels(var7);
            var4 = var6.getHeightInPixels(var7);
         }
      }

      var3 = Math.max(var3, this.getSuggestedMinimumWidth());
      var4 = Math.max(var4, this.getSuggestedMinimumHeight());
      this.setMeasuredDimension(View.resolveSize(var3, var1), View.resolveSize(var4, var2));
   }
}
