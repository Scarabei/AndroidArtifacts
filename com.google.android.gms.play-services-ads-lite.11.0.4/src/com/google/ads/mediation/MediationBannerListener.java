package com.google.ads.mediation;

import com.google.ads.AdRequest;

/** @deprecated */
@Deprecated
public interface MediationBannerListener {
   void onReceivedAd(MediationBannerAdapter var1);

   void onFailedToReceiveAd(MediationBannerAdapter var1, AdRequest.ErrorCode var2);

   void onPresentScreen(MediationBannerAdapter var1);

   void onDismissScreen(MediationBannerAdapter var1);

   void onLeaveApplication(MediationBannerAdapter var1);

   void onClick(MediationBannerAdapter var1);
}
