package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.os.Debug.MemoryInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.google.android.gms.ads.internal.zzax;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.dynamite.DynamiteModule;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzagz {
   public static final Handler zzZr = new zzafs(Looper.getMainLooper());
   private static AtomicReference zzZs = new AtomicReference((Object)null);
   private static AtomicReference zzZt = new AtomicReference((Object)null);
   private final Object mLock = new Object();
   private boolean zzZu = true;
   private String zzJP;
   private boolean zzZv = false;
   private zzl zzLG;

   public final void zza(Context var1, String var2, boolean var3, HttpURLConnection var4) {
      var4.setConnectTimeout(60000);
      var4.setInstanceFollowRedirects(var3);
      var4.setReadTimeout(60000);
      var4.setRequestProperty("User-Agent", this.zzs(var1, var2));
      var4.setUseCaches(false);
   }

   public static boolean zzD(Context var0) {
      Intent var1;
      (var1 = new Intent()).setClassName(var0, "com.google.android.gms.ads.AdActivity");
      ResolveInfo var2 = var0.getPackageManager().resolveActivity(var1, 65536);
      boolean var3 = true;
      if (var2 != null && var2.activityInfo != null) {
         String var4 = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
         if ((var2.activityInfo.configChanges & 16) == 0) {
            zzafr.zzaT(String.format(var4, "keyboard"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 32) == 0) {
            zzafr.zzaT(String.format(var4, "keyboardHidden"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 128) == 0) {
            zzafr.zzaT(String.format(var4, "orientation"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 256) == 0) {
            zzafr.zzaT(String.format(var4, "screenLayout"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 512) == 0) {
            zzafr.zzaT(String.format(var4, "uiMode"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 1024) == 0) {
            zzafr.zzaT(String.format(var4, "screenSize"));
            var3 = false;
         }

         if ((var2.activityInfo.configChanges & 2048) == 0) {
            zzafr.zzaT(String.format(var4, "smallestScreenSize"));
            var3 = false;
         }
      } else {
         zzafr.zzaT("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
         var3 = false;
      }

      return var3;
   }

   public static boolean zzc(Context var0, String var1, String var2) {
      return zzbha.zzaP(var0).checkPermission(var2, var1) == 0;
   }

   public static void zza(Context var0, String var1, List var2) {
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         (new zzaiq(var0, var1, var4)).zzgp();
      }

   }

   public static void zzd(Context var0, String var1, String var2) {
      ArrayList var3;
      (var3 = new ArrayList()).add(var2);
      zza((Context)var0, (String)var1, (List)var3);
   }

   public static void zza(List var0, String var1) {
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         (new zzaiq(var3, var1)).zzgp();
      }

   }

   public final void zza(Context var1, List var2) {
      if (var1 instanceof Activity) {
         if (!TextUtils.isEmpty(aeo.zzbU((Activity)var1))) {
            if (var2 == null) {
               zzafr.v("Cannot ping urls: empty list.");
            } else if (!zznl.zzi(var1)) {
               zzafr.v("Cannot ping url because custom tabs is not supported");
            } else {
               zznl var3 = new zznl();
               zzaha var4 = new zzaha(this, var2, var3, var1);
               var3.zza((zznm)var4);
               var3.zzd((Activity)var1);
            }
         }
      }
   }

   public static String zza(InputStreamReader var0) throws IOException {
      StringBuilder var1 = new StringBuilder(8192);
      char[] var2 = new char[2048];

      int var3;
      while((var3 = var0.read(var2)) != -1) {
         var1.append(var2, 0, var3);
      }

      return var1.toString();
   }

   public final boolean zzE(Context var1) {
      if (this.zzZv) {
         return false;
      } else {
         IntentFilter var2;
         (var2 = new IntentFilter()).addAction("android.intent.action.USER_PRESENT");
         var2.addAction("android.intent.action.SCREEN_OFF");
         var1.getApplicationContext().registerReceiver(new zzahd(this, (zzaha)null), var2);
         this.zzZv = true;
         return true;
      }
   }

   private static String zzhN() {
      StringBuffer var0;
      (var0 = new StringBuffer(256)).append("Mozilla/5.0 (Linux; U; Android");
      if (VERSION.RELEASE != null) {
         var0.append(" ").append(VERSION.RELEASE);
      }

      var0.append("; ").append(Locale.getDefault());
      if (Build.DEVICE != null) {
         var0.append("; ").append(Build.DEVICE);
         if (Build.DISPLAY != null) {
            var0.append(" Build/").append(Build.DISPLAY);
         }
      }

      var0.append(") AppleWebKit/533 Version/4.0 Safari/533");
      return var0.toString();
   }

   public final String zzs(Context var1, String var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzJP != null) {
            return this.zzJP;
         } else if (var2 == null) {
            return zzhN();
         } else {
            try {
               this.zzJP = com.google.android.gms.ads.internal.zzbs.zzbB().getDefaultUserAgent(var1);
            } catch (Exception var6) {
               ;
            }

            if (TextUtils.isEmpty(this.zzJP)) {
               zzji.zzds();
               if (!zzaiy.zzil()) {
                  this.zzJP = null;
                  zzZr.post(new zzahb(this, var1));

                  while(this.zzJP == null) {
                     try {
                        this.mLock.wait();
                     } catch (InterruptedException var7) {
                        this.zzJP = zzhN();
                        String var10001 = String.valueOf(this.zzJP);
                        String var10000;
                        if (var10001.length() != 0) {
                           var10000 = "Interrupted, use default user agent: ".concat(var10001);
                        } else {
                           String var10002 = new String;
                           var10000 = var10002;
                           var10002.<init>("Interrupted, use default user agent: ");
                        }

                        zzafr.zzaT(var10000);
                     }
                  }
               } else {
                  this.zzJP = zzF(var1);
               }
            }

            String var4 = String.valueOf(this.zzJP);
            this.zzJP = (new StringBuilder(11 + String.valueOf(var4).length() + String.valueOf(var2).length())).append(var4).append(" (Mobile; ").append(var2).append(")").toString();
            return this.zzJP;
         }
      }
   }

   protected static String zzF(Context var0) {
      try {
         return (new WebView(var0)).getSettings().getUserAgentString();
      } catch (Exception var1) {
         return zzhN();
      }
   }

   public static boolean zza(ClassLoader var0, Class var1, String var2) {
      try {
         Class var3 = Class.forName(var2, false, var0);
         return var1.isAssignableFrom(var3);
      } catch (Throwable var4) {
         return false;
      }
   }

   public static String zzaI(String var0) {
      return Uri.parse(var0).buildUpon().query((String)null).build().toString();
   }

   public final JSONObject zzj(Map var1) throws JSONException {
      try {
         JSONObject var2 = new JSONObject();
         Iterator var3 = var1.keySet().iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            this.zza(var2, var4, var1.get(var4));
         }

         return var2;
      } catch (ClassCastException var5) {
         JSONException var10000 = new JSONException;
         String var10003 = String.valueOf(var5.getMessage());
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Could not convert map to JSON: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Could not convert map to JSON: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   public final JSONObject zza(Bundle var1, JSONObject var2) {
      try {
         return this.zzg(var1);
      } catch (JSONException var4) {
         zzafr.zzb("Error converting Bundle to JSON", var4);
         return null;
      }
   }

   private final JSONObject zzg(Bundle var1) throws JSONException {
      JSONObject var2 = new JSONObject();
      Iterator var3 = var1.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         this.zza(var2, var4, var1.get(var4));
      }

      return var2;
   }

   private final JSONArray zza(Collection var1) throws JSONException {
      JSONArray var2 = new JSONArray();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         Object var4 = var3.next();
         this.zza(var2, var4);
      }

      return var2;
   }

   private final void zza(JSONArray var1, Object var2) throws JSONException {
      if (var2 instanceof Bundle) {
         var1.put(this.zzg((Bundle)var2));
      } else if (var2 instanceof Map) {
         Map var3 = (Map)var2;
         var1.put(this.zzj(var3));
      } else if (var2 instanceof Collection) {
         var1.put(this.zza((Collection)var2));
      } else if (!(var2 instanceof Object[])) {
         var1.put(var2);
      } else {
         Object[] var5 = (Object[])var2;
         zzagz var4 = this;
         JSONArray var6 = new JSONArray();
         Object[] var7 = var5;
         int var8 = var5.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            Object var10 = var7[var9];
            var4.zza(var6, var10);
         }

         var1.put(var6);
      }
   }

   private final void zza(JSONObject var1, String var2, Object var3) throws JSONException {
      if (var3 instanceof Bundle) {
         var1.put(var2, this.zzg((Bundle)var3));
      } else if (var3 instanceof Map) {
         Map var4 = (Map)var3;
         var1.put(var2, this.zzj(var4));
      } else if (var3 instanceof Collection) {
         var1.put(var2 != null ? var2 : "null", this.zza((Collection)var3));
      } else if (var3 instanceof Object[]) {
         var1.put(var2, this.zza((Collection)Arrays.asList((Object[])var3)));
      } else {
         var1.put(var2, var3);
      }
   }

   public static Map zzg(Uri var0) {
      if (var0 == null) {
         return null;
      } else {
         HashMap var1 = new HashMap();
         Iterator var2 = com.google.android.gms.ads.internal.zzbs.zzbB().zzh(var0).iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.put(var3, var0.getQueryParameter(var3));
         }

         return var1;
      }
   }

   public static String zzhO() {
      return UUID.randomUUID().toString();
   }

   public static String zzhP() {
      UUID var0;
      byte[] var1 = BigInteger.valueOf((var0 = UUID.randomUUID()).getLeastSignificantBits()).toByteArray();
      byte[] var2 = BigInteger.valueOf(var0.getMostSignificantBits()).toByteArray();
      String var3 = (new BigInteger(1, var1)).toString();

      for(int var4 = 0; var4 < 2; ++var4) {
         try {
            MessageDigest var5;
            (var5 = MessageDigest.getInstance("MD5")).update(var1);
            var5.update(var2);
            byte[] var6 = new byte[8];
            System.arraycopy(var5.digest(), 0, var6, 0, 8);
            var3 = (new BigInteger(1, var6)).toString();
         } catch (NoSuchAlgorithmException var7) {
            ;
         }
      }

      return var3;
   }

   public static int zzaJ(String var0) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var2) {
         String var1 = String.valueOf(var2);
         zzafr.zzaT((new StringBuilder(22 + String.valueOf(var1).length())).append("Could not parse value:").append(var1).toString());
         return 0;
      }
   }

   public static String zzhQ() {
      String var0 = Build.MANUFACTURER;
      String var1 = Build.MODEL;
      return Build.MODEL.startsWith(var0) ? var1 : (new StringBuilder(1 + String.valueOf(var0).length() + String.valueOf(var1).length())).append(var0).append(" ").append(var1).toString();
   }

   private static int[] zzhR() {
      return new int[]{0, 0};
   }

   public static int[] zzf(Activity var0) {
      Window var1;
      View var2;
      return (var1 = var0.getWindow()) != null && (var2 = var1.findViewById(16908290)) != null ? new int[]{var2.getWidth(), var2.getHeight()} : zzhR();
   }

   public final int[] zzg(Activity var1) {
      int[] var2 = zzf(var1);
      int[] var10000 = new int[2];
      zzji.zzds();
      var10000[0] = zzaiy.zzd(var1, var2[0]);
      zzji.zzds();
      var10000[1] = zzaiy.zzd(var1, var2[1]);
      return var10000;
   }

   public final int[] zzh(Activity var1) {
      Window var4;
      View var5;
      int[] var2 = (var4 = var1.getWindow()) != null && (var5 = var4.findViewById(16908290)) != null ? new int[]{var5.getTop(), var5.getBottom()} : zzhR();
      int[] var10000 = new int[2];
      zzji.zzds();
      var10000[0] = zzaiy.zzd(var1, var2[0]);
      zzji.zzds();
      var10000[1] = zzaiy.zzd(var1, var2[1]);
      return var10000;
   }

   public static boolean zzaK(String var0) {
      return TextUtils.isEmpty(var0) ? false : var0.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
   }

   public static void zza(Activity var0, OnGlobalLayoutListener var1) {
      Window var2;
      if ((var2 = var0.getWindow()) != null && var2.getDecorView() != null && var2.getDecorView().getViewTreeObserver() != null) {
         var2.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(var1);
      }

   }

   public static void zza(Activity var0, OnScrollChangedListener var1) {
      Window var2;
      if ((var2 = var0.getWindow()) != null && var2.getDecorView() != null && var2.getDecorView().getViewTreeObserver() != null) {
         var2.getDecorView().getViewTreeObserver().addOnScrollChangedListener(var1);
      }

   }

   public static void zzb(Activity var0, OnScrollChangedListener var1) {
      Window var2;
      if ((var2 = var0.getWindow()) != null && var2.getDecorView() != null && var2.getDecorView().getViewTreeObserver() != null) {
         var2.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(var1);
      }

   }

   public static DisplayMetrics zza(WindowManager var0) {
      DisplayMetrics var1 = new DisplayMetrics();
      var0.getDefaultDisplay().getMetrics(var1);
      return var1;
   }

   public static Builder zzG(Context var0) {
      return new Builder(var0);
   }

   public static zzlz zzH(Context var0) {
      return new zzlz(var0);
   }

   public static Bitmap zzl(View var0) {
      var0.setDrawingCacheEnabled(true);
      Bitmap var1 = Bitmap.createBitmap(var0.getDrawingCache());
      var0.setDrawingCacheEnabled(false);
      return var1;
   }

   public static PopupWindow zza(View var0, int var1, int var2, boolean var3) {
      return new PopupWindow(var0, var1, var2, false);
   }

   private static String zzI(Context var0) {
      try {
         ActivityManager var1;
         if ((var1 = (ActivityManager)var0.getSystemService("activity")) == null) {
            return null;
         }

         List var2;
         RunningTaskInfo var3;
         if ((var2 = var1.getRunningTasks(1)) != null && !var2.isEmpty() && (var3 = (RunningTaskInfo)var2.get(0)) != null && var3.topActivity != null) {
            return var3.topActivity.getClassName();
         }
      } catch (Exception var4) {
         ;
      }

      return null;
   }

   public static String zza(Context var0, View var1, zziv var2) {
      zzme var10 = zzmo.zzDr;
      if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).booleanValue()) {
         return null;
      } else {
         try {
            JSONObject var3 = new JSONObject();
            JSONObject var4;
            (var4 = new JSONObject()).put("width", var2.width);
            var4.put("height", var2.height);
            var3.put("size", var4);
            var3.put("activity", zzI(var0));
            if (!var2.zzAt) {
               JSONArray var5 = new JSONArray();
               View var6 = var1;

               while(true) {
                  while(var6 != null) {
                     ViewParent var7;
                     if ((var7 = var6.getParent()) != null) {
                        int var8 = -1;
                        if (var7 instanceof ViewGroup) {
                           var8 = ((ViewGroup)var7).indexOfChild(var6);
                        }

                        JSONObject var9;
                        (var9 = new JSONObject()).put("type", var7.getClass().getName());
                        var9.put("index_of_child", var8);
                        var5.put(var9);
                     }

                     if (var7 != null && var7 instanceof View) {
                        var6 = (View)var7;
                     } else {
                        var6 = null;
                     }
                  }

                  if (var5.length() > 0) {
                     var3.put("parents", var5);
                  }
                  break;
               }
            }

            return var3.toString();
         } catch (JSONException var11) {
            zzafr.zzc("Fail to get view hierarchy json", var11);
            return null;
         }
      }
   }

   public static String zzb(zzaka var0, String var1) {
      return zza(var0.getContext(), var0.zziy(), var1, var0.getView());
   }

   private static String zza(Context var0, zzcu var1, String var2, View var3) {
      if (var1 == null) {
         return var2;
      } else {
         try {
            Uri var4 = Uri.parse(var2);
            if (var1.zzd(var4)) {
               var4 = var1.zza(var4, var0, var3);
            }

            return var4.toString();
         } catch (Exception var5) {
            return var2;
         }
      }
   }

   public static boolean zzJ(Context param0) {
      // $FF: Couldn't be decompiled
   }

   public final void zza(Context var1, @Nullable String var2, String var3, Bundle var4, boolean var5) {
      if (var5) {
         com.google.android.gms.ads.internal.zzbs.zzbz();
         var4.putString("device", zzhQ());
         var4.putString("eids", TextUtils.join(",", zzmo.zzdJ()));
      }

      zzji.zzds();
      zzaiy.zza(var1, var2, var3, var4, var5, new zzahc(this, var1, var2));
   }

   public final void zzb(Context var1, String var2, String var3, Bundle var4, boolean var5) {
      zzme var6 = zzmo.zzEv;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
         this.zza(var1, var2, var3, var4, var5);
      }

   }

   public static void runOnUiThread(Runnable var0) {
      if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
         var0.run();
      } else {
         zzZr.post(var0);
      }
   }

   public static void zzb(Runnable var0) {
      if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
         var0.run();
      } else {
         zzagt.zza(var0);
      }
   }

   public static Bitmap zzm(View var0) {
      if (var0 == null) {
         return null;
      } else {
         Bitmap var1;
         if ((var1 = zzo(var0)) == null) {
            var1 = zzn(var0);
         }

         return var1;
      }
   }

   private static Bitmap zzn(@NonNull View var0) {
      try {
         int var1 = var0.getWidth();
         int var2 = var0.getHeight();
         if (var1 != 0 && var2 != 0) {
            Bitmap var3 = Bitmap.createBitmap(var0.getWidth(), var0.getHeight(), Config.RGB_565);
            Canvas var4 = new Canvas(var3);
            var0.layout(0, 0, var1, var2);
            var0.draw(var4);
            return var3;
         } else {
            zzafr.zzaT("Width or height of view is zero");
            return null;
         }
      } catch (RuntimeException var5) {
         zzafr.zzb("Fail to capture the webview", var5);
         return null;
      }
   }

   private static Bitmap zzo(@NonNull View var0) {
      Bitmap var1 = null;

      try {
         boolean var2 = var0.isDrawingCacheEnabled();
         var0.setDrawingCacheEnabled(true);
         Bitmap var3;
         if ((var3 = var0.getDrawingCache()) != null) {
            var1 = Bitmap.createBitmap(var3);
         }

         var0.setDrawingCacheEnabled(var2);
      } catch (RuntimeException var4) {
         zzafr.zzb("Fail to capture the web view", var4);
      }

      return var1;
   }

   public static Bitmap zzK(Context var0) {
      if (!(var0 instanceof Activity)) {
         return null;
      } else {
         Bitmap var1 = null;

         try {
            zzme var3 = zzmo.zzFi;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
               Window var2;
               if ((var2 = ((Activity)var0).getWindow()) != null) {
                  var1 = zzo(var2.getDecorView().getRootView());
               }
            } else {
               var1 = zzn(((Activity)var0).getWindow().getDecorView());
            }
         } catch (RuntimeException var4) {
            zzafr.zzb("Fail to capture screen shot", var4);
         }

         return var1;
      }
   }

   public static void zzb(Context var0, Intent var1) {
      try {
         var0.startActivity(var1);
      } catch (Throwable var2) {
         var1.addFlags(268435456);
         var0.startActivity(var1);
      }
   }

   public static float zzbf() {
      com.google.android.gms.ads.internal.zzbs.zzbT();
      zzax var0;
      return (var0 = zzax.zzbe()) != null && var0.zzbg() ? var0.zzbf() : 1.0F;
   }

   public static boolean zzbh() {
      com.google.android.gms.ads.internal.zzbs.zzbT();
      zzax var0;
      return (var0 = zzax.zzbe()) != null ? var0.zzbh() : false;
   }

   public static AudioManager zzL(Context var0) {
      return (AudioManager)var0.getSystemService("audio");
   }

   public static float zzM(Context var0) {
      AudioManager var1;
      if ((var1 = zzL(var0)) == null) {
         return 0.0F;
      } else {
         int var2 = var1.getStreamMaxVolume(3);
         int var3 = var1.getStreamVolume(3);
         return var2 == 0 ? 0.0F : (float)var3 / (float)var2;
      }
   }

   public final zzl zze(Context var1, zzaje var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLG == null) {
            Context var10003 = var1.getApplicationContext() != null ? var1.getApplicationContext() : var1;
            zzme var5 = zzmo.zzBX;
            this.zzLG = new zzl(var10003, var2, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5));
         }

         return this.zzLG;
      }
   }

   public static int zzN(Context var0) {
      ApplicationInfo var1;
      return (var1 = var0.getApplicationInfo()) == null ? 0 : var1.targetSdkVersion;
   }

   public final boolean zza(View var1, Context var2) {
      PowerManager var3 = null;
      Context var4;
      if ((var4 = var2.getApplicationContext()) != null) {
         var3 = (PowerManager)var4.getSystemService("power");
      }

      KeyguardManager var5 = null;
      Object var6;
      if ((var6 = var2.getSystemService("keyguard")) != null && var6 instanceof KeyguardManager) {
         var5 = (KeyguardManager)var6;
      }

      return this.zza(var1, var3, var5);
   }

   public final boolean zza(View var1, PowerManager var2, KeyguardManager var3) {
      zzme var5;
      boolean var13;
      label98: {
         if (!com.google.android.gms.ads.internal.zzbs.zzbz().zzZu && (var3 == null ? false : var3.inKeyguardRestrictedInputMode())) {
            label99: {
               var5 = zzmo.zzEs;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue()) {
                  label76: {
                     View var9;
                     Context var10;
                     Activity var10000 = (var9 = var1.getRootView()) != null && (var10 = var9.getContext()) instanceof Activity ? (Activity)var10 : null;
                     Activity var7 = var10000;
                     if (var10000 != null) {
                        Window var11;
                        LayoutParams var12 = (var11 = var7.getWindow()) == null ? null : var11.getAttributes();
                        LayoutParams var8 = var12;
                        if (var12 != null && (var8.flags & 524288) != 0) {
                           var13 = true;
                           break label76;
                        }
                     }

                     var13 = false;
                  }

                  if (var13) {
                     break label99;
                  }
               }

               var13 = false;
               break label98;
            }
         }

         var13 = true;
      }

      boolean var4 = var13;
      if (var1.getVisibility() == 0 && var1.isShown() && (var2 == null || var2.isScreenOn()) && var4) {
         var5 = zzmo.zzEq;
         if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue() || var1.getLocalVisibleRect(new Rect()) || var1.getGlobalVisibleRect(new Rect())) {
            return true;
         }
      }

      return false;
   }

   public static int zzp(@Nullable View var0) {
      if (var0 == null) {
         return -1;
      } else {
         ViewParent var1;
         for(var1 = var0.getParent(); var1 != null && !(var1 instanceof AdapterView); var1 = var1.getParent()) {
            ;
         }

         return var1 == null ? -1 : ((AdapterView)var1).getPositionForView(var0);
      }
   }

   public static boolean zzO(Context var0) {
      try {
         var0.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
         return false;
      } catch (ClassNotFoundException var2) {
         return true;
      } catch (Throwable var3) {
         zzafr.zzb("Error loading class.", var3);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza(var3, "AdUtil.isLiteSdk");
         return false;
      }
   }

   public static Bundle zzhS() {
      Bundle var0 = new Bundle();

      try {
         zzme var2 = zzmo.zzCC;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue()) {
            MemoryInfo var1;
            Debug.getMemoryInfo(var1 = new MemoryInfo());
            var0.putParcelable("debug_memory_info", var1);
         }

         var2 = zzmo.zzCD;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue()) {
            Runtime var4 = Runtime.getRuntime();
            var0.putLong("runtime_free_memory", var4.freeMemory());
            var0.putLong("runtime_max_memory", var4.maxMemory());
            var0.putLong("runtime_total_memory", var4.totalMemory());
         }

         var0.putInt("web_view_count", com.google.android.gms.ads.internal.zzbs.zzbD().zzhJ());
      } catch (Exception var3) {
         zzafr.zzc("Unable to gather memory stats", var3);
      }

      return var0;
   }

   public static Uri zzb(String var0, String var1, String var2) throws UnsupportedOperationException {
      int var3;
      if ((var3 = var0.indexOf("&adurl")) == -1) {
         var3 = var0.indexOf("?adurl");
      }

      return var3 != -1 ? Uri.parse(var0.substring(0, var3 + 1) + var1 + "=" + var2 + "&" + var0.substring(var3 + 1)) : Uri.parse(var0).buildUpon().appendQueryParameter(var1, var2).build();
   }

   @TargetApi(18)
   public static void zza(Context var0, Uri var1) {
      try {
         Intent var2 = new Intent("android.intent.action.VIEW", var1);
         Bundle var3 = new Bundle();
         var2.putExtras(var3);
         zzme var5 = zzmo.zzGo;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue()) {
            zzc(var0, var2);
         }

         var3.putString("com.android.browser.application_id", var0.getPackageName());
         var0.startActivity(var2);
         String var4 = String.valueOf(var1.toString());
         zzafr.zzaC((new StringBuilder(26 + String.valueOf(var4).length())).append("Opening ").append(var4).append(" in a new browser.").toString());
      } catch (ActivityNotFoundException var6) {
         zzafr.zzb("No browser is found.", var6);
      }
   }

   @TargetApi(18)
   public static void zzc(Context var0, Intent var1) {
      if (var1 != null) {
         if (zzq.zzsb()) {
            Bundle var2;
            (var2 = var1.getExtras() != null ? var1.getExtras() : new Bundle()).putBinder("android.support.customtabs.extra.SESSION", (IBinder)null);
            var2.putString("com.android.browser.application_id", var0.getPackageName());
            var1.putExtras(var2);
         }

      }
   }

   public static void zze(Context var0, String var1, String var2) {
      try {
         FileOutputStream var3;
         (var3 = var0.openFileOutput(var1, 0)).write(var2.getBytes("UTF-8"));
         var3.close();
      } catch (Exception var4) {
         zzafr.zzb("Error writing to file in internal storage.", var4);
      }
   }

   public static String zzt(Context var0, String var1) {
      try {
         FileInputStream var2 = var0.openFileInput(var1);
         return new String(zzn.zza(var2, true), "UTF-8");
      } catch (IOException var3) {
         zzafr.zzaC("Error reading from internal storage.");
         return "";
      }
   }

   @TargetApi(24)
   public static boolean zza(Activity var0, Configuration var1) {
      zzji.zzds();
      int var2 = zzaiy.zzc(var0, var1.screenHeightDp);
      int var3 = zzaiy.zzc(var0, var1.screenWidthDp);
      DisplayMetrics var4;
      int var5 = (var4 = zza((WindowManager)var0.getApplicationContext().getSystemService("window"))).heightPixels;
      int var6 = var4.widthPixels;
      int var7 = 0;
      int var8;
      if ((var8 = var0.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
         var7 = var0.getResources().getDimensionPixelSize(var8);
      }

      int var10000 = (int)Math.round((double)var0.getResources().getDisplayMetrics().density + 0.5D);
      zzme var10 = zzmo.zzGy;
      int var9 = var10000 * ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).intValue();
      return zzb(var5, var2 + var7, var9) && zzb(var6, var3, var9);
   }

   public static boolean zzaL(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return false;
      } else {
         if (zzZs.get() == null) {
            try {
               zzme var4 = zzmo.zzDw;
               JSONArray var1 = new JSONArray((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4));
               ArrayList var2 = new ArrayList(var1.length());

               for(int var3 = 0; var3 < var1.length(); ++var3) {
                  var2.add(var1.getString(var3));
               }

               zzZs.compareAndSet((Object)null, var2);
            } catch (JSONException var5) {
               zzafr.zzaT("Could not parse click ping schema");
               return false;
            }
         }

         Iterator var6 = ((List)zzZs.get()).iterator();

         String var7;
         do {
            if (!var6.hasNext()) {
               return false;
            }

            var7 = (String)var6.next();
         } while(!var0.contains(var7));

         return true;
      }
   }

   public static boolean zzaM(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return false;
      } else {
         if (zzZt.get() == null) {
            try {
               zzme var4 = zzmo.zzDx;
               JSONArray var1 = new JSONArray((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4));
               ArrayList var2 = new ArrayList(var1.length());

               for(int var3 = 0; var3 < var1.length(); ++var3) {
                  var2.add(var1.getString(var3));
               }

               zzZt.compareAndSet((Object)null, var2);
            } catch (JSONException var5) {
               zzafr.zzaT("Could not parse impression ping schema");
               return false;
            }
         }

         Iterator var6 = ((List)zzZt.get()).iterator();

         String var7;
         do {
            if (!var6.hasNext()) {
               return false;
            }

            var7 = (String)var6.next();
         } while(!var0.contains(var7));

         return true;
      }
   }

   private static boolean zzb(int var0, int var1, int var2) {
      return Math.abs(var0 - var1) <= var2;
   }

   public static Bundle zza(zzgz var0) {
      if (var0 == null) {
         return null;
      } else {
         zzme var6 = zzmo.zzCZ;
         if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
            var6 = zzmo.zzDb;
            if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
               return null;
            }
         }

         if (com.google.android.gms.ads.internal.zzbs.zzbD().zzhn() && com.google.android.gms.ads.internal.zzbs.zzbD().zzho()) {
            return null;
         } else {
            if (var0.zzcQ()) {
               var0.wakeup();
            }

            zzgt var1 = var0.zzcO();
            String var3 = null;
            String var2;
            String var4;
            if (var1 != null) {
               var2 = var1.zzcD();
               var3 = var1.zzcE();
               var4 = var1.zzcF();
               if (var2 != null) {
                  com.google.android.gms.ads.internal.zzbs.zzbD().zzaF(var2);
               }

               if (var4 != null) {
                  com.google.android.gms.ads.internal.zzbs.zzbD().zzaG(var4);
               }
            } else {
               var2 = com.google.android.gms.ads.internal.zzbs.zzbD().zzhu();
               var4 = com.google.android.gms.ads.internal.zzbs.zzbD().zzhv();
            }

            Bundle var5 = new Bundle(1);
            if (var4 != null) {
               var6 = zzmo.zzDb;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue() && !com.google.android.gms.ads.internal.zzbs.zzbD().zzho()) {
                  var5.putString("v_fp_vertical", var4);
               }
            }

            if (var2 != null) {
               var6 = zzmo.zzCZ;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue() && !com.google.android.gms.ads.internal.zzbs.zzbD().zzhn()) {
                  var5.putString("fingerprint", var2);
                  if (!var2.equals(var3)) {
                     var5.putString("v_fp", var3);
                  }
               }
            }

            return !var5.isEmpty() ? var5 : null;
         }
      }
   }

   public static int zzP(Context var0) {
      return DynamiteModule.zzF(var0, "com.google.android.gms.ads.dynamite");
   }

   public static int zzQ(Context var0) {
      return DynamiteModule.zzE(var0, "com.google.android.gms.ads.dynamite");
   }

   // $FF: synthetic method
   static boolean zza(zzagz var0, boolean var1) {
      return var0.zzZu = var1;
   }

   // $FF: synthetic method
   static Object zza(zzagz var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static String zza(zzagz var0, String var1) {
      return var0.zzJP = var1;
   }
}
