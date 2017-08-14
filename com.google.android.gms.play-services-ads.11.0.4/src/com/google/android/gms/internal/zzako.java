package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.common.util.zzq;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
final class zzako extends WebView implements OnGlobalLayoutListener, DownloadListener, zzaka {
   private final zzakr zzabS;
   private final Object mLock = new Object();
   @Nullable
   private final zzcu zzIc;
   private final zzaje zztW;
   private final zzbl zzabT;
   private final zzv zzsS;
   private zzakb zzabU;
   private zzm zzabV;
   private zziv zzuZ;
   private boolean zzabW;
   private boolean zzabX;
   private boolean zzabY;
   private boolean zzabZ;
   private Boolean zzYA;
   private int zzaca;
   private boolean zzacb = true;
   private boolean zzacc = false;
   private String zzQx = "";
   private zzaks zzacd;
   private boolean zzace;
   private boolean zzacf;
   private zznw zzacg;
   private int zzach;
   private int zzaci;
   private zzmz zzacj;
   private zzmz zzQy;
   private zzmz zzack;
   private zzna zzacl;
   private WeakReference zzacm;
   private zzm zzacn;
   private boolean zzaco;
   private zzaix zzwC;
   private int zzNZ = -1;
   private int zzNY = -1;
   private int zzOb = -1;
   private int zzOc = -1;
   private Map zzacp;
   private final WindowManager zzwR;
   private final zzig zzacq;

   static zzako zzb(Context var0, zziv var1, boolean var2, boolean var3, @Nullable zzcu var4, zzaje var5, zznb var6, zzbl var7, zzv var8, zzig var9) {
      zzakr var10 = new zzakr(var0);
      return new zzako(var10, var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   private zzako(zzakr var1, zziv var2, boolean var3, boolean var4, @Nullable zzcu var5, zzaje var6, zznb var7, zzbl var8, zzv var9, zzig var10) {
      super(var1);
      this.zzabS = var1;
      this.zzuZ = var2;
      this.zzabY = var3;
      this.zzaca = -1;
      this.zzIc = var5;
      this.zztW = var6;
      this.zzabT = var8;
      this.zzsS = var9;
      this.zzwR = (WindowManager)this.getContext().getSystemService("window");
      this.zzacq = var10;
      this.setBackgroundColor(0);
      WebSettings var11;
      (var11 = this.getSettings()).setAllowFileAccess(false);

      try {
         var11.setJavaScriptEnabled(true);
      } catch (NullPointerException var17) {
         zzafr.zzb("Unable to enable Javascript.", var17);
      }

      var11.setSavePassword(false);
      var11.setSupportMultipleWindows(true);
      var11.setJavaScriptCanOpenWindowsAutomatically(true);
      if (VERSION.SDK_INT >= 21) {
         var11.setMixedContentMode(2);
      }

      zzagz var10000 = com.google.android.gms.ads.internal.zzbs.zzbz();
      String var15 = var6.zzaP;
      zzagz var13 = var10000;
      var11.setUserAgentString(var13.zzs(var1, var15));
      com.google.android.gms.ads.internal.zzbs.zzbB().zza(this.getContext(), var11);
      this.setDownloadListener(this);
      this.zzjd();
      if (zzq.zzsa()) {
         this.addJavascriptInterface(new zzakv(this), "googleAdsJsInterface");
      }

      this.removeJavascriptInterface("accessibility");
      this.removeJavascriptInterface("accessibilityTraversal");
      this.zzwC = new zzaix(this.zzabS.zzis(), this, this, (OnScrollChangedListener)null);
      this.zzd(var7);
      com.google.android.gms.ads.internal.zzbs.zzbB().zzR(var1);
   }

   public final void setWebViewClient(WebViewClient var1) {
      super.setWebViewClient(var1);
      if (var1 instanceof zzakb) {
         this.zzabU = (zzakb)var1;
      }

   }

   public final WebView getWebView() {
      return this;
   }

   public final View getView() {
      return this;
   }

   public final zzv zzak() {
      return this.zzsS;
   }

   private final boolean zzjb() {
      if (!this.zzabU.zzcn() && !this.zzabU.zziP()) {
         return false;
      } else {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         DisplayMetrics var1 = zzagz.zza(this.zzwR);
         zzji.zzds();
         int var2 = zzaiy.zzb(var1, var1.widthPixels);
         zzji.zzds();
         int var3 = zzaiy.zzb(var1, var1.heightPixels);
         Activity var4;
         int var5;
         int var6;
         if ((var4 = this.zzabS.zzis()) != null && var4.getWindow() != null) {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            int[] var7 = zzagz.zzf(var4);
            zzji.zzds();
            var5 = zzaiy.zzb(var1, var7[0]);
            zzji.zzds();
            var6 = zzaiy.zzb(var1, var7[1]);
         } else {
            var5 = var2;
            var6 = var3;
         }

         if (this.zzNY == var2 && this.zzNZ == var3 && this.zzOb == var5 && this.zzOc == var6) {
            return false;
         } else {
            boolean var8 = this.zzNY != var2 || this.zzNZ != var3;
            this.zzNY = var2;
            this.zzNZ = var3;
            this.zzOb = var5;
            this.zzOc = var6;
            (new zzwu(this)).zza(var2, var3, var5, var6, var1.density, this.zzwR.getDefaultDisplay().getRotation());
            return var8;
         }
      }
   }

   public final void zza(String var1, Map var2) {
      JSONObject var3;
      try {
         var3 = com.google.android.gms.ads.internal.zzbs.zzbz().zzj(var2);
      } catch (JSONException var4) {
         zzafr.zzaT("Could not convert parameters to JSON.");
         return;
      }

      this.zzb(var1, var3);
   }

   @TargetApi(19)
   public final void evaluateJavascript(String var1, ValueCallback var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.isDestroyed()) {
            zzafr.zzaT("The webview is destroyed. Ignoring action.");
            if (var2 != null) {
               var2.onReceiveValue((Object)null);
            }

         } else {
            super.evaluateJavascript(var1, var2);
         }
      }
   }

