package com.google.android.gms.ads.mediation;

public interface MediationInterstitialListener {
   void onAdLoaded(MediationInterstitialAdapter var1);

   void onAdFailedToLoad(MediationInterstitialAdapter var1, int var2);

   void onAdOpened(MediationInterstitialAdapter var1);

   void onAdClosed(MediationInterstitialAdapter var1);

   void onAdLeftApplication(MediationInterstitialAdapter var1);

   void onAdClicked(MediationInterstitialAdapter var1);
}
