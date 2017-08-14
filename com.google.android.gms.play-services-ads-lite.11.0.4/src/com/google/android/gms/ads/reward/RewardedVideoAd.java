package com.google.android.gms.ads.reward;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;

public interface RewardedVideoAd {
   void loadAd(String var1, AdRequest var2);

   boolean isLoaded();

   void show();

   void setRewardedVideoAdListener(RewardedVideoAdListener var1);

   /** @deprecated */
   @Deprecated
   void setUserId(String var1);

   RewardedVideoAdListener getRewardedVideoAdListener();

   /** @deprecated */
   @Deprecated
   String getUserId();

   /** @deprecated */
   @Deprecated
   void pause();

   void pause(Context var1);

   /** @deprecated */
   @Deprecated
   void resume();

   void resume(Context var1);

   /** @deprecated */
   @Deprecated
   void destroy();

   void destroy(Context var1);

   String getMediationAdapterClassName();

   void setImmersiveMode(boolean var1);
}
