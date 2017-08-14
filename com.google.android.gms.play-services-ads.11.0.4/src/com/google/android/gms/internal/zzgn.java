package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class zzgn implements zzgr {
   // $FF: synthetic field
   private Activity val$activity;

   zzgn(zzgj var1, Activity var2) {
      this.val$activity = var2;
      super();
   }

   public final void zza(ActivityLifecycleCallbacks var1) {
      var1.onActivityPaused(this.val$activity);
   }
}
