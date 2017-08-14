package com.google.android.gms.auth;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzbo;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class GoogleAuthUtil extends zzd {
   public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
   public static final String WORK_ACCOUNT_TYPE = "com.google.work";
   public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
   private static String KEY_CALLER_UID;
   private static String KEY_ANDROID_PACKAGE_NAME;
   public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
   public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
   public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
   public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;

   /** @deprecated */
   @Deprecated
   public static String getTokenWithNotification(Context var0, String var1, String var2, Bundle var3) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      Account var4 = new Account(var1, "com.google");
      return getTokenWithNotification(var0, var4, var2, var3);
   }

   /** @deprecated */
   @Deprecated
   public static String getTokenWithNotification(Context var0, String var1, String var2, Bundle var3, Intent var4) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      Account var5 = new Account(var1, "com.google");
      return getTokenWithNotification(var0, var5, var2, var3, var4);
   }

   /** @deprecated */
   @Deprecated
   public static String getTokenWithNotification(Context var0, String var1, String var2, Bundle var3, String var4, Bundle var5) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      Account var6 = new Account(var1, "com.google");
      return getTokenWithNotification(var0, var6, var2, var3, var4, var5);
   }

   public static String getTokenWithNotification(Context var0, Account var1, String var2, Bundle var3) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      Bundle var7 = var3;
      if (var3 == null) {
         var7 = new Bundle();
      }

      var7.putBoolean("handle_notification", true);
      return zza(var0, var1, var2, var7).getToken();
   }

   public static String getTokenWithNotification(Context var0, Account var1, String var2, Bundle var3, Intent var4) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      if (var4 == null) {
         throw new IllegalArgumentException("Callback cannot be null.");
      } else {
         String var6 = var4.toUri(1);

         try {
            Intent.parseUri(var6, 1);
         } catch (URISyntaxException var7) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
         }

         Bundle var10000 = var3 == null ? new Bundle() : var3;
         var3 = var10000;
         var10000.putParcelable("callback_intent", var4);
         var3.putBoolean("handle_notification", true);
         return zza(var0, var1, var2, var3).getToken();
      }
   }

   public static String getTokenWithNotification(Context var0, Account var1, String var2, Bundle var3, String var4, Bundle var5) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
      zzbo.zzh(var4, "Authority cannot be empty or null.");
      var3 = var3 == null ? new Bundle() : var3;
      Bundle var10000 = var5 == null ? new Bundle() : var5;
      var5 = var10000;
      ContentResolver.validateSyncExtrasBundle(var10000);
      var3.putString("authority", var4);
      var3.putBundle("sync_extras", var5);
      var3.putBoolean("handle_notification", true);
      return zza(var0, var1, var2, var3).getToken();
   }

   private static TokenData zza(Context var0, Account var1, String var2, Bundle var3) throws IOException, GoogleAuthException {
      if (var3 == null) {
         var3 = new Bundle();
      }

      try {
         TokenData var4 = zzd.zzb(var0, var1, var2, var3);
         GooglePlayServicesUtil.zzat(var0);
         return var4;
      } catch (GooglePlayServicesAvailabilityException var8) {
         GooglePlayServicesUtil.showErrorNotification(var8.getConnectionStatusCode(), var0);
         Log.w("GoogleAuthUtil", "Error when getting token", var8);
         throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
      } catch (UserRecoverableAuthException var9) {
         GooglePlayServicesUtil.zzat(var0);
         Log.w("GoogleAuthUtil", "Error when getting token", var9);
         throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
      }
   }

   /** @deprecated */
   @Deprecated
   public static String getToken(Context var0, String var1, String var2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      return zzd.getToken(var0, var1, var2);
   }

   /** @deprecated */
   @Deprecated
   public static String getToken(Context var0, String var1, String var2, Bundle var3) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      return zzd.getToken(var0, var1, var2, var3);
   }

   public static String getToken(Context var0, Account var1, String var2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      return zzd.getToken(var0, var1, var2);
   }

   public static String getToken(Context var0, Account var1, String var2, Bundle var3) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      return zzd.getToken(var0, var1, var2, var3);
   }

   /** @deprecated */
   @Deprecated
   @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
   public static void invalidateToken(Context var0, String var1) {
      zzd.invalidateToken(var0, var1);
   }

   public static void clearToken(Context var0, String var1) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
      zzd.clearToken(var0, var1);
   }

   public static List getAccountChangeEvents(Context var0, int var1, String var2) throws GoogleAuthException, IOException {
      return zzd.getAccountChangeEvents(var0, var1, var2);
   }

   public static String getAccountId(Context var0, String var1) throws GoogleAuthException, IOException {
      return zzd.getAccountId(var0, var1);
   }

   @TargetApi(23)
   public static Bundle removeAccount(Context var0, Account var1) throws GoogleAuthException, IOException {
      return zzd.removeAccount(var0, var1);
   }

   static {
      KEY_CALLER_UID = zzd.KEY_CALLER_UID;
      KEY_ANDROID_PACKAGE_NAME = zzd.KEY_ANDROID_PACKAGE_NAME;
   }
}
