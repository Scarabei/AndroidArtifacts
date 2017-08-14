package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzfi implements OnGlobalLayoutListener, OnScrollChangedListener {
   private Object mLock = new Object();
   private final WeakReference zzwN;
   private WeakReference zzwO;
   private final zzgs zzwP;
   protected final zzfg zzwQ;
   private final Context mApplicationContext;
   private final WindowManager zzwR;
   private final PowerManager zzwS;
   private final KeyguardManager zzwT;
   private final DisplayMetrics zzwU;
   @Nullable
   private zzfp zzwV;
   private boolean zzwW;
   private boolean zzuV = false;
   private boolean zzwX = false;
   private boolean zzwY;
   private boolean zzwZ;
   private boolean zzxa;
   @Nullable
   private BroadcastReceiver zzxb;
   private final HashSet zzxc = new HashSet();
   private zzair zzwx;
   private final HashSet zzxd = new HashSet();
   private final Rect zzxe = new Rect();
   private final zzfl zzxf;
   private float zzxg;

   public zzfi(Context var1, zziv var2, zzaff var3, zzaje var4, zzgs var5) {
      this.zzwN = new WeakReference(var3);
      this.zzwP = var5;
      this.zzwO = new WeakReference((Object)null);
      this.zzwY = true;
      this.zzxa = false;
      this.zzwx = new zzair(200L);
      this.zzwQ = new zzfg(UUID.randomUUID().toString(), var4, var2.zzAs, var3.zzXL, var3.zzcn(), var2.zzAv);
      this.zzwR = (WindowManager)var1.getSystemService("window");
      this.zzwS = (PowerManager)var1.getApplicationContext().getSystemService("power");
      this.zzwT = (KeyguardManager)var1.getSystemService("keyguard");
      this.mApplicationContext = var1;
      this.zzxf = new zzfl(this, new Handler());
      this.mApplicationContext.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzxf);
      this.zzwU = var1.getResources().getDisplayMetrics();
      Display var6 = this.zzwR.getDefaultDisplay();
      this.zzxe.right = var6.getWidth();
      this.zzxe.bottom = var6.getHeight();
      this.zzcp();
   }

   public final void zzcp() {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      this.zzxg = zzagz.zzM(this.mApplicationContext);
   }

   public final void zzcq() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzwY) {
            this.zzwZ = true;

            try {
               JSONObject var4;
               (var4 = this.zzcu()).put("doneReasonCode", "u");
               this.zza(var4, true);
            } catch (JSONException var5) {
               zzafr.zzb("JSON failure while processing active view data.", var5);
            } catch (RuntimeException var6) {
               zzafr.zzb("Failure while processing active view data.", var6);
            }

            String var10001 = String.valueOf(this.zzwQ.zzcm());
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Untracking ad unit: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Untracking ad unit: ");
            }

            zzafr.zzaC(var10000);
         }

      }
   }

   protected final void zzg(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         Iterator var9 = this.zzxd.iterator();

         boolean var10000;
         while(true) {
            if (var9.hasNext()) {
               if (!((zzgd)var9.next()).zzcy()) {
                  continue;
               }

               var10000 = true;
               break;
            }

            var10000 = false;
            break;
         }

         if (var10000 && this.zzwY) {
            View var3;
            boolean var4 = (var3 = this.zzwP.zzcv()) != null && com.google.android.gms.ads.internal.zzbs.zzbz().zza(var3, this.zzwS, this.zzwT);
            boolean var5 = var3 != null && var4 && var3.getGlobalVisibleRect(new Rect(), (Point)null);
            if (this.zzwP.zzcw()) {
               this.zzcq();
            } else if (var1 != 1 || this.zzwx.tryAcquire() || var5 != this.zzxa) {
               if (var5 || this.zzxa || var1 != 1) {
                  try {
                     JSONObject var6 = this.zza(var3, var4);
                     this.zza(var6, false);
                     this.zzxa = var5;
                  } catch (RuntimeException | JSONException var13) {
                     zzafr.zza("Active view update failed.", var13);
                  }

                  View var10;
                  if ((var10 = this.zzwP.zzcx().zzcv()) != null) {
                     ViewTreeObserver var11 = (ViewTreeObserver)this.zzwO.get();
                     ViewTreeObserver var12;
                     if ((var12 = var10.getViewTreeObserver()) != var11) {
                        this.zzct();
                        if (!this.zzwW || var11 != null && var11.isAlive()) {
                           this.zzwW = true;
                           var12.addOnScrollChangedListener(this);
                           var12.addOnGlobalLayoutListener(this);
                        }

                        this.zzwO = new WeakReference(var12);
                     }
                  }

                  this.zzcr();
               }
            }
         }
      }
   }

   private final void zzcr() {
      if (this.zzwV != null) {
         this.zzwV.zza(this);
      }

   }

   public final boolean zzcs() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzwY;
      }
   }

   private static int zza(int var0, DisplayMetrics var1) {
      float var2 = var1.density;
      return (int)((float)var0 / var2);
   }

   final boolean zzb(@Nullable Map var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2;
         return !TextUtils.isEmpty(var2 = (String)var1.get("hashCode")) && var2.equals(this.zzwQ.zzcm());
      }
   }

   final void zzb(zzaka var1, Map var2) {
      this.zzg(3);
   }

   final void zza(zzgd var1, Map var2) {
      String var10001 = String.valueOf(this.zzwQ.zzcm());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Received request to untrack: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Received request to untrack: ");
      }

      zzafr.zzaC(var10000);
      this.zzb(var1);
   }

   final void zzc(Map var1) {
      if (var1.containsKey("isVisible")) {
         if (!"1".equals(var1.get("isVisible"))) {
            "true".equals(var1.get("isVisible"));
         }

         Iterator var2 = this.zzxc.iterator();

         while(var2.hasNext()) {
            var2.next();
         }

      }
   }

   private final void zzct() {
      ViewTreeObserver var1;
      if ((var1 = (ViewTreeObserver)this.zzwO.get()) != null && var1.isAlive()) {
         var1.removeOnScrollChangedListener(this);
         var1.removeGlobalOnLayoutListener(this);
      }
   }

   private final JSONObject zzcu() throws JSONException {
      JSONObject var1;
      JSONObject var10000 = (var1 = new JSONObject()).put("afmaVersion", this.zzwQ.zzck()).put("activeViewJSON", this.zzwQ.zzcl()).put("timestamp", com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime()).put("adFormat", this.zzwQ.zzcj()).put("hashCode", this.zzwQ.zzcm()).put("isMraid", this.zzwQ.zzcn()).put("isStopped", this.zzwX).put("isPaused", this.zzuV).put("isNative", this.zzwQ.zzco()).put("isScreenOn", this.isScreenOn());
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var10000 = var10000.put("appMuted", zzagz.zzbh());
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var10000.put("appVolume", (double)zzagz.zzbf()).put("deviceVolume", (double)this.zzxg);
      return var1;
   }

   private static JSONObject zza(JSONObject var0) throws JSONException {
      JSONArray var1 = new JSONArray();
      JSONObject var2 = new JSONObject();
      var1.put(var0);
      var2.put("units", var1);
      return var2;
   }

   private final void zza(JSONObject var1, boolean var2) {
      try {
         JSONObject var3 = zza(var1);
         boolean var6 = var2;
         JSONObject var5 = var3;
         ArrayList var7;
         int var8 = (var7 = (ArrayList)(new ArrayList(this.zzxd))).size();
         int var9 = 0;

         while(var9 < var8) {
            Object var10000 = var7.get(var9);
            ++var9;
            ((zzgd)var10000).zzb(var5, var6);
         }

      } catch (Throwable var10) {
         zzafr.zzb("Skipping active view message.", var10);
      }
   }

   public final void zza(zzgd var1) {
      if (this.zzxd.isEmpty()) {
         zzfi var4 = this;
         Object var5 = this.mLock;
         synchronized(this.mLock) {
            if (var4.zzxb == null) {
               IntentFilter var6;
               (var6 = new IntentFilter()).addAction("android.intent.action.SCREEN_ON");
               var6.addAction("android.intent.action.SCREEN_OFF");
               var4.zzxb = new zzfj(var4);
               var4.mApplicationContext.registerReceiver(var4.zzxb, var6);
            }
         }

         this.zzg(3);
      }

      this.zzxd.add(var1);

      try {
         JSONObject var3 = zza(this.zza((View)this.zzwP.zzcv(), (Boolean)null));
         var1.zzb(var3, false);
      } catch (JSONException var8) {
         zzafr.zzb("Skipping measurement update for new client.", var8);
      }
   }

   public final void zzb(zzgd var1) {
      this.zzxd.remove(var1);
      var1.zzcz();
      if (this.zzxd.isEmpty()) {
         zzfi var2 = this;
         Object var3 = this.mLock;
         synchronized(this.mLock) {
            var2.zzct();
            zzfi var5 = var2;
            Object var6 = var2.mLock;
            synchronized(var2.mLock) {
               if (var5.zzxb != null) {
                  try {
                     var5.mApplicationContext.unregisterReceiver(var5.zzxb);
                  } catch (IllegalStateException var12) {
                     zzafr.zzb("Failed trying to unregister the receiver", var12);
                  } catch (Exception var13) {
                     com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var13, (String)"ActiveViewUnit.stopScreenStatusMonitoring");
                  }

                  var5.zzxb = null;
               }
            }

            var2.mApplicationContext.getContentResolver().unregisterContentObserver(var2.zzxf);
            var2.zzwY = false;
            var2.zzcr();
            var5 = var2;
            ArrayList var9;
            int var10 = (var9 = (ArrayList)(new ArrayList(var2.zzxd))).size();
            int var11 = 0;

            while(var11 < var10) {
               Object var10000 = var9.get(var11);
               ++var11;
               zzgd var8 = (zzgd)var10000;
               var5.zzb(var8);
            }

         }
      }
   }

   private final JSONObject zza(@Nullable View var1, @Nullable Boolean var2) throws JSONException {
      if (var1 == null) {
         return this.zzcu().put("isAttachedToWindow", false).put("isScreenOn", this.isScreenOn()).put("isVisible", false);
      } else {
         boolean var3 = com.google.android.gms.ads.internal.zzbs.zzbB().isAttachedToWindow(var1);
         int[] var4 = new int[2];
         int[] var5 = new int[2];

         try {
            var1.getLocationOnScreen(var4);
            var1.getLocationInWindow(var5);
         } catch (Exception var14) {
            zzafr.zzb("Failure getting view location.", var14);
         }

         Rect var6;
         (var6 = new Rect()).left = var4[0];
         var6.top = var4[1];
         var6.right = var6.left + var1.getWidth();
         var6.bottom = var6.top + var1.getHeight();
         Rect var7 = new Rect();
         boolean var8 = var1.getGlobalVisibleRect(var7, (Point)null);
         Rect var9 = new Rect();
         boolean var10 = var1.getLocalVisibleRect(var9);
         Rect var11 = new Rect();
         var1.getHitRect(var11);
         JSONObject var12;
         (var12 = this.zzcu()).put("windowVisibility", var1.getWindowVisibility()).put("isAttachedToWindow", var3).put("viewBox", (new JSONObject()).put("top", zza(this.zzxe.top, this.zzwU)).put("bottom", zza(this.zzxe.bottom, this.zzwU)).put("left", zza(this.zzxe.left, this.zzwU)).put("right", zza(this.zzxe.right, this.zzwU))).put("adBox", (new JSONObject()).put("top", zza(var6.top, this.zzwU)).put("bottom", zza(var6.bottom, this.zzwU)).put("left", zza(var6.left, this.zzwU)).put("right", zza(var6.right, this.zzwU))).put("globalVisibleBox", (new JSONObject()).put("top", zza(var7.top, this.zzwU)).put("bottom", zza(var7.bottom, this.zzwU)).put("left", zza(var7.left, this.zzwU)).put("right", zza(var7.right, this.zzwU))).put("globalVisibleBoxVisible", var8).put("localVisibleBox", (new JSONObject()).put("top", zza(var9.top, this.zzwU)).put("bottom", zza(var9.bottom, this.zzwU)).put("left", zza(var9.left, this.zzwU)).put("right", zza(var9.right, this.zzwU))).put("localVisibleBoxVisible", var10).put("hitBox", (new JSONObject()).put("top", zza(var11.top, this.zzwU)).put("bottom", zza(var11.bottom, this.zzwU)).put("left", zza(var11.left, this.zzwU)).put("right", zza(var11.right, this.zzwU))).put("screenDensity", (double)this.zzwU.density);
         if (var2 == null) {
            var2 = com.google.android.gms.ads.internal.zzbs.zzbz().zza(var1, this.zzwS, this.zzwT);
         }

         var12.put("isVisible", var2.booleanValue());
         return var12;
      }
   }

   private final boolean isScreenOn() {
      return VERSION.SDK_INT >= 20 ? this.zzwS.isInteractive() : this.zzwS.isScreenOn();
   }

   public final void onScrollChanged() {
      this.zzg(1);
   }

   public final void onGlobalLayout() {
      this.zzg(2);
   }

   public final void zza(zzfp var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzwV = var1;
      }
   }

   public final void stop() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzwX = true;
         this.zzg(3);
      }
   }

   public final void pause() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzuV = true;
         this.zzg(3);
      }
   }

   public final void resume() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzuV = false;
         this.zzg(3);
      }
   }
}