   private final void zzaW(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.isDestroyed()) {
            this.loadUrl(var1);
         } else {
            zzafr.zzaT("The webview is destroyed. Ignoring action.");
         }

      }
   }

   public final void loadUrl(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (!this.isDestroyed()) {
            try {
               super.loadUrl(var1);
            } catch (Throwable var5) {
               com.google.android.gms.ads.internal.zzbs.zzbD().zza(var5, "AdWebViewImpl.loadUrl");
               zzafr.zzc("Could not call loadUrl. ", var5);
            }
         } else {
            zzafr.zzaT("The webview is destroyed. Ignoring action.");
         }

      }
   }

   public final void zzaU(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         try {
            super.loadUrl(var1);
         } catch (Throwable var5) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zza(var5, "AdWebViewImpl.loadUrlUnsafe");
            zzafr.zzc("Could not call loadUrl. ", var5);
         }

      }
   }

   public final void loadData(String var1, String var2, String var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (!this.isDestroyed()) {
            super.loadData(var1, var2, var3);
         } else {
            zzafr.zzaT("The webview is destroyed. Ignoring action.");
         }

      }
   }

   public final void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
      Object var6 = this.mLock;
      synchronized(this.mLock) {
         if (!this.isDestroyed()) {
            super.loadDataWithBaseURL(var1, var2, var3, var4, var5);
         } else {
            zzafr.zzaT("The webview is destroyed. Ignoring action.");
         }

      }
   }

   private final void zzaX(String var1) {
      String var10001;
      String var10002;
      String var10003;
      if (zzq.zzsc()) {
         zzako var2;
         if (this.zzhw() == null) {
            var2 = this;
            Object var3 = this.mLock;
            synchronized(this.mLock) {
               var2.zzYA = com.google.android.gms.ads.internal.zzbs.zzbD().zzhw();
               if (var2.zzYA == null) {
                  try {
                     String var4 = "(function(){})()";
                     var2.evaluateJavascript(var4, (ValueCallback)null);
                     var2.zza(true);
                  } catch (IllegalStateException var8) {
                     var2.zza(false);
                  }
               }
            }
         }

         if (this.zzhw().booleanValue()) {
            String var10 = var1;
            var2 = this;
            synchronized(this.mLock) {
               if (!var2.isDestroyed()) {
                  var2.evaluateJavascript(var10, (ValueCallback)null);
               } else {
                  zzafr.zzaT("The webview is destroyed. Ignoring action.");
               }

            }
         } else {
            var10002 = String.valueOf(var1);
            if (var10002.length() != 0) {
               var10001 = "javascript:".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("javascript:");
            }

            this.zzaW(var10001);
         }
      } else {
         var10002 = String.valueOf(var1);
         if (var10002.length() != 0) {
            var10001 = "javascript:".concat(var10002);
         } else {
            var10003 = new String;
            var10001 = var10003;
            var10003.<init>("javascript:");
         }

         this.zzaW(var10001);
      }
   }

   private final void zza(Boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzYA = var1;
      }

      com.google.android.gms.ads.internal.zzbs.zzbD().zza(var1);
   }

   private final Boolean zzhw() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzYA;
      }
   }

   public final void zza(String var1, JSONObject var2) {
      if (var2 == null) {
         var2 = new JSONObject();
      }

      String var3 = var2.toString();
      this.zzi(var1, var3);
   }

   public final void zzi(String var1, String var2) {
      String var3 = (new StringBuilder(3 + String.valueOf(var1).length() + String.valueOf(var2).length())).append(var1).append("(").append(var2).append(");").toString();
      this.zzaX(var3);
   }

   public final void zzb(String var1, JSONObject var2) {
      if (var2 == null) {
         var2 = new JSONObject();
      }

      String var3 = var2.toString();
      StringBuilder var4;
      (var4 = new StringBuilder()).append("(window.AFMA_ReceiveMessage || function() {})('");
      var4.append(var1);
      var4.append("'");
      var4.append(",");
      var4.append(var3);
      var4.append(");");
      String var10001 = String.valueOf(var4.toString());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Dispatching AFMA event: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Dispatching AFMA event: ");
      }

      zzafr.zzaC(var10000);
      this.zzaX(var4.toString());
   }

   public final void zziq() {
      this.zzjc();
      HashMap var1;
      (var1 = new HashMap(1)).put("version", this.zztW.zzaP);
      this.zza("onhide", (Map)var1);
   }

   public final void zzA(int var1) {
      if (var1 == 0) {
         zzmu.zza(this.zzacl.zzdR(), this.zzQy, "aebb2");
      }

      this.zzjc();
      if (this.zzacl.zzdR() != null) {
         this.zzacl.zzdR().zzh("close_type", String.valueOf(var1));
      }

      HashMap var2;
      (var2 = new HashMap(2)).put("closetype", String.valueOf(var1));
      var2.put("version", this.zztW.zzaP);
      this.zza("onhide", (Map)var2);
   }

   private final void zzjc() {
      zzmu.zza(this.zzacl.zzdR(), this.zzQy, "aeh2");
   }

   public final void zzfP() {
      if (this.zzacj == null) {
         zzmu.zza(this.zzacl.zzdR(), this.zzQy, "aes2");
         this.zzacj = zzmu.zzb(this.zzacl.zzdR());
         this.zzacl.zza("native:view_show", this.zzacj);
      }

      HashMap var1;
      (var1 = new HashMap(1)).put("version", this.zztW.zzaP);
      this.zza("onshow", (Map)var1);
   }

   public final void zzir() {
      HashMap var1;
      HashMap var10000 = var1 = new HashMap(3);
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var10000.put("app_muted", String.valueOf(zzagz.zzbh()));
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var1.put("app_volume", String.valueOf(zzagz.zzbf()));
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var1.put("device_volume", String.valueOf(zzagz.zzM(this.getContext())));
      this.zza("volume", (Map)var1);
   }

   public final zzm zziu() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabV;
      }
   }

   public final zzm zziv() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacn;
      }
   }

   public final zziv zzam() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzuZ;
      }
   }

   public final zzakb zziw() {
      return this.zzabU;
   }

   public final boolean zzix() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabW;
      }
   }

   public final zzcu zziy() {
      return this.zzIc;
   }

   public final zzaje zziz() {
      return this.zztW;
   }

   public final boolean zziA() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabY;
      }
   }

   public final void onDownloadStart(String var1, String var2, String var3, String var4, long var5) {
      try {
         Intent var7;
         (var7 = new Intent("android.intent.action.VIEW")).setDataAndType(Uri.parse(var1), var4);
         com.google.android.gms.ads.internal.zzbs.zzbz();
         zzagz.zzb(this.getContext(), var7);
      } catch (ActivityNotFoundException var8) {
         zzafr.zzaC((new StringBuilder(51 + String.valueOf(var1).length() + String.valueOf(var4).length())).append("Couldn't find an Activity to view url/mimetype: ").append(var1).append(" / ").append(var4).toString());
      }
   }

   public final boolean onTouchEvent(MotionEvent var1) {
      if (this.zzabU.zziP()) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzacg != null) {
               this.zzacg.zzc(var1);
            }
         }
      } else if (this.zzIc != null) {
         this.zzIc.zza(var1);
      }

      return this.isDestroyed() ? false : super.onTouchEvent(var1);
   }

   public final boolean onGenericMotionEvent(MotionEvent var1) {
      zzme var4 = zzmo.zzDF;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
         float var2 = var1.getAxisValue(9);
         float var3 = var1.getAxisValue(10);
         if (var1.getActionMasked() == 8 && (var2 > 0.0F && !this.canScrollVertically(-1) || var2 < 0.0F && !this.canScrollVertically(1) || var3 > 0.0F && !this.canScrollHorizontally(-1) || var3 < 0.0F && !this.canScrollHorizontally(1))) {
            return false;
         }
      }

      return super.onGenericMotionEvent(var1);
   }

   @SuppressLint({"DrawAllocation"})
   protected final void onMeasure(int var1, int var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.isDestroyed()) {
            this.setMeasuredDimension(0, 0);
         } else if (!this.isInEditMode() && !this.zzabY && !this.zzuZ.zzAv) {
            int var6;
            int var7;
            if (this.zzuZ.zzAw) {
               zzme var16 = zzmo.zzFD;
               if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var16)).booleanValue() && zzq.zzsa()) {
                  this.zza("/contentHeight", (zzrd)(new zzakp(this)));
                  String var19 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();";
                  this.zzaX(var19);
                  float var20 = this.zzabS.getResources().getDisplayMetrics().density;
                  var6 = MeasureSpec.getSize(var1);
                  switch(this.zzaci) {
                  case -1:
                     var7 = MeasureSpec.getSize(var2);
                     break;
                  default:
                     var7 = (int)((float)this.zzaci * var20);
                  }

                  this.setMeasuredDimension(var6, var7);
               } else {
                  super.onMeasure(var1, var2);
               }
            } else if (this.zzuZ.zzAt) {
               DisplayMetrics var18 = new DisplayMetrics();
               this.zzwR.getDefaultDisplay().getMetrics(var18);
               this.setMeasuredDimension(var18.widthPixels, var18.heightPixels);
            } else {
               int var4 = MeasureSpec.getMode(var1);
               int var5 = MeasureSpec.getSize(var1);
               var6 = MeasureSpec.getMode(var2);
               var7 = MeasureSpec.getSize(var2);
               int var8 = Integer.MAX_VALUE;
               if (var4 == Integer.MIN_VALUE || var4 == 1073741824) {
                  var8 = var5;
               }

               int var9 = Integer.MAX_VALUE;
               if (var6 == Integer.MIN_VALUE || var6 == 1073741824) {
                  var9 = var7;
               }

               if (this.zzuZ.widthPixels <= var8 && this.zzuZ.heightPixels <= var9) {
                  if (this.getVisibility() != 8) {
                     this.setVisibility(0);
                  }

                  this.setMeasuredDimension(this.zzuZ.widthPixels, this.zzuZ.heightPixels);
               } else {
                  float var10 = this.zzabS.getResources().getDisplayMetrics().density;
                  int var11 = (int)((float)this.zzuZ.widthPixels / var10);
                  int var12 = (int)((float)this.zzuZ.heightPixels / var10);
                  int var13 = (int)((float)var5 / var10);
                  int var14 = (int)((float)var7 / var10);
                  zzafr.zzaT((new StringBuilder(103)).append("Not enough space to show ad. Needs ").append(var11).append("x").append(var12).append(" dp, but only has ").append(var13).append("x").append(var14).append(" dp.").toString());
                  if (this.getVisibility() != 8) {
                     this.setVisibility(4);
                  }

                  this.setMeasuredDimension(0, 0);
               }

            }
         } else {
            super.onMeasure(var1, var2);
         }
      }
   }

   public final void onGlobalLayout() {
      boolean var1 = this.zzjb();
      zzm var2;
      if ((var2 = this.zziu()) != null && var1) {
         var2.zzfO();
      }

   }

   public final void zza(Context var1, zziv var2, zznb var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         this.zzwC.zzih();
         this.setContext(var1);
         this.zzabV = null;
         this.zzuZ = var2;
         this.zzabY = false;
         this.zzabW = false;
         this.zzQx = "";
         this.zzaca = -1;
         com.google.android.gms.ads.internal.zzbs.zzbB();
         zzahe.zzl(this);
         this.loadUrl("about:blank");
         this.zzabU.reset();
         this.setOnTouchListener((OnTouchListener)null);
         this.setOnClickListener((OnClickListener)null);
         this.zzacb = true;
         this.zzacc = false;
         this.zzacd = null;
         this.zzd(var3);
         this.zzace = false;
         this.zzach = 0;
         com.google.android.gms.ads.internal.zzbs.zzbW();
         zzsa.zze(this);
         this.zzjf();
      }
   }

   private final void zzd(zznb var1) {
      this.zzjg();
      this.zzacl = new zzna(new zznb(true, "make_wv", this.zzuZ.zzAs));
      this.zzacl.zzdR().zzc(var1);
      this.zzQy = zzmu.zzb(this.zzacl.zzdR());
      this.zzacl.zza("native:view_create", this.zzQy);
      this.zzack = null;
      this.zzacj = null;
   }

   public final void zzb(zzm var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzabV = var1;
      }
   }

   public final void zzc(zzm var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzacn = var1;
      }
   }

   public final void zza(zziv var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzuZ = var1;
         this.requestLayout();
      }
   }

   public final void zzA(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         boolean var3 = var1 != this.zzabY;
         this.zzabY = var1;
         this.zzjd();
         if (var3) {
            (new zzwu(this)).zzap(var1 ? "expanded" : "default");
         }

      }
   }

   public final void zziJ() {
      this.zzwC.zzig();
   }

   protected final void onAttachedToWindow() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         super.onAttachedToWindow();
         if (!this.isDestroyed()) {
            this.zzwC.onAttachedToWindow();
         }

         boolean var2 = this.zzace;
         if (this.zzabU != null && this.zzabU.zziP()) {
            var2 = true;
            if (!this.zzacf) {
               OnGlobalLayoutListener var3;
               if ((var3 = this.zzabU.zziQ()) != null) {
                  com.google.android.gms.ads.internal.zzbs.zzbX();
                  zzajv.zza(this, (OnGlobalLayoutListener)var3);
               }

               OnScrollChangedListener var4;
               if ((var4 = this.zzabU.zziR()) != null) {
                  com.google.android.gms.ads.internal.zzbs.zzbX();
                  zzajv.zza(this, (OnScrollChangedListener)var4);
               }

               this.zzacf = true;
            }

            this.zzjb();
         }

         this.zzF(var2);
      }
   }

   protected final void onDetachedFromWindow() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.isDestroyed()) {
            this.zzwC.onDetachedFromWindow();
         }

         super.onDetachedFromWindow();
         if (this.zzacf && this.zzabU != null && this.zzabU.zziP() && this.getViewTreeObserver() != null && this.getViewTreeObserver().isAlive()) {
            OnGlobalLayoutListener var2;
            if ((var2 = this.zzabU.zziQ()) != null) {
               com.google.android.gms.ads.internal.zzbs.zzbB().zza(this.getViewTreeObserver(), var2);
            }

            OnScrollChangedListener var3;
            if ((var3 = this.zzabU.zziR()) != null) {
               this.getViewTreeObserver().removeOnScrollChangedListener(var3);
            }

            this.zzacf = false;
         }
      }

      this.zzF(false);
   }

   public final void setContext(Context var1) {
      this.zzabS.setBaseContext(var1);
      this.zzwC.zzi(this.zzabS.zzis());
   }

   public final void zzB(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzabV != null) {
            this.zzabV.zza(this.zzabU.zzcn(), var1);
         } else {
            this.zzabW = var1;
         }

      }
   }

   public final int getRequestedOrientation() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaca;
      }
   }

   public final void setRequestedOrientation(int var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzaca = var1;
         if (this.zzabV != null) {
            this.zzabV.setRequestedOrientation(this.zzaca);
         }

      }
   }

   public final Activity zzis() {
      return this.zzabS.zzis();
   }

   public final Context zzit() {
      return this.zzabS.zzit();
   }

   private final void zzjd() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzabY && !this.zzuZ.zzAt) {
            if (VERSION.SDK_INT < 18) {
               zzafr.zzaC("Disabling hardware acceleration on an AdView.");
               zzako var3 = this;
               Object var4 = this.mLock;
               synchronized(this.mLock) {
                  if (!var3.zzabZ) {
                     com.google.android.gms.ads.internal.zzbs.zzbB().zzr(var3);
                  }

                  var3.zzabZ = true;
               }
            } else {
               zzafr.zzaC("Enabling hardware acceleration on an AdView.");
               this.zzje();
            }
         } else {
            zzafr.zzaC("Enabling hardware acceleration on an overlay.");
            this.zzje();
         }

      }
   }

   private final void zzje() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzabZ) {
            com.google.android.gms.ads.internal.zzbs.zzbB().zzq(this);
         }

         this.zzabZ = false;
      }
   }

   public final void destroy() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzjg();
         this.zzwC.zzih();
         if (this.zzabV != null) {
            this.zzabV.close();
            this.zzabV.onDestroy();
            this.zzabV = null;
         }

         this.zzabU.reset();
         if (!this.zzabX) {
            com.google.android.gms.ads.internal.zzbs.zzbW();
            zzsa.zze(this);
            this.zzjf();
            this.zzabX = true;
            zzafr.v("Initiating WebView self destruct sequence in 3...");
            this.zzabU.zziT();
         }
      }
   }

   protected final void finalize() throws Throwable {
      try {
         if (this.mLock != null) {
            Object var1 = this.mLock;
            synchronized(this.mLock) {
               if (!this.zzabX) {
                  this.zzabU.reset();
                  com.google.android.gms.ads.internal.zzbs.zzbW();
                  zzsa.zze(this);
                  this.zzjf();
                  this.zzhI();
               }
            }
         }
      } finally {
         super.finalize();
      }

   }

   public final void zziB() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzafr.v("Destroying WebView!");
         this.zzhI();
         zzagz.zzZr.post(new zzakq(this));
      }
   }

   private final void zzhI() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzaco) {
            this.zzaco = true;
            com.google.android.gms.ads.internal.zzbs.zzbD().zzhI();
         }

      }
   }

   public final boolean isDestroyed() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabX;
      }
   }

   @TargetApi(21)
   protected final void onDraw(Canvas var1) {
      if (!this.isDestroyed()) {
         if (VERSION.SDK_INT != 21 || !var1.isHardwareAccelerated() || this.isAttachedToWindow()) {
            super.onDraw(var1);
            if (this.zzabU != null && this.zzabU.zzja() != null) {
               this.zzabU.zzja().zzaS();
            }

         }
      }
   }

   public final void zziK() {
      if (this.zzack == null) {
         this.zzack = zzmu.zzb(this.zzacl.zzdR());
         this.zzacl.zza("native:view_load", this.zzack);
      }

   }

   public final void onPause() {
      if (!this.isDestroyed()) {
         try {
            super.onPause();
         } catch (Exception var2) {
            zzafr.zzb("Could not pause webview.", var2);
         }
      }
   }

   public final void onResume() {
      if (!this.isDestroyed()) {
         try {
            super.onResume();
         } catch (Exception var2) {
            zzafr.zzb("Could not resume webview.", var2);
         }
      }
   }

   public final void stopLoading() {
      if (!this.isDestroyed()) {
         try {
            super.stopLoading();
         } catch (Exception var2) {
            zzafr.zzb("Could not stop loading webview.", var2);
         }
      }
   }

   public final void zzC(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzacb = var1;
      }
   }

   public final boolean zziC() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacb;
      }
   }

   public final boolean zziD() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacc;
      }
   }

   public final void zzaJ() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzacc = true;
         if (this.zzabT != null) {
            this.zzabT.zzaJ();
         }

      }
   }

   public final void zzaK() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzacc = false;
         if (this.zzabT != null) {
            this.zzabT.zzaK();
         }

      }
   }

   private final void zzjf() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzacp = null;
      }
   }

   public final void zzaV(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzQx = var1 == null ? "" : var1;
      }
   }

   public final String getRequestId() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzQx;
      }
   }

   public final zzajz zziE() {
      return null;
   }

   public final zzmz zziF() {
      return this.zzQy;
   }

   public final zzna zziG() {
      return this.zzacl;
   }

   public final void setOnClickListener(OnClickListener var1) {
      this.zzacm = new WeakReference(var1);
      super.setOnClickListener(var1);
   }

   public final OnClickListener zziL() {
      return (OnClickListener)this.zzacm.get();
   }

   public final void zzb(zznw var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzacg = var1;
      }
   }

   public final zznw zziM() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacg;
      }
   }

   public final zzaks zziH() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzacd;
      }
   }

   public final void zza(zzaks var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzacd != null) {
            zzafr.e("Attempt to create multiple AdWebViewVideoControllers.");
         } else {
            this.zzacd = var1;
         }
      }
   }

   public final boolean zziI() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzach > 0;
      }
   }

   public final void zzD(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzach += var1 ? 1 : -1;
         if (this.zzach <= 0 && this.zzabV != null) {
            this.zzabV.zzfR();
         }

      }
   }

   private final void zzjg() {
      if (this.zzacl != null) {
         zznb var1;
         if ((var1 = this.zzacl.zzdR()) != null && com.google.android.gms.ads.internal.zzbs.zzbD().zzhr() != null) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zzhr().zza(var1);
         }

      }
   }

   public final void zziN() {
      this.setBackgroundColor(0);
   }

   public final void zza(zzgh var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzace = var1.zzxS;
      }

      this.zzF(var1.zzxS);
   }

   private final void zzF(boolean var1) {
      HashMap var2;
      (var2 = new HashMap()).put("isVisible", var1 ? "1" : "0");
      this.zza("onAdVisibilityChanged", (Map)var2);
   }

   public final void zza(String var1, zzrd var2) {
      if (this.zzabU != null) {
         this.zzabU.zza(var1, var2);
      }

   }

   public final void zzb(String var1, zzrd var2) {
      if (this.zzabU != null) {
         this.zzabU.zzb(var1, var2);
      }

   }

   // $FF: synthetic method
   static Object zza(zzako var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static int zzb(zzako var0) {
      return var0.zzaci;
   }

   // $FF: synthetic method
   static int zza(zzako var0, int var1) {
      return var0.zzaci = var1;
   }

   // $FF: synthetic method
   static void zzc(zzako var0) {
      var0.destroy();
   }
}
