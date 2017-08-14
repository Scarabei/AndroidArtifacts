package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(14)
final class zzgw implements ActivityLifecycleCallbacks {
   @Nullable
   private Activity mActivity;
   private Context mContext;
   private final Object mLock = new Object();
   private boolean zzyq = true;
   private boolean zzyr = false;
   private final List zzys = new ArrayList();
   private final List zzyt = new ArrayList();
   private Runnable zzyu;
   private boolean zzuH = false;
   private long zzyv;

   public final void zza(Application var1, Context var2) {
      if (!this.zzuH) {
         var1.registerActivityLifecycleCallbacks(this);
         if (var2 instanceof Activity) {
            this.setActivity((Activity)var2);
         }

         this.mContext = var2;
         zzme var3 = zzmo.zzDK;
         this.zzyv = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).longValue();
         this.zzuH = true;
      }

   }

   public final void zza(zzgy var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzys.add(var1);
      }
   }

   public final void onActivityCreated(Activity var1, Bundle var2) {
   }

   @Nullable
   public final Activity getActivity() {
      return this.mActivity;
   }

   @Nullable
   public final Context getContext() {
      return this.mContext;
   }

   public final void onActivityStarted(Activity var1) {
      this.setActivity(var1);
   }

   public final void onActivityResumed(Activity var1) {
      this.setActivity(var1);
      this.zzyr = false;
      boolean var2 = !this.zzyq;
      this.zzyq = true;
      if (this.zzyu != null) {
         zzagz.zzZr.removeCallbacks(this.zzyu);
      }

      Object var3 = this.mLock;
      synchronized(this.mLock) {
         Iterator var4 = this.zzyt.iterator();

         while(var4.hasNext()) {
            var4.next();
         }

         if (var2) {
            var4 = this.zzys.iterator();

            while(var4.hasNext()) {
               zzgy var5 = (zzgy)var4.next();

               try {
                  var5.zzf(true);
               } catch (Exception var8) {
                  zzafr.zzb("OnForegroundStateChangedListener threw exception.", var8);
               }
            }
         } else {
            zzafr.zzaC("App is still foreground.");
         }

      }
   }

   public final void onActivityPaused(Activity var1) {
      this.setActivity(var1);
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         Iterator var3 = this.zzyt.iterator();

         while(true) {
            if (!var3.hasNext()) {
               break;
            }

            var3.next();
         }
      }

      this.zzyr = true;
      if (this.zzyu != null) {
         zzagz.zzZr.removeCallbacks(this.zzyu);
      }

      zzagz.zzZr.postDelayed(this.zzyu = new zzgx(this), this.zzyv);
   }

   public final void onActivityStopped(Activity var1) {
   }

   public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
   }

   public final void onActivityDestroyed(Activity var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.mActivity != null) {
            if (this.mActivity.equals(var1)) {
               this.mActivity = null;
            }

            Iterator var3 = this.zzyt.iterator();

            while(var3.hasNext()) {
               zzhl var4 = (zzhl)var3.next();

               try {
                  if (var4.zza(var1)) {
                     var3.remove();
                  }
               } catch (Exception var7) {
                  com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var7, (String)"AppActivityTracker.ActivityListener.onActivityDestroyed");
                  zzafr.zzb("onActivityStateChangedListener threw exception.", var7);
               }
            }

         }
      }
   }

   private final void setActivity(Activity var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!var1.getClass().getName().startsWith("com.google.android.gms.ads")) {
            this.mActivity = var1;
         }

      }
   }

   // $FF: synthetic method
   static Object zza(zzgw var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static boolean zzb(zzgw var0) {
      return var0.zzyq;
   }

   // $FF: synthetic method
   static boolean zzc(zzgw var0) {
      return var0.zzyr;
   }

   // $FF: synthetic method
   static boolean zza(zzgw var0, boolean var1) {
      return var0.zzyq = false;
   }

   // $FF: synthetic method
   static List zzd(zzgw var0) {
      return var0.zzys;
   }
}
