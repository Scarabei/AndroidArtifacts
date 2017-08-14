package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.dynamite.DynamiteModule;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

@zzzn
public final class zzaiy {
   public static final Handler zzaaH = new Handler(Looper.getMainLooper());
   private static final String zzaaI = AdView.class.getName();
   private static final String zzaaJ = InterstitialAd.class.getName();
   private static final String zzaaK = PublisherAdView.class.getName();
   private static final String zzaaL = PublisherInterstitialAd.class.getName();
   private static final String zzaaM = SearchAdView.class.getName();
   private static final String zzaaN = AdLoader.class.getName();

   public static void zza(boolean var0, HttpURLConnection var1, @Nullable String var2) {
      var1.setConnectTimeout(60000);
      var1.setInstanceFollowRedirects(true);
      var1.setReadTimeout(60000);
      if (var2 != null) {
         var1.setRequestProperty("User-Agent", var2);
      }

      var1.setUseCaches(false);
   }

   public static int zzc(Context var0, int var1) {
      return zza(var0.getResources().getDisplayMetrics(), var1);
   }

   public static int zza(DisplayMetrics var0, int var1) {
      return (int)TypedValue.applyDimension(1, (float)var1, var0);
   }

   public static int zzd(Context var0, int var1) {
      Display var2 = ((WindowManager)var0.getSystemService("window")).getDefaultDisplay();
      DisplayMetrics var3 = new DisplayMetrics();
      var2.getMetrics(var3);
      return zzb(var3, var1);
   }

   public static int zzb(DisplayMetrics var0, int var1) {
      return Math.round((float)var1 / var0.density);
   }

   public final void zza(ViewGroup var1, zziv var2, String var3, String var4) {
      zzajc.zzaT(var4);
      this.zza(var1, var2, var3, -65536, -16777216);
   }

   public final void zza(ViewGroup var1, zziv var2, String var3) {
      this.zza(var1, var2, var3, -16777216, -1);
   }

   private final void zza(ViewGroup var1, zziv var2, String var3, int var4, int var5) {
      if (var1.getChildCount() == 0) {
         Context var6 = var1.getContext();
         TextView var7;
         (var7 = new TextView(var6)).setGravity(17);
         var7.setText(var3);
         var7.setTextColor(var4);
         var7.setBackgroundColor(var5);
         FrameLayout var8;
         (var8 = new FrameLayout(var6)).setBackgroundColor(var4);
         int var9 = zzc(var6, 3);
         var8.addView(var7, new LayoutParams(var2.widthPixels - var9, var2.heightPixels - var9, 17));
         var1.addView(var8, var2.widthPixels, var2.heightPixels);
      }
   }

   public static String zzV(Context var0) {
      ContentResolver var1;
      String var10000 = (var1 = var0.getContentResolver()) == null ? null : Secure.getString(var1, "android_id");
      String var2 = var10000;
      return zzaR(var10000 != null && !zzik() ? var2 : "emulator");
   }

   @Nullable
   public static String zzW(Context var0) {
      ContentResolver var1;
      return (var1 = var0.getContentResolver()) == null ? null : Secure.getString(var1, "android_id");
   }

   public static boolean zzik() {
      return Build.DEVICE.startsWith("generic");
   }

   public static boolean zzil() {
      return Looper.myLooper() == Looper.getMainLooper();
   }

   public static String zzaR(String var0) {
      int var1 = 0;

      while(var1 < 2) {
         try {
            MessageDigest var2;
            (var2 = MessageDigest.getInstance("MD5")).update(var0.getBytes());
            return String.format(Locale.US, "%032X", new BigInteger(1, var2.digest()));
         } catch (NoSuchAlgorithmException var3) {
            ++var1;
         }
      }

      return null;
   }

   public static boolean zzX(Context var0) {
      return 0 == zze.zzoW().isGooglePlayServicesAvailable(var0);
   }

   public static int zzP(Context var0) {
      return DynamiteModule.zzF(var0, "com.google.android.gms.ads.dynamite");
   }

   public static int zzQ(Context var0) {
      return DynamiteModule.zzE(var0, "com.google.android.gms.ads.dynamite");
   }

