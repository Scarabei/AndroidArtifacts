package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzle;

public final class InterstitialAd {
   private final zzle zzsb;

   public InterstitialAd(Context var1) {
      this.zzsb = new zzle(var1);
      zzbo.zzb(var1, "Context cannot be null");
   }

   public final AdListener getAdListener() {
      return this.zzsb.getAdListener();
   }

   public final String getAdUnitId() {
      return this.zzsb.getAdUnitId();
   }

   public final boolean isLoaded() {
      return this.zzsb.isLoaded();
   }

   public final boolean isLoading() {
      return this.zzsb.isLoading();
   }

   @RequiresPermission("android.permission.INTERNET")
   public final void loadAd(AdRequest var1) {
      this.zzsb.zza(var1.zzab());
   }

   public final void setAdListener(AdListener var1) {
      this.zzsb.setAdListener(var1);
      if (var1 != null && var1 instanceof zzim) {
         this.zzsb.zza((zzim)var1);
      } else {
         if (var1 == null) {
            this.zzsb.zza((zzim)null);
         }

      }
   }

   public final void setAdUnitId(String var1) {
      this.zzsb.setAdUnitId(var1);
   }

   public final String getMediationAdapterClassName() {
      return this.zzsb.getMediationAdapterClassName();
   }

   public final void show() {
      this.zzsb.show();
   }

   public final void setRewardedVideoAdListener(RewardedVideoAdListener var1) {
      this.zzsb.setRewardedVideoAdListener(var1);
   }

   public final void zza(boolean var1) {
      this.zzsb.zza(true);
   }

   public final void setImmersiveMode(boolean var1) {
      this.zzsb.setImmersiveMode(var1);
   }
}
