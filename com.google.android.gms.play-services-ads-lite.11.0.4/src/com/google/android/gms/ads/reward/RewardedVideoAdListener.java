package com.google.android.gms.ads.reward;

public interface RewardedVideoAdListener {
   void onRewardedVideoAdLoaded();

   void onRewardedVideoAdOpened();

   void onRewardedVideoStarted();

   void onRewardedVideoAdClosed();

   void onRewarded(RewardItem var1);

   void onRewardedVideoAdLeftApplication();

   void onRewardedVideoAdFailedToLoad(int var1);
}
