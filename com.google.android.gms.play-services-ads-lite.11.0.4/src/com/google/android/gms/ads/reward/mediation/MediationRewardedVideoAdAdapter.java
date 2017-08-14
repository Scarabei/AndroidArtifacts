package com.google.android.gms.ads.reward.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;

public interface MediationRewardedVideoAdAdapter extends MediationAdapter {
   String CUSTOM_EVENT_SERVER_PARAMETER_FIELD = "parameter";

   void initialize(Context var1, MediationAdRequest var2, String var3, MediationRewardedVideoAdListener var4, Bundle var5, Bundle var6);

   void loadAd(MediationAdRequest var1, Bundle var2, Bundle var3);

   void showVideo();

   boolean isInitialized();
}
