package com.google.ads.mediation;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdSize;

/** @deprecated */
@Deprecated
public interface MediationBannerAdapter extends MediationAdapter {
   void requestBannerAd(MediationBannerListener var1, Activity var2, MediationServerParameters var3, AdSize var4, MediationAdRequest var5, NetworkExtras var6);

   View getBannerView();
}
