package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzgp implements zzgr {
   // $FF: synthetic field
   private Activity val$activity;
   // $FF: synthetic field
   private Bundle zzxV;

   zzgp(zzgj var1, Activity var2, Bundle var3) {
      this.val$activity = var2;
      this.zzxV = var3;
      super();
   }

   public final void zza(ActivityLifecycleCallbacks var1) {
      var1.onActivitySaveInstanceState(this.val$activity, this.zzxV);
   }
}
