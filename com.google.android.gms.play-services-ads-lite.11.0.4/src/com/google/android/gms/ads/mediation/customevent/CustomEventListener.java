package com.google.android.gms.ads.mediation.customevent;

public interface CustomEventListener {
   void onAdFailedToLoad(int var1);

   void onAdOpened();

   void onAdClicked();

   void onAdClosed();

   void onAdLeftApplication();
}
