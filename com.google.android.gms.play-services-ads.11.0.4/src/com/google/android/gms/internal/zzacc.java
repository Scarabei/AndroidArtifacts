package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.common.util.zzj;
import java.util.Locale;

public final class zzacc {
   private int zzVF;
   private boolean zzVM;
   private boolean zzVN;
   private int zzVR;
   private int zzVV;
   private int zzVW;
   private String zzVI;
   private int zzVS;
   private int zzVT;
   private int zzVU;
   private boolean zzVZ;
   private int zzWa;
   private double zzVX;
   private boolean zzVY;
   private String zzWb;
   private String zzWc;
   private boolean zzVG;
   private boolean zzVH;
   private String zzVJ;
   private boolean zzVK;
   private boolean zzVL;
   private String zzVO;
   private String zzVP;
   private float zzxR;
   private int zzSI;
   private int zzSJ;
   private String zzVQ;
   private boolean zzWd;

   public zzacc(Context var1) {
      PackageManager var2 = var1.getPackageManager();
      this.zzk(var1);
      this.zzl(var1);
      this.zzm(var1);
      Locale var3 = Locale.getDefault();
      this.zzVG = zza(var2, "geo:0,0?q=donuts") != null;
      this.zzVH = zza(var2, "http://www.google.com") != null;
      this.zzVJ = var3.getCountry();
      zzji.zzds();
      this.zzVK = zzaiy.zzik();
      this.zzVL = zzj.zzaI(var1);
      this.zzVO = var3.getLanguage();
      this.zzVP = zzb(var1, var2);
      this.zzVQ = zza(var1, var2);
      Resources var4;
      if ((var4 = var1.getResources()) != null) {
         DisplayMetrics var5;
         if ((var5 = var4.getDisplayMetrics()) != null) {
            this.zzxR = var5.density;
            this.zzSI = var5.widthPixels;
            this.zzSJ = var5.heightPixels;
         }
      }
   }

   public zzacc(Context var1, zzacb var2) {
      var1.getPackageManager();
      this.zzk(var1);
      this.zzl(var1);
      this.zzm(var1);
      this.zzWb = Build.FINGERPRINT;
      this.zzWc = Build.DEVICE;
      this.zzWd = zznl.zzi(var1);
      this.zzVG = var2.zzVG;
      this.zzVH = var2.zzVH;
      this.zzVJ = var2.zzVJ;
      this.zzVK = var2.zzVK;
      this.zzVL = var2.zzVL;
      this.zzVO = var2.zzVO;
      this.zzVP = var2.zzVP;
      this.zzVQ = var2.zzVQ;
      this.zzxR = var2.zzxR;
      this.zzSI = var2.zzSI;
      this.zzSJ = var2.zzSJ;
   }

   private final void zzk(Context var1) {
      com.google.android.gms.ads.internal.zzbs.zzbz();
      AudioManager var2;
      if ((var2 = zzagz.zzL(var1)) != null) {
         try {
            this.zzVF = var2.getMode();
            this.zzVM = var2.isMusicActive();
            this.zzVN = var2.isSpeakerphoneOn();
            this.zzVR = var2.getStreamVolume(3);
            this.zzVV = var2.getRingerMode();
            this.zzVW = var2.getStreamVolume(2);
            return;
         } catch (Throwable var4) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zza(var4, "DeviceInfo.gatherAudioInfo");
         }
      }

      this.zzVF = -2;
      this.zzVM = false;
      this.zzVN = false;
      this.zzVR = 0;
      this.zzVV = 0;
      this.zzVW = 0;
   }

   @TargetApi(16)
   private final void zzl(Context var1) {
      TelephonyManager var2 = (TelephonyManager)var1.getSystemService("phone");
      ConnectivityManager var3 = (ConnectivityManager)var1.getSystemService("connectivity");
      this.zzVI = var2.getNetworkOperator();
      this.zzVT = var2.getNetworkType();
      this.zzVU = var2.getPhoneType();
      this.zzVS = -2;
      this.zzVZ = false;
      this.zzWa = -1;
      com.google.android.gms.ads.internal.zzbs.zzbz();
      if (zzagz.zzc(var1, var1.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
         NetworkInfo var4;
         if ((var4 = var3.getActiveNetworkInfo()) != null) {
            this.zzVS = var4.getType();
            this.zzWa = var4.getDetailedState().ordinal();
         } else {
            this.zzVS = -1;
         }

         if (VERSION.SDK_INT >= 16) {
            this.zzVZ = var3.isActiveNetworkMetered();
         }
      }

   }

   private final void zzm(Context var1) {
      IntentFilter var2 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
      Intent var3;
      if ((var3 = var1.registerReceiver((BroadcastReceiver)null, var2)) == null) {
         this.zzVX = -1.0D;
         this.zzVY = false;
      } else {
         int var4 = var3.getIntExtra("status", -1);
         int var5 = var3.getIntExtra("level", -1);
         int var6 = var3.getIntExtra("scale", -1);
         this.zzVX = (double)((float)var5 / (float)var6);
         this.zzVY = var4 == 2 || var4 == 5;
      }
   }

   private static String zza(Context var0, PackageManager var1) {
      try {
         PackageInfo var2;
         if ((var2 = zzbha.zzaP(var0).getPackageInfo("com.android.vending", 128)) != null) {
            int var3 = var2.versionCode;
            String var4 = String.valueOf(var2.packageName);
            return (new StringBuilder(12 + String.valueOf(var4).length())).append(var3).append(".").append(var4).toString();
         } else {
            return null;
         }
      } catch (Exception var5) {
         return null;
      }
   }

   private static String zzb(Context var0, PackageManager var1) {
      ResolveInfo var2;
      if ((var2 = zza(var1, "market://details?id=com.google.android.gms.ads")) == null) {
         return null;
      } else {
         ActivityInfo var3 = var2.activityInfo;
         if (var2.activityInfo == null) {
            return null;
         } else {
            try {
               PackageInfo var4;
               if ((var4 = zzbha.zzaP(var0).getPackageInfo(var3.packageName, 0)) != null) {
                  int var5 = var4.versionCode;
                  String var6 = String.valueOf(var3.packageName);
                  return (new StringBuilder(12 + String.valueOf(var6).length())).append(var5).append(".").append(var6).toString();
               } else {
                  return null;
               }
            } catch (NameNotFoundException var7) {
               return null;
            }
         }
      }
   }

   private static ResolveInfo zza(PackageManager var0, String var1) {
      Intent var2 = new Intent("android.intent.action.VIEW", Uri.parse(var1));
      return var0.resolveActivity(var2, 65536);
   }

   public final zzacb zzgM() {
      return new zzacb(this.zzVF, this.zzVG, this.zzVH, this.zzVI, this.zzVJ, this.zzVK, this.zzVL, this.zzVM, this.zzVN, this.zzVO, this.zzVP, this.zzVQ, this.zzVR, this.zzVS, this.zzVT, this.zzVU, this.zzVV, this.zzVW, this.zzxR, this.zzSI, this.zzSJ, this.zzVX, this.zzVY, this.zzVZ, this.zzWa, this.zzWb, this.zzWd, this.zzWc);
   }
}
