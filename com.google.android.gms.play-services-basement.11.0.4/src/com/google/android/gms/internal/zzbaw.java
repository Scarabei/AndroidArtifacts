package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzbaw implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
   private static final zzbaw zzaBJ = new zzbaw();
   private final AtomicBoolean zzaBK = new AtomicBoolean();
   private final AtomicBoolean zzaBL = new AtomicBoolean();
   private final ArrayList mListeners = new ArrayList();
   private boolean zzafK = false;

   public static zzbaw zzpv() {
      return zzaBJ;
   }

   public static void zza(Application var0) {
      zzbaw var1 = zzaBJ;
      synchronized(zzaBJ) {
         if (!zzaBJ.zzafK) {
            var0.registerActivityLifecycleCallbacks(zzaBJ);
            var0.registerComponentCallbacks(zzaBJ);
            zzaBJ.zzafK = true;
         }

      }
   }

   @TargetApi(16)
   public final boolean zzab(boolean var1) {
      if (!this.zzaBL.get()) {
         if (!com.google.android.gms.common.util.zzq.zzrZ()) {
            return true;
         }

         RunningAppProcessInfo var2;
         ActivityManager.getMyMemoryState(var2 = new RunningAppProcessInfo());
         if (!this.zzaBL.getAndSet(true) && var2.importance > 100) {
            this.zzaBK.set(true);
         }
      }

      return this.zzaBK.get();
   }

   public final boolean zzpw() {
      return this.zzaBK.get();
   }

   public final void zza(zzbax var1) {
      zzbaw var2 = zzaBJ;
      synchronized(zzaBJ) {
         this.mListeners.add(var1);
      }
   }

   public final void onActivityCreated(Activity var1, Bundle var2) {
      boolean var3 = this.zzaBK.compareAndSet(true, false);
      this.zzaBL.set(true);
      if (var3) {
         this.zzac(false);
      }

   }

   public final void onActivityResumed(Activity var1) {
      boolean var2 = this.zzaBK.compareAndSet(true, false);
      this.zzaBL.set(true);
      if (var2) {
         this.zzac(false);
      }

   }

   public final void onTrimMemory(int var1) {
      if (var1 == 20 && this.zzaBK.compareAndSet(false, true)) {
         this.zzaBL.set(true);
         this.zzac(true);
      }

   }

   private final void zzac(boolean var1) {
      zzbaw var2 = zzaBJ;
      synchronized(zzaBJ) {
         ArrayList var4;
         int var5 = (var4 = (ArrayList)this.mListeners).size();
         int var6 = 0;

         while(var6 < var5) {
            Object var10000 = var4.get(var6);
            ++var6;
            ((zzbax)var10000).zzac(var1);
         }

      }
   }

   public final void onActivityStarted(Activity var1) {
   }

   public final void onActivityPaused(Activity var1) {
   }

   public final void onActivityStopped(Activity var1) {
   }

   public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
   }

   public final void onActivityDestroyed(Activity var1) {
   }

   public final void onConfigurationChanged(Configuration var1) {
   }

   public final void onLowMemory() {
   }
}
