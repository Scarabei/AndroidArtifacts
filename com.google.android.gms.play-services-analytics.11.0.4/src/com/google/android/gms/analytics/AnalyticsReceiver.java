package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzaoj;

public final class AnalyticsReceiver extends BroadcastReceiver {
   private zzaoj zzadl;

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final void onReceive(Context var1, Intent var2) {
      if (this.zzadl == null) {
         this.zzadl = new zzaoj();
      }

      zzaoj.onReceive(var1, var2);
   }
}
