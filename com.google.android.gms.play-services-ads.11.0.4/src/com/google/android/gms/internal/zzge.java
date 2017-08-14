package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzzn
@TargetApi(14)
public final class zzge implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
   private static final long zzxy;
   private final Context mApplicationContext;
   private Application zzxz;
   private final WindowManager zzwR;
   private final PowerManager zzwS;
   private final KeyguardManager zzwT;
   @Nullable
   private BroadcastReceiver zzxb;
   private WeakReference zzxA;
   private WeakReference zzxB;
   private zzgj zzxC;
   private zzair zzwx;
   private boolean zzxa;
   private int zzxD;
   private HashSet zzxE;
   private DisplayMetrics zzxF;

   public zzge(Context var1, View var2) {
      this.zzwx = new zzair(zzxy);
      this.zzxa = false;
      this.zzxD = -1;
      this.zzxE = new HashSet();
      this.mApplicationContext = var1.getApplicationContext();
      this.zzwR = (WindowManager)var1.getSystemService("window");
      this.zzwS = (PowerManager)this.mApplicationContext.getSystemService("power");
      this.zzwT = (KeyguardManager)var1.getSystemService("keyguard");
      if (this.mApplicationContext instanceof Application) {
         this.zzxz = (Application)this.mApplicationContext;
         this.zzxC = new zzgj((Application)this.mApplicationContext, this);
      }

      this.zzxF = var1.getResources().getDisplayMetrics();
      View var10000 = this.zzxB != null ? (View)this.zzxB.get() : null;
      View var5 = var10000;
      if (var10000 != null) {
         var5.removeOnAttachStateChangeListener(this);
         this.zze(var5);
      }

      this.zzxB = new WeakReference(var2);
      if (var2 != null) {
         if (com.google.android.gms.ads.internal.zzbs.zzbB().isAttachedToWindow(var2)) {
            this.zzd(var2);
         }

         var2.addOnAttachStateChangeListener(this);
      }

   }

   public final void zza(zzgi var1) {
      this.zzxE.add(var1);
      this.zzh(3);
   }

   public final void zzb(zzgi var1) {
      this.zzxE.remove(var1);
   }

   private final void zzcA() {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      zzagz.zzZr.post(new zzgf(this));
   }

   public final void onViewAttachedToWindow(View var1) {
      this.zzxD = -1;
      this.zzd(var1);
      this.zzh(3);
   }

   public final void onViewDetachedFromWindow(View var1) {
      this.zzxD = -1;
      this.zzh(3);
      this.zzcA();
      this.zze(var1);
   }

   private final void zza(Activity var1, int var2) {
      if (this.zzxB != null) {
         Window var3;
         if ((var3 = var1.getWindow()) != null) {
            View var4 = var3.peekDecorView();
            View var5;
            if ((var5 = (View)this.zzxB.get()) != null && var4 != null && var5.getRootView() == var4.getRootView()) {
               this.zzxD = var2;
            }

         }
      }
   }

   public final void onActivityCreated(Activity var1, Bundle var2) {
      this.zza((Activity)var1, 0);
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivityStarted(Activity var1) {
      this.zza((Activity)var1, 0);
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivityResumed(Activity var1) {
      this.zza((Activity)var1, 0);
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivityPaused(Activity var1) {
      this.zza((Activity)var1, 4);
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivityStopped(Activity var1) {
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
      this.zzh(3);
      this.zzcA();
   }

   public final void onActivityDestroyed(Activity var1) {
      this.zzh(3);
      this.zzcA();
   }

   public final void onGlobalLayout() {
      this.zzh(2);
      this.zzcA();
   }

   public final void onScrollChanged() {
      this.zzh(1);
   }

   public final void zzcB() {
      this.zzh(4);
   }

   private final void zzh(int var1) {
      if (this.zzxE.size() != 0) {
         if (this.zzxB != null) {
            View var2 = (View)this.zzxB.get();
            boolean var3 = var1 == 1;
            boolean var4 = var2 == null;
            Rect var5 = new Rect();
            Rect var6 = new Rect();
            boolean var7 = false;
            Rect var8 = new Rect();
            boolean var9 = false;
            Rect var10 = new Rect();
            Rect var11;
            (var11 = new Rect()).right = this.zzwR.getDefaultDisplay().getWidth();
            var11.bottom = this.zzwR.getDefaultDisplay().getHeight();
            int[] var12 = new int[2];
            int[] var13 = new int[2];
            if (var2 != null) {
               var7 = var2.getGlobalVisibleRect(var6);
               var9 = var2.getLocalVisibleRect(var8);
               var2.getHitRect(var10);

               try {
                  var2.getLocationOnScreen(var12);
                  var2.getLocationInWindow(var13);
               } catch (Exception var19) {
                  zzafr.zzb("Failure getting view location.", var19);
               }

               var5.left = var12[0];
               var5.top = var12[1];
               var5.right = var5.left + var2.getWidth();
               var5.bottom = var5.top + var2.getHeight();
            }

            int var14 = var2 != null ? var2.getWindowVisibility() : 8;
            int var15 = this.zzxD != -1 ? this.zzxD : var14;
            boolean var16 = !var4 && com.google.android.gms.ads.internal.zzbs.zzbz().zza(var2, this.zzwS, this.zzwT) && var7 && var9 && var15 == 0;
            if (!var3 || this.zzwx.tryAcquire() || var16 != this.zzxa) {
               if (var16 || this.zzxa || var1 != 1) {
                  zzgh var17 = new zzgh(com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime(), this.zzwS.isScreenOn(), var2 != null ? com.google.android.gms.ads.internal.zzbs.zzbB().isAttachedToWindow(var2) : false, var2 != null ? var2.getWindowVisibility() : 8, this.zza(var11), this.zza(var5), this.zza(var6), var7, this.zza(var8), var9, this.zza(var10), this.zzxF.density, var16);
                  Iterator var18 = this.zzxE.iterator();

                  while(var18.hasNext()) {
                     ((zzgi)var18.next()).zza(var17);
                  }

                  this.zzxa = var16;
               }
            }
         }
      }
   }

   private final Rect zza(Rect var1) {
      return new Rect(this.zzi(var1.left), this.zzi(var1.top), this.zzi(var1.right), this.zzi(var1.bottom));
   }

   private final int zzi(int var1) {
      float var2 = this.zzxF.density;
      return (int)((float)var1 / var2);
   }

   private final void zzd(View var1) {
      ViewTreeObserver var2;
      if ((var2 = var1.getViewTreeObserver()).isAlive()) {
         this.zzxA = new WeakReference(var2);
         var2.addOnScrollChangedListener(this);
         var2.addOnGlobalLayoutListener(this);
      }

      if (this.zzxb == null) {
         IntentFilter var5;
         (var5 = new IntentFilter()).addAction("android.intent.action.SCREEN_ON");
         var5.addAction("android.intent.action.SCREEN_OFF");
         var5.addAction("android.intent.action.USER_PRESENT");
         this.zzxb = new zzgg(this);
         this.mApplicationContext.registerReceiver(this.zzxb, var5);
      }

      if (this.zzxz != null) {
         try {
            this.zzxz.registerActivityLifecycleCallbacks(this.zzxC);
            return;
         } catch (Exception var6) {
            zzafr.zzb("Error registering activity lifecycle callbacks.", var6);
         }
      }

   }

   private final void zze(View var1) {
      ViewTreeObserver var2;
      try {
         if (this.zzxA != null) {
            if ((var2 = (ViewTreeObserver)this.zzxA.get()) != null && var2.isAlive()) {
               var2.removeOnScrollChangedListener(this);
               var2.removeGlobalOnLayoutListener(this);
            }

            this.zzxA = null;
         }
      } catch (Exception var8) {
         zzafr.zzb("Error while unregistering listeners from the last ViewTreeObserver.", var8);
      }

      try {
         if ((var2 = var1.getViewTreeObserver()).isAlive()) {
            var2.removeOnScrollChangedListener(this);
            var2.removeGlobalOnLayoutListener(this);
         }
      } catch (Exception var7) {
         zzafr.zzb("Error while unregistering listeners from the ViewTreeObserver.", var7);
      }

      zzge var3 = this;
      if (this.zzxb != null) {
         try {
            var3.mApplicationContext.unregisterReceiver(var3.zzxb);
         } catch (IllegalStateException var5) {
            zzafr.zzb("Failed trying to unregister the receiver", var5);
         } catch (Exception var6) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var6, (String)"ActiveViewUnit.stopScreenStatusMonitoring");
         }

         this.zzxb = null;
      }

      if (this.zzxz != null) {
         try {
            this.zzxz.unregisterActivityLifecycleCallbacks(this.zzxC);
            return;
         } catch (Exception var9) {
            zzafr.zzb("Error registering activity lifecycle callbacks.", var9);
         }
      }

   }

   // $FF: synthetic method
   static void zza(zzge var0, int var1) {
      var0.zzh(3);
   }

   static {
      zzme var0 = zzmo.zzEr;
      zzxy = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).longValue();
   }
}
