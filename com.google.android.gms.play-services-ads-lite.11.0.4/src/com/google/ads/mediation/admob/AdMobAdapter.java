package com.google.ads.mediation.admob;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;

@Keep
public final class AdMobAdapter extends AbstractAdViewAdapter {
   protected final Bundle zza(Bundle var1, Bundle var2) {
      Bundle var10000 = var1 != null ? var1 : new Bundle();
      Bundle var3 = var10000;
      var10000.putInt("gw", 1);
      var3.putString("mad_hac", var2.getString("mad_hac"));
      if (!TextUtils.isEmpty(var2.getString("adJson"))) {
         var3.putString("_ad", var2.getString("adJson"));
      }

      var3.putBoolean("_noRefresh", true);
      return var3;
   }
}
