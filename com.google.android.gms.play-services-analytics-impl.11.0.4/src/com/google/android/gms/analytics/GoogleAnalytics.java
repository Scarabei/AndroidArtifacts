package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.internal.zzamj;
import com.google.android.gms.internal.zzans;
import com.google.android.gms.internal.zzaoa;
import com.google.android.gms.internal.zzaob;
import com.google.android.gms.internal.zzaop;
import com.google.android.gms.internal.zzaor;
import com.google.android.gms.internal.zzaot;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
   private static List zzadC = new ArrayList();
   private boolean zzuH;
   private Set zzadD = new HashSet();
   private boolean zzadE;
   private boolean zzadF;
   private volatile boolean zzadG;
   private boolean zzadH;

   public final void initialize() {
      zzaot var2;
      (var2 = this.zzji().zzkx()).zzmg();
      if (var2.zzmh()) {
         this.setDryRun(var2.zzmi());
      }

      var2.zzmg();
      this.zzuH = true;
   }

   public final boolean isInitialized() {
      return this.zzuH;
   }

   public GoogleAnalytics(zzamj var1) {
      super(var1);
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public static GoogleAnalytics getInstance(Context var0) {
      return zzamj.zzaf(var0).zzkG();
   }

   public static void zzjo() {
      Class var0 = GoogleAnalytics.class;
      synchronized(GoogleAnalytics.class) {
         if (zzadC != null) {
            Iterator var1 = zzadC.iterator();

            while(var1.hasNext()) {
               ((Runnable)var1.next()).run();
            }

            zzadC = null;
         }

      }
   }

   public final void setDryRun(boolean var1) {
      this.zzadF = var1;
   }

   public final boolean isDryRunEnabled() {
      return this.zzadF;
   }

   @TargetApi(14)
   public final void enableAutoActivityReports(Application var1) {
      if (!this.zzadE) {
         var1.registerActivityLifecycleCallbacks(new GoogleAnalytics.zzb());
         this.zzadE = true;
      }

   }

   public final void reportActivityStart(Activity var1) {
      if (!this.zzadE) {
         this.zzj(var1);
      }

   }

   final void zzj(Activity var1) {
      Iterator var2 = this.zzadD.iterator();

      while(var2.hasNext()) {
         ((GoogleAnalytics.zza)var2.next()).zzl(var1);
      }

   }

   public final void reportActivityStop(Activity var1) {
      if (!this.zzadE) {
         this.zzk(var1);
      }

   }

   final void zzk(Activity var1) {
      Iterator var2 = this.zzadD.iterator();

      while(var2.hasNext()) {
         ((GoogleAnalytics.zza)var2.next()).zzm(var1);
      }

   }

   public final Tracker newTracker(String var1) {
      synchronized(this) {
         Tracker var3;
         (var3 = new Tracker(this.zzji(), var1, (zzaoa)null)).initialize();
         return var3;
      }
   }

   public final Tracker newTracker(int var1) {
      synchronized(this) {
         Tracker var3 = new Tracker(this.zzji(), (String)null, (zzaoa)null);
         zzaor var4;
         if (var1 > 0 && (var4 = (zzaor)(new zzaop(this.zzji())).zzP(var1)) != null) {
            var3.zza(var4);
         }

         var3.initialize();
         return var3;
      }
   }

   final void zza(GoogleAnalytics.zza var1) {
      this.zzadD.add(var1);
      Context var2;
      if ((var2 = this.zzji().getContext()) instanceof Application) {
         this.enableAutoActivityReports((Application)var2);
      }

   }

   final void zzb(GoogleAnalytics.zza var1) {
      this.zzadD.remove(var1);
   }

   public final void setAppOptOut(boolean var1) {
      this.zzadG = var1;
      if (this.zzadG) {
         this.zzji().zzkv().zzkk();
      }

   }

   public final boolean getAppOptOut() {
      return this.zzadG;
   }

   /** @deprecated */
   @Deprecated
   public final Logger getLogger() {
      return zzaob.getLogger();
   }

   /** @deprecated */
   @Deprecated
   public final void setLogger(Logger var1) {
      zzaob.setLogger(var1);
      if (!this.zzadH) {
         String var2;
         Log.i(var2 = (String)zzans.zzahg.get(), (new StringBuilder(112 + String.valueOf(var2).length())).append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(var2).append(" DEBUG").toString());
         this.zzadH = true;
      }

   }

   public final void setLocalDispatchPeriod(int var1) {
      this.zzji().zzkv().setLocalDispatchPeriod(var1);
   }

   public final void dispatchLocalHits() {
      this.zzji().zzkv().zzkl();
   }

   @TargetApi(14)
   class zzb implements ActivityLifecycleCallbacks {
      // $FF: synthetic field
      private GoogleAnalytics zzadI;

      zzb() {
         this.zzadI = GoogleAnalytics.this;
         super();
      }

      public final void onActivityStarted(Activity var1) {
         this.zzadI.zzj(var1);
      }

      public final void onActivityStopped(Activity var1) {
         this.zzadI.zzk(var1);
      }

      public final void onActivityCreated(Activity var1, Bundle var2) {
      }

      public final void onActivityDestroyed(Activity var1) {
      }

      public final void onActivityPaused(Activity var1) {
      }

      public final void onActivityResumed(Activity var1) {
      }

      public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
      }
   }

   interface zza {
      void zzl(Activity var1);

      void zzm(Activity var1);
   }
}
