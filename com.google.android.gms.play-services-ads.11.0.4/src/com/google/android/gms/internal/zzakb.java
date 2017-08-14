package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzag;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzu;
import com.google.android.gms.ads.internal.overlay.zzw;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzzn
public class zzakb extends WebViewClient {
   private static final String[] zzabn = new String[]{"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
   private static final String[] zzabo = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
   protected zzaka zzJH;
   private final HashMap zzabp;
   private final Object mLock;
   private zzim zzzL;
   private zzw zzabq;
   private zzakf zzabr;
   private zzakg zzabs;
   private zzqk zzIT;
   private zzakh zzabt;
   private boolean zzabu;
   private zzrm zzJC;
   private boolean zzwI;
   private boolean zzabv;
   private OnGlobalLayoutListener zzabw;
   private OnScrollChangedListener zzabx;
   private boolean zzaby;
   private zzag zzabz;
   private final zzwt zzabA;
   private com.google.android.gms.ads.internal.zzw zzJE;
   private zzwk zzJF;
   private zzwv zzNH;
   private zzakj zzabB;
   @Nullable
   protected zzaet zztr;
   private boolean zzabC;
   private boolean zzabD;
   private boolean zzabE;
   private int zzabF;
   private OnAttachStateChangeListener zzabG;

   public zzakb(zzaka var1, boolean var2) {
      this(var1, var2, new zzwt(var1, var1.zzit(), new zzlz(var1.getContext())), (zzwk)null);
   }

   private zzakb(zzaka var1, boolean var2, zzwt var3, zzwk var4) {
      this.zzabp = new HashMap();
      this.mLock = new Object();
      this.zzabu = false;
      this.zzJH = var1;
      this.zzwI = var2;
      this.zzabA = var3;
      this.zzJF = null;
   }

   public final void zza(int var1, int var2, boolean var3) {
      this.zzabA.zzc(var1, var2);
      if (this.zzJF != null) {
         this.zzJF.zza(var1, var2, var3);
      }

   }

   public final void zza(zzim var1, zzw var2, zzqk var3, zzag var4, boolean var5, @Nullable zzrm var6, com.google.android.gms.ads.internal.zzw var7, zzwv var8, @Nullable zzaet var9) {
      if (var7 == null) {
         var7 = new com.google.android.gms.ads.internal.zzw(var9);
      }

      this.zzJF = new zzwk(this.zzJH, var8);
      this.zztr = var9;
      this.zza((String)"/appEvent", (zzrd)(new zzqj(var3)));
      this.zza("/backButton", zzqn.zzJe);
      this.zza("/refresh", zzqn.zzJf);
      this.zza("/canOpenURLs", zzqn.zzIV);
      this.zza("/canOpenIntents", zzqn.zzIW);
      this.zza("/click", zzqn.zzIX);
      this.zza("/close", zzqn.zzIY);
      this.zza("/customClose", zzqn.zzIZ);
      this.zza("/instrument", zzqn.zzJk);
      this.zza("/delayPageLoaded", zzqn.zzJm);
      this.zza("/delayPageClosed", zzqn.zzJn);
      this.zza("/getLocationInfo", zzqn.zzJo);
      this.zza("/httpTrack", zzqn.zzJa);
      this.zza("/log", zzqn.zzJb);
      this.zza((String)"/mraid", (zzrd)(new zzrp(var7, this.zzJF)));
      this.zza((String)"/mraidLoaded", (zzrd)this.zzabA);
      this.zza((String)"/open", (zzrd)(new zzrq(var7, this.zzJF)));
      this.zza("/precache", zzqn.zzJj);
      this.zza("/touch", zzqn.zzJd);
      this.zza("/video", zzqn.zzJg);
      this.zza("/videoMeta", zzqn.zzJh);
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(this.zzJH.getContext())) {
         this.zza("/logScionEvent", zzqn.zzJi);
      }

      if (var6 != null) {
         this.zza((String)"/setInterstitialProperties", (zzrd)(new zzrl(var6)));
      }

      this.zzzL = var1;
      this.zzabq = var2;
      this.zzIT = var3;
      this.zzabz = var4;
      this.zzJE = var7;
      this.zzNH = var8;
      this.zzJC = var6;
      this.zzabu = var5;
   }

   public final com.google.android.gms.ads.internal.zzw zziO() {
      return this.zzJE;
   }

