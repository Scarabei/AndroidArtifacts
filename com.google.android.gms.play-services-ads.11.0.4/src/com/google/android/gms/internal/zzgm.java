package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class zzgm implements zzgr {
   // $FF: synthetic field
   private Activity val$activity;

   zzgm(zzgj var1, Activity var2) {
      this.val$activity = var2;
      super();
   }

   public final void zza(ActivityLifecycleCallbacks var1) {
      var1.onActivityResumed(this.val$activity);
   }
}
