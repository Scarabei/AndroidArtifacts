package com.google.ads.mediation;

import com.google.ads.AdRequest;

/** @deprecated */
@Deprecated
public interface MediationInterstitialListener {
   void onReceivedAd(MediationInterstitialAdapter var1);

   void onFailedToReceiveAd(MediationInterstitialAdapter var1, AdRequest.ErrorCode var2);

   void onPresentScreen(MediationInterstitialAdapter var1);

   void onDismissScreen(MediationInterstitialAdapter var1);

   void onLeaveApplication(MediationInterstitialAdapter var1);
}