   public final boolean zzcn() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzwI;
      }
   }

   public final boolean zziP() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabv;
      }
   }

   public final OnGlobalLayoutListener zziQ() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabw;
      }
   }

   public final OnScrollChangedListener zziR() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzabx;
      }
   }

   public final boolean zziS() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzaby;
      }
   }

   public final void zziT() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzafr.v("Loading blank page in WebView, 2...");
         this.zzabC = true;
         this.zzJH.zzaU("about:blank");
      }
   }

   public final void onPageFinished(WebView var1, String var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzabC) {
            zzafr.v("Blank page loaded, 1...");
            this.zzJH.zziB();
            return;
         }
      }

      this.zzabD = true;
      if (this.zzabs != null) {
         this.zzabs.zzj(this.zzJH);
         this.zzabs = null;
      }

      this.zziZ();
   }

   private final void zza(View var1, zzaet var2, int var3) {
      if (var2.zzgZ() && var3 > 0) {
         var2.zzk(var1);
         if (var2.zzgZ()) {
            zzagz.zzZr.postDelayed(new zzakc(this, var1, var2, var3), 100L);
         }
      }

   }

   private final void zziU() {
      if (this.zzabG != null) {
         this.zzJH.getView().removeOnAttachStateChangeListener(this.zzabG);
      }
   }

   public final void zziV() {
      zzaet var1 = this.zztr;
      if (this.zztr != null) {
         WebView var2;
         if (ViewCompat.isAttachedToWindow(var2 = this.zzJH.getWebView())) {
            this.zza(var2, var1, 10);
            return;
         }

         this.zziU();
         this.zzabG = new zzakd(this, var1);
         this.zzJH.getView().addOnAttachStateChangeListener(this.zzabG);
      }

   }

   public final void zziW() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzaby = true;
      }

      ++this.zzabF;
      this.zziZ();
   }

   public final void zziX() {
      --this.zzabF;
      this.zziZ();
   }

   public final void zziY() {
      this.zzabE = true;
      this.zziZ();
   }

   private final void zziZ() {
      if (this.zzabr != null && (this.zzabD && this.zzabF <= 0 || this.zzabE)) {
         this.zzabr.zza(this.zzJH, !this.zzabE);
         this.zzabr = null;
      }

      this.zzJH.zziK();
   }

   public final void zza(zzc var1) {
      boolean var2 = this.zzJH.zziA();
      this.zza(new AdOverlayInfoParcel(var1, var2 && !this.zzJH.zzam().zzAt ? null : this.zzzL, var2 ? null : this.zzabq, this.zzabz, this.zzJH.zziz()));
   }

   public final void zza(boolean var1, int var2) {
      boolean var3 = this.zzJH.zziA();
      this.zza(new AdOverlayInfoParcel(var3 && !this.zzJH.zzam().zzAt ? null : this.zzzL, this.zzabq, this.zzabz, this.zzJH, var1, var2, this.zzJH.zziz()));
   }

   public final void zza(boolean var1, int var2, String var3) {
      boolean var4 = this.zzJH.zziA();
      this.zza(new AdOverlayInfoParcel(var4 && !this.zzJH.zzam().zzAt ? null : this.zzzL, var4 ? null : new zzaki(this.zzJH, this.zzabq), this.zzIT, this.zzabz, this.zzJH, var1, var2, var3, this.zzJH.zziz()));
   }

   public final void zza(boolean var1, int var2, String var3, String var4) {
      boolean var5 = this.zzJH.zziA();
      this.zza(new AdOverlayInfoParcel(var5 && !this.zzJH.zzam().zzAt ? null : this.zzzL, var5 ? null : new zzaki(this.zzJH, this.zzabq), this.zzIT, this.zzabz, this.zzJH, var1, var2, var3, var4, this.zzJH.zziz()));
   }

   private final void zza(AdOverlayInfoParcel var1) {
      boolean var2 = false;
      if (this.zzJF != null) {
         var2 = this.zzJF.zzfC();
      }

      com.google.android.gms.ads.internal.zzbs.zzbx();
      zzu.zza(this.zzJH.getContext(), var1, !var2);
      if (this.zztr != null) {
         String var3 = var1.url;
         if (var1.url == null && var1.zzPd != null) {
            var3 = var1.zzPd.url;
         }

         this.zztr.zzaA(var3);
      }

   }

   public final void zza(String var1, zzrd var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         Object var4;
         if ((var4 = (List)this.zzabp.get(var1)) == null) {
            var4 = new CopyOnWriteArrayList();
            this.zzabp.put(var1, var4);
         }

         ((List)var4).add(var2);
      }
   }

   public final void zzb(String var1, zzrd var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         List var4;
         if ((var4 = (List)this.zzabp.get(var1)) != null) {
            var4.remove(var2);
         }
      }
   }

   public final void reset() {
      if (this.zztr != null) {
         this.zztr.zzhb();
         this.zztr = null;
      }

      this.zziU();
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzabp.clear();
         this.zzzL = null;
         this.zzabq = null;
         this.zzabr = null;
         this.zzabs = null;
         this.zzIT = null;
         this.zzabu = false;
         this.zzwI = false;
         this.zzabv = false;
         this.zzaby = false;
         this.zzabz = null;
         this.zzabt = null;
         if (this.zzJF != null) {
            this.zzJF.zzk(true);
            this.zzJF = null;
         }

      }
   }

   public final void zza(zzakf var1) {
      this.zzabr = var1;
   }

   public final void zza(zzakg var1) {
      this.zzabs = var1;
   }

   public final void zza(zzakh var1) {
      this.zzabt = var1;
   }

   public final void zza(zzakj var1) {
      this.zzabB = var1;
   }

   public final zzakj zzja() {
      return this.zzabB;
   }

   public final void onLoadResource(WebView var1, String var2) {
      String var10001 = String.valueOf(var2);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Loading resource: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Loading resource: ");
      }

      zzafr.v(var10000);
      Uri var3 = Uri.parse(var2);
      if ("gmsg".equalsIgnoreCase(var3.getScheme()) && "mobileads.google.com".equalsIgnoreCase(var3.getHost())) {
         this.zzi(var3);
      }

   }

   public final void onReceivedError(WebView var1, int var2, String var3, String var4) {
      String var5;
      if (var2 < 0 && -var2 - 1 < zzabn.length) {
         var5 = zzabn[-var2 - 1];
      } else {
         var5 = String.valueOf(var2);
      }

      this.zzc(this.zzJH.getContext(), "http_err", var5, var4);
      super.onReceivedError(var1, var2, var3, var4);
   }

   public final void onReceivedSslError(WebView var1, SslErrorHandler var2, SslError var3) {
      if (var3 != null) {
         int var4;
         String var5;
         if ((var4 = var3.getPrimaryError()) >= 0 && var4 < zzabo.length) {
            var5 = zzabo[var4];
         } else {
            var5 = String.valueOf(var4);
         }

         this.zzc(this.zzJH.getContext(), "ssl_err", var5, com.google.android.gms.ads.internal.zzbs.zzbB().zza(var3));
      }

      super.onReceivedSslError(var1, var2, var3);
   }

   private final void zzc(Context var1, String var2, String var3, String var4) {
      zzme var6 = zzmo.zzEw;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
         Bundle var5;
         (var5 = new Bundle()).putString("err", var2);
         var5.putString("code", var3);
         Uri var7;
         var5.putString("host", !TextUtils.isEmpty(var4) && (var7 = Uri.parse(var4)).getHost() != null ? var7.getHost() : "");
         com.google.android.gms.ads.internal.zzbs.zzbz().zza(var1, this.zzJH.zziz().zzaP, "gmob-apps", var5, true);
      }
   }

   public final boolean shouldOverrideUrlLoading(WebView var1, String var2) {
      String var10001 = String.valueOf(var2);
      String var10000;
      String var10002;
      if (var10001.length() != 0) {
         var10000 = "AdWebView shouldOverrideUrlLoading: ".concat(var10001);
      } else {
         var10002 = new String;
         var10000 = var10002;
         var10002.<init>("AdWebView shouldOverrideUrlLoading: ");
      }

      zzafr.v(var10000);
      Uri var3 = Uri.parse(var2);
      if ("gmsg".equalsIgnoreCase(var3.getScheme()) && "mobileads.google.com".equalsIgnoreCase(var3.getHost())) {
         this.zzi(var3);
      } else {
         if (this.zzabu && var1 == this.zzJH.getWebView()) {
            String var6 = var3.getScheme();
            if ("http".equalsIgnoreCase(var6) || "https".equalsIgnoreCase(var6)) {
               if (this.zzzL != null) {
                  zzme var5 = zzmo.zzDo;
                  if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue()) {
                     this.zzzL.onAdClicked();
                     if (this.zztr != null) {
                        this.zztr.zzaA(var2);
                     }

                     this.zzzL = null;
                  }
               }

               return super.shouldOverrideUrlLoading(var1, var2);
            }
         }

         if (!this.zzJH.getWebView().willNotDraw()) {
            try {
               zzcu var4;
               if ((var4 = this.zzJH.zziy()) != null && var4.zzc(var3)) {
                  var3 = var4.zza(var3, this.zzJH.getContext(), this.zzJH.getView());
               }
            } catch (zzcv var7) {
               var10001 = String.valueOf(var2);
               if (var10001.length() != 0) {
                  var10000 = "Unable to append parameter to URL: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Unable to append parameter to URL: ");
               }

               zzafr.zzaT(var10000);
            }

            if (this.zzJE != null && !this.zzJE.zzaR()) {
               this.zzJE.zzt(var2);
            } else {
               this.zza(new zzc("android.intent.action.VIEW", var3.toString(), (String)null, (String)null, (String)null, (String)null, (String)null));
            }
         } else {
            var10001 = String.valueOf(var2);
            if (var10001.length() != 0) {
               var10000 = "AdWebView unable to handle URL: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("AdWebView unable to handle URL: ");
            }

            zzafr.zzaT(var10000);
         }
      }

      return true;
   }

   @TargetApi(11)
   public WebResourceResponse shouldInterceptRequest(WebView var1, String var2) {
      try {
         String var3;
         if (!(var3 = zzaez.zzb(var2, this.zzJH.getContext())).equals(var2)) {
            HttpURLConnection var7 = (HttpURLConnection)(new URL(var3)).openConnection();
            com.google.android.gms.ads.internal.zzbs.zzbz().zza(this.zzJH.getContext(), this.zzJH.zziz().zzaP, true, var7);
            return new WebResourceResponse(var7.getContentType(), var7.getHeaderField("encoding"), var7.getInputStream());
         } else {
            zzia var4;
            if ((var4 = zzia.zzB(var2)) == null) {
               return null;
            } else {
               zzhx var5;
               return (var5 = com.google.android.gms.ads.internal.zzbs.zzbE().zza(var4)) != null && var5.zzcY() ? new WebResourceResponse("", "", var5.zzcZ()) : null;
            }
         }
      } catch (Throwable var6) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza(var6, "AdWebViewClient.shouldInterceptRequest");
         return null;
      }
   }

   public final void zzE(boolean var1) {
      this.zzabu = false;
   }

   public final void zza(OnGlobalLayoutListener var1, OnScrollChangedListener var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         this.zzabv = true;
         this.zzJH.zziJ();
         this.zzabw = var1;
         this.zzabx = var2;
      }
   }

   public final void zzfL() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzabu = false;
         this.zzwI = true;
         com.google.android.gms.ads.internal.zzbs.zzbz();
         zzagz.runOnUiThread(new zzake(this));
      }
   }

   public final void zzb(int var1, int var2) {
      if (this.zzJF != null) {
         this.zzJF.zzb(var1, var2);
      }

   }

   public boolean shouldOverrideKeyEvent(WebView var1, KeyEvent var2) {
      switch(var2.getKeyCode()) {
      case 79:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      case 90:
      case 91:
      case 126:
      case 127:
      case 128:
      case 129:
      case 130:
      case 222:
         return true;
      default:
         return false;
      }
   }

   private final void zzi(Uri var1) {
      String var2 = var1.getPath();
      List var3;
      if ((var3 = (List)this.zzabp.get(var2)) == null) {
         String var8 = String.valueOf(var1);
         zzafr.v((new StringBuilder(32 + String.valueOf(var8).length())).append("No GMSG handler found for GMSG: ").append(var8).toString());
      } else {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         Map var4 = zzagz.zzg(var1);
         Iterator var5;
         if (zzafr.zzz(2)) {
            String var10001 = String.valueOf(var2);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Received GMSG: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Received GMSG: ");
            }

            zzafr.v(var10000);
            var5 = var4.keySet().iterator();

            while(var5.hasNext()) {
               String var6 = (String)var5.next();
               String var7 = (String)var4.get(var6);
               zzafr.v((new StringBuilder(4 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("  ").append(var6).append(": ").append(var7).toString());
            }
         }

         var5 = var3.iterator();

         while(var5.hasNext()) {
            ((zzrd)var5.next()).zza(this.zzJH, var4);
         }

      }
   }

   // $FF: synthetic method
   static void zza(zzakb var0, View var1, zzaet var2, int var3) {
      var0.zza(var1, var2, var3);
   }

   // $FF: synthetic method
   static zzakh zza(zzakb var0) {
      return var0.zzabt;
   }

   // $FF: synthetic method
   static zzakh zza(zzakb var0, zzakh var1) {
      return var0.zzabt = null;
   }
}
