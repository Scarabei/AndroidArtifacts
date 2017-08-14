package com.google.android.gms.nearby.messages.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

@TargetApi(14)
final class zzaj implements ActivityLifecycleCallbacks {
   private final Activity zzbzg;
   private final zzah zzbzh;

   private zzaj(Activity var1, zzah var2) {
      this.zzbzg = var1;
      this.zzbzh = var2;
   }

   public final void onActivityCreated(Activity var1, Bundle var2) {
   }

   public final void onActivityStarted(Activity var1) {
   }

   public final void onActivityResumed(Activity var1) {
   }

   public final void onActivityPaused(Activity var1) {
   }

   public final void onActivityStopped(Activity var1) {
      if (var1 == this.zzbzg) {
         try {
            this.zzbzh.zzbs(1);
            return;
         } catch (RemoteException var3) {
            Log.v("NearbyMessagesClient", String.format("Failed to emit ACTIVITY_STOPPED from ClientLifecycleSafetyNet for Activity %s: %s", var1.getPackageName(), var3));
         }
      }

   }

   public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
   }

   public final void onActivityDestroyed(Activity var1) {
      if (var1 == this.zzbzg) {
         Log.v("NearbyMessagesClient", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", var1.getPackageName()));
         var1.getApplication().unregisterActivityLifecycleCallbacks(this);
      }

   }

   // $FF: synthetic method
   zzaj(Activity var1, zzah var2, zzai var3) {
      this(var1, var2);
   }
}
