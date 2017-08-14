package com.google.android.gms.ads.mediation;

public interface MediationBannerListener {
   void onAdLoaded(MediationBannerAdapter var1);

   void onAdFailedToLoad(MediationBannerAdapter var1, int var2);

   void onAdOpened(MediationBannerAdapter var1);

   void onAdClosed(MediationBannerAdapter var1);

   void onAdLeftApplication(MediationBannerAdapter var1);

   void onAdClicked(MediationBannerAdapter var1);

   void zza(MediationBannerAdapter var1, String var2, String var3);
}
