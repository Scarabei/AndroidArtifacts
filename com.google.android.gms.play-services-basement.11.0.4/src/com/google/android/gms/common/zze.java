package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.internal.zzbha;

public class zze {
   public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
   public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
   private static final zze zzaAc;

   public static zze zzoW() {
      return zzaAc;
   }

   public int isGooglePlayServicesAvailable(Context var1) {
      int var2 = zzo.isGooglePlayServicesAvailable(var1);
      if (zzo.zze(var1, var2)) {
         var2 = 18;
      }

      return var2;
   }

   public static void zzas(Context var0) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      zzo.zzah(var0);
   }

   public boolean isUserResolvableError(int var1) {
      return zzo.isUserRecoverableError(var1);
   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public final Intent zzak(int var1) {
      return zza((Context)null, var1, (String)null);
   }

   @Nullable
   public static Intent zza(Context var0, int var1, @Nullable String var2) {
      switch(var1) {
      case 1:
      case 2:
         if (var0 != null && com.google.android.gms.common.util.zzj.zzaH(var0)) {
            return zzai.zzrD();
         }

         return zzai.zzw("com.google.android.gms", zzx(var0, var2));
      case 3:
         return zzai.zzcD("com.google.android.gms");
      default:
         return null;
      }
   }

   @Nullable
   public PendingIntent getErrorResolutionPendingIntent(Context var1, int var2, int var3) {
      return this.zza(var1, var2, var3, (String)null);
   }

   @Nullable
   public final PendingIntent zza(Context var1, int var2, int var3, @Nullable String var4) {
      Intent var5;
      return (var5 = zza(var1, var2, var4)) == null ? null : PendingIntent.getActivity(var1, var3, var5, 268435456);
   }

   public static void zzat(Context var0) {
      zzo.zzat(var0);
   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public String getOpenSourceSoftwareLicenseInfo(Context var1) {
      return zzo.getOpenSourceSoftwareLicenseInfo(var1);
   }

   public static int zzau(Context var0) {
      return zzo.zzau(var0);
   }

   public static boolean zze(Context var0, int var1) {
      return zzo.zze(var0, var1);
   }

   public String getErrorString(int var1) {
      return zzo.getErrorString(var1);
   }

   private static String zzx(@Nullable Context var0, @Nullable String var1) {
      StringBuilder var2;
      (var2 = new StringBuilder()).append("gcore_");
      var2.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
      var2.append("-");
      if (!TextUtils.isEmpty(var1)) {
         var2.append(var1);
      }

      var2.append("-");
      if (var0 != null) {
         var2.append(var0.getPackageName());
      }

      var2.append("-");
      if (var0 != null) {
         try {
            PackageInfo var3 = zzbha.zzaP(var0).getPackageInfo(var0.getPackageName(), 0);
            var2.append(var3.versionCode);
         } catch (NameNotFoundException var4) {
            ;
         }
      }

      return var2.toString();
   }

   static {
      GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
      zzaAc = new zze();
   }
}
