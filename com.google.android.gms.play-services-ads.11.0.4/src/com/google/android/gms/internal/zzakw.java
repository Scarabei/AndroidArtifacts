package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView.WebViewTransport;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zzm;

@zzzn
@TargetApi(11)
public class zzakw extends WebChromeClient {
   private final zzaka zzJH;

   private static boolean zza(Context var0, String var1, String var2, String var3, JsResult var4, JsPromptResult var5, boolean var6) {
      try {
         Builder var7;
         (var7 = new Builder(var0)).setTitle(var1);
         if (var6) {
            LinearLayout var13;
            (var13 = new LinearLayout(var0)).setOrientation(1);
            TextView var14;
            (var14 = new TextView(var0)).setText(var2);
            EditText var15;
            (var15 = new EditText(var0)).setText(var3);
            var13.addView(var14);
            var13.addView(var15);
            var7.setView(var13).setPositiveButton(17039370, new zzalc(var5, var15)).setNegativeButton(17039360, new zzalb(var5)).setOnCancelListener(new zzala(var5)).create().show();
         } else {
            var7.setMessage(var2).setPositiveButton(17039370, new zzakz(var4)).setNegativeButton(17039360, new zzaky(var4)).setOnCancelListener(new zzakx(var4)).create().show();
         }
      } catch (BadTokenException var16) {
         zzafr.zzc("Fail to display Dialog.", var16);
      }

      return true;
   }

   public zzakw(zzaka var1) {
      this.zzJH = var1;
   }

   public final boolean onCreateWindow(WebView var1, boolean var2, boolean var3, Message var4) {
      WebViewTransport var5 = (WebViewTransport)var4.obj;
      WebView var6;
      (var6 = new WebView(var1.getContext())).setWebViewClient(this.zzJH.zziw());
      var5.setWebView(var6);
      var4.sendToTarget();
      return true;
   }

   public final void onCloseWindow(WebView var1) {
      if (!(var1 instanceof zzaka)) {
         zzafr.zzaT("Tried to close a WebView that wasn't an AdWebView.");
      } else {
         zzm var2;
         if ((var2 = ((zzaka)var1).zziu()) == null) {
            zzafr.zzaT("Tried to close an AdWebView not associated with an overlay.");
         } else {
            var2.close();
         }
      }
   }

   public final boolean onConsoleMessage(ConsoleMessage var1) {
      String var3 = String.valueOf(var1.message());
      String var4 = String.valueOf(var1.sourceId());
      int var5 = var1.lineNumber();
      String var2;
      if ((var2 = (new StringBuilder(19 + String.valueOf(var3).length() + String.valueOf(var4).length())).append("JS: ").append(var3).append(" (").append(var4).append(":").append(var5).append(")").toString()).contains("Application Cache")) {
         return super.onConsoleMessage(var1);
      } else {
         switch(zzald.zzacK[var1.messageLevel().ordinal()]) {
         case 1:
            zzafr.e(var2);
            break;
         case 2:
            zzafr.zzaT(var2);
            break;
         case 3:
         case 4:
            zzafr.zzaS(var2);
            break;
         case 5:
            zzafr.zzaC(var2);
            break;
         default:
            zzafr.zzaS(var2);
         }

         return super.onConsoleMessage(var1);
      }
   }

   public final void onExceededDatabaseQuota(String var1, String var2, long var3, long var5, long var7, QuotaUpdater var9) {
      long var10;
      if ((var10 = 5242880L - var7) <= 0L) {
         var9.updateQuota(var3);
      } else {
         long var12;
         if (var3 == 0L) {
            var12 = var5 <= var10 && var5 <= 1048576L ? var5 : 0L;
         } else {
            long var10000;
            long var14;
            if (var5 == 0L) {
               var14 = Math.min(131072L, var10);
               var10000 = Math.min(var3 + var14, 1048576L);
            } else {
               var14 = Math.min(1048576L - var3, var10);
               var10000 = var5 <= var14 ? var3 + var5 : var3;
            }

            var12 = var10000;
         }

         var9.updateQuota(var12);
      }
   }

   public final void onHideCustomView() {
      zzm var1;
      if ((var1 = this.zzJH.zziu()) == null) {
         zzafr.zzaT("Could not get ad overlay when hiding custom view.");
      } else {
         var1.zzfI();
      }
   }

   private static Context zza(WebView var0) {
      if (!(var0 instanceof zzaka)) {
         return var0.getContext();
      } else {
         zzaka var1;
         Activity var2;
         return (Context)((var2 = (var1 = (zzaka)var0).zzis()) != null ? var2 : var1.getContext());
      }
   }

   public final boolean onJsAlert(WebView var1, String var2, String var3, JsResult var4) {
      return zza(zza(var1), var2, var3, (String)null, var4, (JsPromptResult)null, false);
   }

   public final boolean onJsBeforeUnload(WebView var1, String var2, String var3, JsResult var4) {
      return zza(zza(var1), var2, var3, (String)null, var4, (JsPromptResult)null, false);
   }

   public final boolean onJsConfirm(WebView var1, String var2, String var3, JsResult var4) {
      return zza(zza(var1), var2, var3, (String)null, var4, (JsPromptResult)null, false);
   }

   public final boolean onJsPrompt(WebView var1, String var2, String var3, String var4, JsPromptResult var5) {
      return zza(zza(var1), var2, var3, var4, (JsResult)null, var5, true);
   }

   public final void onReachedMaxAppCacheSize(long var1, long var3, QuotaUpdater var5) {
      long var6 = 5242880L - var3;
      long var8 = var1 + 131072L;
      if (var6 < var8) {
         var5.updateQuota(0L);
      } else {
         var5.updateQuota(var8);
      }
   }

   public final void onShowCustomView(View var1, CustomViewCallback var2) {
      this.zza(var1, -1, var2);
   }

   public final void onGeolocationPermissionsShowPrompt(String var1, Callback var2) {
      if (var2 != null) {
         boolean var10002;
         label14: {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            if (!zzagz.zzc(this.zzJH.getContext(), this.zzJH.getContext().getPackageName(), "android.permission.ACCESS_FINE_LOCATION")) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               if (!zzagz.zzc(this.zzJH.getContext(), this.zzJH.getContext().getPackageName(), "android.permission.ACCESS_COARSE_LOCATION")) {
                  var10002 = false;
                  break label14;
               }
            }

            var10002 = true;
         }

         var2.invoke(var1, var10002, true);
      }

   }

   protected final void zza(View var1, int var2, CustomViewCallback var3) {
      zzm var4;
      if ((var4 = this.zzJH.zziu()) == null) {
         zzafr.zzaT("Could not get ad overlay when showing custom view.");
         var3.onCustomViewHidden();
      } else {
         var4.zza(var1, var3);
         var4.setRequestedOrientation(var2);
      }
   }
}
