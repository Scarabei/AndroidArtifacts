package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener {
   // $FF: synthetic field
   private AbstractAdViewAdapter zzcT;

   zza(AbstractAdViewAdapter var1) {
      this.zzcT = var1;
      super();
   }

   public final void onRewardedVideoAdLoaded() {
      AbstractAdViewAdapter.zza(this.zzcT).onAdLoaded(this.zzcT);
   }

   public final void onRewardedVideoAdOpened() {
      AbstractAdViewAdapter.zza(this.zzcT).onAdOpened(this.zzcT);
   }

   public final void onRewardedVideoStarted() {
      AbstractAdViewAdapter.zza(this.zzcT).onVideoStarted(this.zzcT);
   }

   public final void onRewardedVideoAdClosed() {
      AbstractAdViewAdapter.zza(this.zzcT).onAdClosed(this.zzcT);
      AbstractAdViewAdapter.zza((AbstractAdViewAdapter)this.zzcT, (InterstitialAd)null);
   }

   public final void onRewarded(RewardItem var1) {
      AbstractAdViewAdapter.zza(this.zzcT).onRewarded(this.zzcT, var1);
   }

   public final void onRewardedVideoAdLeftApplication() {
      AbstractAdViewAdapter.zza(this.zzcT).onAdLeftApplication(this.zzcT);
   }

   public final void onRewardedVideoAdFailedToLoad(int var1) {
      AbstractAdViewAdapter.zza(this.zzcT).onAdFailedToLoad(this.zzcT, var1);
   }
}
