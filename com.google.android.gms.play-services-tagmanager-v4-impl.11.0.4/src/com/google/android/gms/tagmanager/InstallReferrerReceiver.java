package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
   protected final Class zzjm() {
      return InstallReferrerService.class;
   }

   protected final void zzu(Context var1, String var2) {
      zzcx.zzfn(var2);
      zzcx.zzK(var1, var2);
   }
}