   public static boolean zzY(Context var0) {
      int var1;
      return (var1 = zze.zzoW().isGooglePlayServicesAvailable(var0)) == 0 || var1 == 2;
   }

   public static boolean zzZ(Context var0) {
      if (var0.getResources().getConfiguration().orientation != 2) {
         return false;
      } else {
         DisplayMetrics var1;
         return (int)((float)(var1 = var0.getResources().getDisplayMetrics()).heightPixels / var1.density) < 600;
      }
   }

   @TargetApi(17)
   public static boolean zzaa(Context var0) {
      DisplayMetrics var1 = var0.getResources().getDisplayMetrics();
      Display var2 = ((WindowManager)var0.getSystemService("window")).getDefaultDisplay();
      int var3;
      int var4;
      if (zzq.zzsa()) {
         var2.getRealMetrics(var1);
         var3 = var1.heightPixels;
         var4 = var1.widthPixels;
      } else {
         try {
            var3 = ((Integer)Display.class.getMethod("getRawHeight").invoke(var2)).intValue();
            var4 = ((Integer)Display.class.getMethod("getRawWidth").invoke(var2)).intValue();
         } catch (Exception var7) {
            return false;
         }
      }

      var2.getMetrics(var1);
      int var5 = var1.heightPixels;
      int var6 = var1.widthPixels;
      return var5 == var3 && var6 == var4;
   }

   public static int zzab(Context var0) {
      int var1;
      return (var1 = var0.getResources().getIdentifier("navigation_bar_width", "dimen", "android")) > 0 ? var0.getResources().getDimensionPixelSize(var1) : 0;
   }

   @Nullable
   public static String zza(StackTraceElement[] var0, String var1) {
      String var2 = null;

      for(int var3 = 0; var3 + 1 < var0.length; ++var3) {
         StackTraceElement var4;
         String var5 = (var4 = var0[var3]).getClassName();
         String var6 = var4.getMethodName();
         if ("loadAd".equalsIgnoreCase(var6) && (zzaaI.equalsIgnoreCase(var5) || zzaaJ.equalsIgnoreCase(var5) || zzaaK.equalsIgnoreCase(var5) || zzaaL.equalsIgnoreCase(var5) || zzaaM.equalsIgnoreCase(var5) || zzaaN.equalsIgnoreCase(var5))) {
            var2 = var0[var3 + 1].getClassName();
            break;
         }
      }

      if (var1 != null) {
         byte var9 = 3;
         String var8 = ".";
         StringTokenizer var10 = new StringTokenizer(var1, var8);
         StringBuilder var11 = new StringBuilder();
         int var13 = var9 - 1;
         String var14;
         if (!var10.hasMoreElements()) {
            var14 = var1;
         } else {
            var11.append(var10.nextToken());

            while(var13-- > 0 && var10.hasMoreElements()) {
               var11.append(".").append(var10.nextToken());
            }

            var14 = var11.toString();
         }

         String var12 = var14;
         if (var2 != null && !var2.contains(var12)) {
            return var2;
         }
      }

      return null;
   }

   public final void zza(Context var1, @Nullable String var2, String var3, Bundle var4, boolean var5) {
      zza(var1, (String)null, var3, var4, true, new zzaiz(this));
   }

   public static void zza(Context var0, @Nullable String var1, String var2, Bundle var3, boolean var4, zzajb var5) {
      if (var4) {
         Context var6;
         if ((var6 = var0.getApplicationContext()) == null) {
            var6 = var0;
         }

         var3.putString("os", VERSION.RELEASE);
         var3.putString("api", String.valueOf(VERSION.SDK_INT));
         var3.putString("appid", var6.getPackageName());
         if (var1 == null) {
            zze.zzoW();
            int var7 = zze.zzau(var0);
            var1 = (new StringBuilder(23)).append(var7).append(".11020000").toString();
         }

         var3.putString("js", var1);
      }

      Builder var9 = (new Builder()).scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", var2);
      Iterator var10 = var3.keySet().iterator();

      while(var10.hasNext()) {
         String var8 = (String)var10.next();
         var9.appendQueryParameter(var8, var3.getString(var8));
      }

      var5.zzaN(var9.toString());
   }
}
