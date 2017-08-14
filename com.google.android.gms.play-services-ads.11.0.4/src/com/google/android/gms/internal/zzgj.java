package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class zzgj implements ActivityLifecycleCallbacks {
   private final Application zzxz;
   private final WeakReference zzxT;
   private boolean zzxU = false;

   public zzgj(Application var1, ActivityLifecycleCallbacks var2) {
      this.zzxT = new WeakReference(var2);
      this.zzxz = var1;
   }

   private final void zza(zzgr var1) {
      try {
         ActivityLifecycleCallbacks var2;
         if ((var2 = (ActivityLifecycleCallbacks)this.zzxT.get()) != null) {
            var1.zza(var2);
         } else {
            if (!this.zzxU) {
               this.zzxz.unregisterActivityLifecycleCallbacks(this);
               this.zzxU = true;
            }

         }
      } catch (Exception var3) {
         zzafr.zzb("Error while dispatching lifecycle callback.", var3);
      }
   }

   public final void onActivityCreated(Activity var1, Bundle var2) {
      this.zza(new zzgk(this, var1, var2));
   }

   public final void onActivityStarted(Activity var1) {
      this.zza(new zzgl(this, var1));
   }

   public final void onActivityResumed(Activity var1) {
      this.zza(new zzgm(this, var1));
   }

   public final void onActivityPaused(Activity var1) {
      this.zza(new zzgn(this, var1));
   }

   public final void onActivityStopped(Activity var1) {
      this.zza(new zzgo(this, var1));
   }

   public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
      this.zza(new zzgp(this, var1, var2));
   }

   public final void onActivityDestroyed(Activity var1) {
      this.zza(new zzgq(this, var1));
   }
}
