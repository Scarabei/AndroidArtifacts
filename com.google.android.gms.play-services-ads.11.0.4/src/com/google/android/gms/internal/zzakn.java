package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.ads.internal.overlay.zzm;
import java.util.Map;
import org.json.JSONObject;

@zzzn
final class zzakn extends FrameLayout implements zzaka {
   private static final int zzOF = Color.argb(0, 0, 0, 0);
   private final zzaka zzabQ;
   private final zzajz zzabR;

   public zzakn(zzaka var1) {
      super(var1.getContext());
      this.zzabQ = var1;
      this.zzabR = new zzajz(var1.zzit(), this, this);
      zzakb var2;
      if ((var2 = this.zzabQ.zziw()) != null) {
         var2.zzJH = this;
      }

      this.addView(this.zzabQ.getView());
   }

   public final zzajz zziE() {
      return this.zzabR;
   }

   public final View getView() {
      return this;
   }

   public final void onPause() {
      this.zzabR.onPause();
      this.zzabQ.onPause();
   }

   public final void zziB() {
      this.zzabR.onDestroy();
      this.zzabQ.zziB();
   }

   public final void zza(Context var1, zziv var2, zznb var3) {
      this.zzabR.onDestroy();
      this.zzabQ.zza(var1, var2, var3);
   }

   public final void zziN() {
      this.setBackgroundColor(zzOF);
      this.zzabQ.setBackgroundColor(zzOF);
   }

   public final WebView getWebView() {
      return this.zzabQ.getWebView();
   }

   public final void zza(String var1, Map var2) {
      this.zzabQ.zza(var1, var2);
   }

   public final void zzb(String var1, JSONObject var2) {
      this.zzabQ.zzb(var1, var2);
   }

   public final void zza(String var1, zzrd var2) {
      this.zzabQ.zza(var1, (zzrd)var2);
   }

   public final void zzb(String var1, zzrd var2) {
      this.zzabQ.zzb(var1, var2);
   }

   public final void zziq() {
      this.zzabQ.zziq();
   }

   public final void zzA(int var1) {
      this.zzabQ.zzA(var1);
   }

   public final void zzfP() {
      this.zzabQ.zzfP();
   }

   public final void zzir() {
      this.zzabQ.zzir();
   }

   public final void zza(String var1, JSONObject var2) {
      this.zzabQ.zza(var1, var2);
   }

   public final void zzi(String var1, String var2) {
      this.zzabQ.zzi(var1, var2);
   }

   public final Activity zzis() {
      return this.zzabQ.zzis();
   }

   public final Context zzit() {
      return this.zzabQ.zzit();
   }

   public final zzv zzak() {
      return this.zzabQ.zzak();
   }

   public final zzm zziu() {
      return this.zzabQ.zziu();
   }

   public final zzm zziv() {
      return this.zzabQ.zziv();
   }

   public final zziv zzam() {
      return this.zzabQ.zzam();
   }

   public final zzakb zziw() {
      return this.zzabQ.zziw();
   }

   public final boolean zzix() {
      return this.zzabQ.zzix();
   }

   public final zzcu zziy() {
      return this.zzabQ.zziy();
   }

   public final zzaje zziz() {
      return this.zzabQ.zziz();
   }

   public final boolean zziA() {
      return this.zzabQ.zziA();
   }

   public final int getRequestedOrientation() {
      return this.zzabQ.getRequestedOrientation();
   }

   public final boolean isDestroyed() {
      return this.zzabQ.isDestroyed();
   }

   public final void zzaU(String var1) {
      this.zzabQ.zzaU(var1);
   }

   public final boolean zziC() {
      return this.zzabQ.zziC();
   }

   public final boolean zziD() {
      return this.zzabQ.zziD();
   }

   public final void zzaK() {
      this.zzabQ.zzaK();
   }

   public final void zzaJ() {
      this.zzabQ.zzaJ();
   }

   public final String getRequestId() {
      return this.zzabQ.getRequestId();
   }

   public final zzmz zziF() {
      return this.zzabQ.zziF();
   }

   public final zzna zziG() {
      return this.zzabQ.zziG();
   }

   public final zzaks zziH() {
      return this.zzabQ.zziH();
   }

   public final void zzb(zzm var1) {
      this.zzabQ.zzb(var1);
   }

   public final void zza(zziv var1) {
      this.zzabQ.zza(var1);
   }

   public final void zzA(boolean var1) {
      this.zzabQ.zzA(var1);
   }

   public final void zziJ() {
      this.zzabQ.zziJ();
   }

   public final void setContext(Context var1) {
      this.zzabQ.setContext(var1);
   }

   public final void zzB(boolean var1) {
      this.zzabQ.zzB(var1);
   }

   public final void setRequestedOrientation(int var1) {
      this.zzabQ.setRequestedOrientation(var1);
   }

   public final void zzc(zzm var1) {
      this.zzabQ.zzc(var1);
   }

   public final void zzC(boolean var1) {
      this.zzabQ.zzC(var1);
   }

   public final void zzaV(String var1) {
      this.zzabQ.zzaV(var1);
   }

   public final void zziK() {
      this.zzabQ.zziK();
   }

   public final void destroy() {
      this.zzabQ.destroy();
   }

   public final void loadData(String var1, String var2, String var3) {
      this.zzabQ.loadData(var1, var2, var3);
   }

   public final void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
      this.zzabQ.loadDataWithBaseURL(var1, var2, var3, var4, var5);
   }

   public final void loadUrl(String var1) {
      this.zzabQ.loadUrl(var1);
   }

   public final void setOnClickListener(OnClickListener var1) {
      this.zzabQ.setOnClickListener(var1);
   }

   public final void setOnTouchListener(OnTouchListener var1) {
      this.zzabQ.setOnTouchListener(var1);
   }

   public final void setWebChromeClient(WebChromeClient var1) {
      this.zzabQ.setWebChromeClient(var1);
   }

   public final void setWebViewClient(WebViewClient var1) {
      this.zzabQ.setWebViewClient(var1);
   }

   public final void stopLoading() {
      this.zzabQ.stopLoading();
   }

   public final void onResume() {
      this.zzabQ.onResume();
   }

   public final void zza(zzgh var1) {
      this.zzabQ.zza((zzgh)var1);
   }

   public final OnClickListener zziL() {
      return this.zzabQ.zziL();
   }

   public final void zzb(@Nullable zznw var1) {
      this.zzabQ.zzb(var1);
   }

   @Nullable
   public final zznw zziM() {
      return this.zzabQ.zziM();
   }

   public final void zza(zzaks var1) {
      this.zzabQ.zza(var1);
   }

   public final boolean zziI() {
      return this.zzabQ.zziI();
   }

   public final void zzD(boolean var1) {
      this.zzabQ.zzD(var1);
   }
}
