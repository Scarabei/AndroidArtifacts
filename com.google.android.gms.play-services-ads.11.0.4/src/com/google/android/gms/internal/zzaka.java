package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.ads.internal.js.zzai;
import com.google.android.gms.ads.internal.overlay.zzm;
import java.util.Map;
import org.json.JSONObject;

@zzzn
public interface zzaka extends zzai, zzbl, zzgi {
   WebView getWebView();

   View getView();

   void zza(String var1, Map var2);

   void zziq();

   void zzA(int var1);

   void zzfP();

   void zzir();

   void zza(String var1, JSONObject var2);

   void zzi(String var1, String var2);

   Activity zzis();

   Context zzit();

   zzv zzak();

   zzm zziu();

   zzm zziv();

   zziv zzam();

   @Nullable
   zzakb zziw();

   boolean zzix();

   zzcu zziy();

   zzaje zziz();

   boolean zziA();

   int getRequestedOrientation();

   boolean isDestroyed();

   void zziB();

   void zzaU(String var1);

   boolean zziC();

   boolean zziD();

   String getRequestId();

   @Nullable
   zzajz zziE();

   @Nullable
   zzmz zziF();

   zzna zziG();

   @Nullable
   zzaks zziH();

   boolean zziI();

   void zzb(zzm var1);

   void zza(zziv var1);

   void zzA(boolean var1);

   void zziJ();

   void setContext(Context var1);

   void zzB(boolean var1);

   void setRequestedOrientation(int var1);

   void zzc(zzm var1);

   void zza(Context var1, zziv var2, zznb var3);

   void zzC(boolean var1);

   void zzaV(String var1);

   void zziK();

   @Nullable
   OnClickListener zziL();

   void zzb(zznw var1);

   zznw zziM();

   void zza(zzaks var1);

   void zzD(boolean var1);

   void zziN();

   void destroy();

   Context getContext();

   LayoutParams getLayoutParams();

   void getLocationOnScreen(int[] var1);

   int getHeight();

   int getMeasuredHeight();

   int getWidth();

   int getMeasuredWidth();

   ViewParent getParent();

   void loadData(String var1, String var2, String var3);

   void loadDataWithBaseURL(String var1, String var2, String var3, String var4, @Nullable String var5);

   void loadUrl(String var1);

   void measure(int var1, int var2);

   void setBackgroundColor(int var1);

   void setOnClickListener(OnClickListener var1);

   void setOnTouchListener(OnTouchListener var1);

   void setWebChromeClient(WebChromeClient var1);

   void setWebViewClient(WebViewClient var1);

   void stopLoading();

   void onPause();

   void onResume();
}
