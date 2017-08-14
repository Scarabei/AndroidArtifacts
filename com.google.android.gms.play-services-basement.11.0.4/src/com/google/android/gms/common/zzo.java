package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.zzbd;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.internal.zzbha;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzo {
   /** @deprecated */
   @Deprecated
   public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 11020000;
   /** @deprecated */
   @Deprecated
   public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
   public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
   private static boolean zzaAo = false;
   private static boolean zzaAp = false;
   private static boolean zzaAq = false;
   private static boolean zzaAr = false;
   static final AtomicBoolean zzaAs = new AtomicBoolean();
   private static final AtomicBoolean zzaAt = new AtomicBoolean();

   /** @deprecated */
   @Deprecated
   public static String getErrorString(int var0) {
      return ConnectionResult.getStatusString(var0);
   }

   /** @deprecated */
   @Deprecated
   public static int isGooglePlayServicesAvailable(Context var0) {
      PackageManager var1 = var0.getPackageManager();

      try {
         var0.getResources().getString(string.common_google_play_services_unknown_issue);
      } catch (Throwable var15) {
         Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
      }

      if (!"com.google.android.gms".equals(var0.getPackageName()) && !zzaAt.get()) {
         int var9;
         if ((var9 = zzbd.zzaE(var0)) == 0) {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
         }

         if (var9 != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
            int var10 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
            String var11 = String.valueOf("com.google.android.gms.version");
            throw new IllegalStateException((new StringBuilder(290 + String.valueOf(var11).length())).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(var10).append(" but found ").append(var9).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(var11).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
         }
      }

      boolean var2 = !com.google.android.gms.common.util.zzj.zzaH(var0) && !com.google.android.gms.common.util.zzj.zzaJ(var0);
      PackageInfo var3 = null;
      if (var2) {
         try {
            var3 = var1.getPackageInfo("com.android.vending", 8256);
         } catch (NameNotFoundException var14) {
            Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
            return 9;
         }
      }

      PackageInfo var4;
      try {
         var4 = var1.getPackageInfo("com.google.android.gms", 64);
      } catch (NameNotFoundException var13) {
         Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
         return 1;
      }

      zzp.zzax(var0);
      if (var2) {
         zzg var5;
         if ((var5 = zzp.zza(var3, zzj.zzaAk)) == null) {
            Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
            return 9;
         }

         if (zzp.zza(var4, var5) == null) {
            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
            return 9;
         }
      } else if (zzp.zza(var4, zzj.zzaAk) == null) {
         Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
         return 9;
      }

      int var16 = GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000;
      if (var4.versionCode / 1000 < var16) {
         int var17 = GOOGLE_PLAY_SERVICES_VERSION_CODE;
         int var7 = var4.versionCode;
         Log.w("GooglePlayServicesUtil", (new StringBuilder(77)).append("Google Play services out of date.  Requires ").append(var17).append(" but found ").append(var7).toString());
         return 2;
      } else {
         ApplicationInfo var6 = var4.applicationInfo;
         if (var4.applicationInfo == null) {
            try {
               var6 = var1.getApplicationInfo("com.google.android.gms", 0);
            } catch (NameNotFoundException var12) {
               Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", var12);
               return 1;
            }
         }

         return !var6.enabled ? 3 : 0;
      }
   }

   /** @deprecated */
   @Deprecated
   public static void zzah(Context var0) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      int var1;
      if ((var1 = zze.zzoW().isGooglePlayServicesAvailable(var0)) != 0) {
         zze.zzoW();
         Intent var2 = zze.zza(var0, var1, "e");
         Log.e("GooglePlayServicesUtil", (new StringBuilder(57)).append("GooglePlayServices not available due to error ").append(var1).toString());
         if (var2 == null) {
            throw new GooglePlayServicesNotAvailableException(var1);
         } else {
            throw new GooglePlayServicesRepairableException(var1, "Google Play Services not available", var2);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean zzf(Context var0, int var1) {
      return zzw.zzf(var0, var1);
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(19)
   public static boolean zzb(Context var0, int var1, String var2) {
      return zzw.zzb(var0, var1, var2);
   }

   public static boolean zzaw(Context var0) {
      if (!zzaAr) {
         Context var2 = var0;

         try {
            PackageInfo var4;
            if ((var4 = zzbha.zzaP(var2).getPackageInfo("com.google.android.gms", 64)) != null) {
               zzp.zzax(var2);
               if (zzp.zza(var4, zzj.zzaAk[1]) != null) {
                  zzaAq = true;
                  return zzaAq || !"user".equals(Build.TYPE);
               }
            }

            zzaAq = false;
         } catch (NameNotFoundException var8) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", var8);
         } finally {
            zzaAr = true;
         }
      }

      return zzaAq || !"user".equals(Build.TYPE);
   }

   /** @deprecated */
   @Deprecated
   public static PendingIntent getErrorPendingIntent(int var0, Context var1, int var2) {
      return zze.zzoW().getErrorResolutionPendingIntent(var1, var0, var2);
   }

   /** @deprecated */
   @Deprecated
   public static void zzat(Context var0) {
      if (!zzaAs.getAndSet(true)) {
         try {
            NotificationManager var1;
            if ((var1 = (NotificationManager)var0.getSystemService("notification")) != null) {
               var1.cancel(10436);
            }

         } catch (SecurityException var2) {
            ;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean isUserRecoverableError(int var0) {
      switch(var0) {
      case 1:
      case 2:
      case 3:
      case 9:
         return true;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      default:
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static String getOpenSourceSoftwareLicenseInfo(Context var0) {
      Uri var1 = (new Builder()).scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();

      try {
         InputStream var2 = var0.getContentResolver().openInputStream(var1);

         try {
            String var3 = (new Scanner(var2)).useDelimiter("\\A").next();
            return var3;
         } catch (NoSuchElementException var8) {
            ;
         } finally {
            if (var2 != null) {
               var2.close();
            }

         }

         return null;
      } catch (Exception var10) {
         return null;
      }
   }

   public static Resources getRemoteResource(Context var0) {
      try {
         return var0.getPackageManager().getResourcesForApplication("com.google.android.gms");
      } catch (NameNotFoundException var1) {
         return null;
      }
   }

   public static Context getRemoteContext(Context var0) {
      try {
         return var0.createPackageContext("com.google.android.gms", 3);
      } catch (NameNotFoundException var1) {
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static int zzau(Context var0) {
      PackageInfo var1;
      try {
         var1 = var0.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      } catch (NameNotFoundException var2) {
         Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
         return 0;
      }

      return var1.versionCode;
   }

   /** @deprecated */
   @Deprecated
   public static boolean zze(Context var0, int var1) {
      if (var1 == 18) {
         return true;
      } else {
         return var1 == 1 ? zzy(var0, "com.google.android.gms") : false;
      }
   }

   @TargetApi(21)
   static boolean zzy(Context var0, String var1) {
      boolean var2 = var1.equals("com.google.android.gms");
      if (zzq.zzse()) {
         Iterator var4 = var0.getPackageManager().getPackageInstaller().getAllSessions().iterator();

         while(var4.hasNext()) {
            SessionInfo var5 = (SessionInfo)var4.next();
            if (var1.equals(var5.getAppPackageName())) {
               return true;
            }
         }
      }

      PackageManager var3 = var0.getPackageManager();

      try {
         ApplicationInfo var9 = var3.getApplicationInfo(var1, 8192);
         if (var2) {
            return var9.enabled;
         } else {
            Bundle var7;
            return var9.enabled && (!zzq.zzsb() || (var7 = ((UserManager)var0.getSystemService("user")).getApplicationRestrictions(var0.getPackageName())) == null || !"true".equals(var7.getString("restricted_profile")));
         }
      } catch (NameNotFoundException var8) {
         return false;
      }
   }
}
