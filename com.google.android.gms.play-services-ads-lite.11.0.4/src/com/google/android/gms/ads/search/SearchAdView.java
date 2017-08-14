package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class SearchAdView extends ViewGroup {
   private final zzlc zzrZ;

   public SearchAdView(Context var1) {
      super(var1);
      this.zzrZ = new zzlc(this);
   }

   public SearchAdView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.zzrZ = new zzlc(this, var2, false);
   }

   public SearchAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzrZ = new zzlc(this, var2, false);
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

   public final String getAdUnitId() {
      return this.zzrZ.getAdUnitId();
   }

   @RequiresPermission("android.permission.INTERNET")
   public final void loadAd(SearchAdRequest var1) {
      this.zzrZ.zza(var1.zzab());
   }

   @RequiresPermission("android.permission.INTERNET")
   public final void loadAd(DynamicHeightSearchAdRequest var1) {
      if (!AdSize.SEARCH.equals(this.getAdSize())) {
         throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
      } else {
         this.zzrZ.zza(var1.zzab());
      }
   }

   public final void pause() {
      this.zzrZ.pause();
   }

   public final void resume() {
      this.zzrZ.resume();
   }

   public final void setAdListener(AdListener var1) {
      this.zzrZ.setAdListener(var1);
   }

   public final void setAdSize(AdSize var1) {
      this.zzrZ.setAdSizes(var1);
   }

   public final void setAdUnitId(String var1) {
      this.zzrZ.setAdUnitId(var1);
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
