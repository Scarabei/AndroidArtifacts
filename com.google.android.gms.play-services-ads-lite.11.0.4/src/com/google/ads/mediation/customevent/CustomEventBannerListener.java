package com.google.ads.mediation.customevent;

import android.view.View;

/** @deprecated */
@Deprecated
public interface CustomEventBannerListener extends CustomEventListener {
   void onReceivedAd(View var1);

   void onClick();
}
