package com.google.ads.mediation;

import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;

@Keep
public final class AdUrlAdapter extends AbstractAdViewAdapter implements com.google.android.gms.ads.mediation.MediationBannerAdapter, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, MediationNativeAdapter {
   protected final Bundle zza(Bundle var1, Bundle var2) {
      Bundle var10000 = var1 != null ? var1 : new Bundle();
      Bundle var3 = var10000;
      var10000.putBundle("sdk_less_server_data", var2);
      var3.putBoolean("_noRefresh", true);
      return var3;
   }

   public final String getAdUnitId(Bundle var1) {
      return "adurl";
   }
}
