package com.google.ads.mediation.customevent;

/** @deprecated */
@Deprecated
public interface CustomEventListener {
   void onFailedToReceiveAd();

   void onPresentScreen();

   void onDismissScreen();

   void onLeaveApplication();
}
