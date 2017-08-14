package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.internal.zzt;

public final class GooglePlayServicesUtil extends zzo {
   public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
   /** @deprecated */
   @Deprecated
   public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
   /** @deprecated */
   @Deprecated
   public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
   public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

   /** @deprecated */
   @Deprecated
   public static Dialog getErrorDialog(int var0, Activity var1, int var2) {
      return getErrorDialog(var0, var1, var2, (OnCancelListener)null);
   }

   /** @deprecated */
   @Deprecated
   public static Dialog getErrorDialog(int var0, Activity var1, int var2, OnCancelListener var3) {
      if (zzo.zze(var1, var0)) {
         var0 = 18;
      }

      return GoogleApiAvailability.getInstance().getErrorDialog(var1, var0, var2, var3);
   }

   /** @deprecated */
   @Deprecated
   public static boolean showErrorDialogFragment(int var0, Activity var1, int var2, OnCancelListener var3) {
      return showErrorDialogFragment(var0, var1, (Fragment)null, var2, var3);
   }

   public static boolean showErrorDialogFragment(int var0, Activity var1, Fragment var2, int var3, OnCancelListener var4) {
      if (zzo.zze(var1, var0)) {
         var0 = 18;
      }

      GoogleApiAvailability var5 = GoogleApiAvailability.getInstance();
      if (var2 == null) {
         return var5.showErrorDialogFragment(var1, var0, var3, var4);
      } else {
         GoogleApiAvailability.getInstance();
         Intent var6 = zze.zza(var1, var0, "d");
         Dialog var7;
         if ((var7 = GoogleApiAvailability.zza(var1, var0, (zzt)zzt.zza(var2, var6, var3), (OnCancelListener)var4)) == null) {
            return false;
         } else {
            GoogleApiAvailability.zza(var1, var7, "GooglePlayServicesErrorDialog", var4);
            return true;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static void showErrorNotification(int var0, Context var1) {
      GoogleApiAvailability var2 = GoogleApiAvailability.getInstance();
      if (!zzo.zze(var1, var0) && !(var0 == 9 ? zzo.zzy(var1, "com.android.vending") : false)) {
         var2.showErrorNotification(var1, var0);
      } else {
         var2.zzar(var1);
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean showErrorDialogFragment(int var0, Activity var1, int var2) {
      return showErrorDialogFragment(var0, var1, var2, (OnCancelListener)null);
   }

   /** @deprecated */
   @Deprecated
   public static String getErrorString(int var0) {
      return zzo.getErrorString(var0);
   }

   /** @deprecated */
   @Deprecated
   public static int isGooglePlayServicesAvailable(Context var0) {
      return zzo.isGooglePlayServicesAvailable(var0);
   }

   /** @deprecated */
   @Deprecated
   public static PendingIntent getErrorPendingIntent(int var0, Context var1, int var2) {
      return zzo.getErrorPendingIntent(var0, var1, var2);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isUserRecoverableError(int var0) {
      return zzo.isUserRecoverableError(var0);
   }

   /** @deprecated */
   @Deprecated
   public static String getOpenSourceSoftwareLicenseInfo(Context var0) {
      return zzo.getOpenSourceSoftwareLicenseInfo(var0);
   }

   public static Resources getRemoteResource(Context var0) {
      return zzo.getRemoteResource(var0);
   }

   public static Context getRemoteContext(Context var0) {
      return zzo.getRemoteContext(var0);
   }

   static {
      GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
   }
}
