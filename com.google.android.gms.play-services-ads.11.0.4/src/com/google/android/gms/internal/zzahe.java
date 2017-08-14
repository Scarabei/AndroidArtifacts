package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@zzzn
@TargetApi(8)
public class zzahe {
   public boolean isAttachedToWindow(View var1) {
      return var1.getWindowToken() != null || var1.getWindowVisibility() != 8;
   }

   public boolean zza(Context var1, WebSettings var2) {
      return false;
   }

   public boolean zzq(View var1) {
      return false;
   }

   public boolean zzr(View var1) {
      return false;
   }

   public boolean zza(Window var1) {
      return false;
   }

   public static boolean zzk(zzaka var0) {
      if (var0 == null) {
         return false;
      } else {
         var0.onPause();
         return true;
      }
   }

   public static boolean zzl(zzaka var0) {
      if (var0 == null) {
         return false;
      } else {
         var0.onResume();
         return true;
      }
   }

   public int zzhT() {
      return 0;
   }

   public int zzhU() {
      return 1;
   }

   public int zzhV() {
      return 5;
   }

   public String getDefaultUserAgent(Context var1) {
      return "";
   }

   public void zzR(Context var1) {
      com.google.android.gms.ads.internal.zzbs.zzbD().zzhH();
   }

   public boolean zza(Request var1) {
      return false;
   }

   public zzakb zzb(zzaka var1, boolean var2) {
      return new zzakb(var1, var2);
   }

   public WebChromeClient zzm(zzaka var1) {
      return null;
   }

   public Set zzh(Uri var1) {
      if (var1.isOpaque()) {
         return Collections.emptySet();
      } else {
         String var2;
         if ((var2 = var1.getEncodedQuery()) == null) {
            return Collections.emptySet();
         } else {
            LinkedHashSet var3 = new LinkedHashSet();
            int var4 = 0;

            int var6;
            do {
               int var5;
               var6 = (var5 = var2.indexOf(38, var4)) == -1 ? var2.length() : var5;
               int var7;
               if ((var7 = var2.indexOf(61, var4)) > var6 || var7 == -1) {
                  var7 = var6;
               }

               String var8 = var2.substring(var4, var7);
               var3.add(Uri.decode(var8));
            } while((var4 = var6 + 1) < var2.length());

            return Collections.unmodifiableSet(var3);
         }
      }
   }

   public void zzb(Activity var1, OnGlobalLayoutListener var2) {
      Window var3;
      if ((var3 = var1.getWindow()) != null && var3.getDecorView() != null && var3.getDecorView().getViewTreeObserver() != null) {
         this.zza(var3.getDecorView().getViewTreeObserver(), var2);
      }

   }

   public void zza(ViewTreeObserver var1, OnGlobalLayoutListener var2) {
      var1.removeGlobalOnLayoutListener(var2);
   }

   public LayoutParams zzhW() {
      return new LayoutParams(-2, -2);
   }

   public void setBackground(View var1, Drawable var2) {
      var1.setBackgroundDrawable(var2);
   }

   public String zza(SslError var1) {
      return "";
   }

   public Drawable zza(Context var1, Bitmap var2, boolean var3, float var4) {
      return new BitmapDrawable(var1.getResources(), var2);
   }

   public CookieManager zzS(Context var1) {
      try {
         CookieSyncManager.createInstance(var1);
         return CookieManager.getInstance();
      } catch (Exception var3) {
         zzafr.zzb("Failed to obtain CookieManager.", var3);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var3, (String)"ApiLevelUtil.getCookieManager");
         return null;
      }
   }

   public int zzhX() {
      return 0;
   }

   private zzahe() {
   }

   // $FF: synthetic method
   zzahe(zzahf var1) {
      this();
   }
}
