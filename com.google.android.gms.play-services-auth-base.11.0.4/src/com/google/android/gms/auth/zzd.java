package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbgb;
import java.io.IOException;
import java.util.List;

public class zzd {
   public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
   public static final String WORK_ACCOUNT_TYPE = "com.google.work";
   private static final String[] zzakl = new String[]{"com.google", "com.google.work", "cn.google"};
   public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
   @SuppressLint({"InlinedApi"})
   public static final String KEY_CALLER_UID = "callerUid";
   @SuppressLint({"InlinedApi"})
   public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
   public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
   public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
   public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
   public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
   private static final ComponentName zzakm = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
   private static final zzbgb zzakn;

   /** @deprecated */
   @Deprecated
   public static String getToken(Context var0, String var1, String var2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      Account var3 = new Account(var1, "com.google");
      return getToken(var0, var3, var2);
   }

   /** @deprecated */
   @Deprecated
   public static String getToken(Context var0, String var1, String var2, Bundle var3) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      Account var4 = new Account(var1, "com.google");
      return getToken(var0, var4, var2, var3);
   }

   public static String getToken(Context var0, Account var1, String var2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      return getToken(var0, var1, var2, new Bundle());
   }

   public static String getToken(Context var0, Account var1, String var2, Bundle var3) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      zzc(var1);
      return zzb(var0, var1, var2, var3).getToken();
   }

   public static TokenData zzb(Context var0, Account var1, String var2, Bundle var3) throws IOException, UserRecoverableAuthException, GoogleAuthException {
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      zzbo.zzh(var2, "Scope cannot be empty or null.");
      zzc(var1);
      zzah(var0);
      Bundle var4 = var3 == null ? new Bundle() : new Bundle(var3);
      String var5 = var0.getApplicationInfo().packageName;
      var4.putString("clientPackageName", var5);
      if (TextUtils.isEmpty(var4.getString(KEY_ANDROID_PACKAGE_NAME))) {
         var4.putString(KEY_ANDROID_PACKAGE_NAME, var5);
      }

      var4.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
      zze var6 = new zze(var1, var2, var4);
      return (TokenData)zza(var0, zzakm, var6);
   }

   /** @deprecated */
   @Deprecated
   @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
   public static void invalidateToken(Context var0, String var1) {
      AccountManager.get(var0).invalidateAuthToken("com.google", var1);
   }

   public static void clearToken(Context var0, String var1) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      zzah(var0);
      Bundle var2 = new Bundle();
      String var3 = var0.getApplicationInfo().packageName;
      var2.putString("clientPackageName", var3);
      if (!var2.containsKey(KEY_ANDROID_PACKAGE_NAME)) {
         var2.putString(KEY_ANDROID_PACKAGE_NAME, var3);
      }

      zzf var4 = new zzf(var1, var2);
      zza(var0, zzakm, var4);
   }

   public static List getAccountChangeEvents(Context var0, int var1, String var2) throws GoogleAuthException, IOException {
      zzbo.zzh(var2, "accountName must be provided");
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      zzah(var0);
      zzg var3 = new zzg(var2, var1);
      return (List)zza(var0, zzakm, var3);
   }

   public static String getAccountId(Context var0, String var1) throws GoogleAuthException, IOException {
      zzbo.zzh(var1, "accountName must be provided");
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      zzah(var0);
      return getToken(var0, var1, "^^_account_id_^^", new Bundle());
   }

   @TargetApi(23)
   public static Bundle removeAccount(Context var0, Account var1) throws GoogleAuthException, IOException {
      zzbo.zzu(var0);
      zzc(var1);
      zzah(var0);
      zzh var2 = new zzh(var1);
      return (Bundle)zza(var0, zzakm, var2);
   }

   private static void zzc(Account var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Account cannot be null");
      } else if (TextUtils.isEmpty(var0.name)) {
         throw new IllegalArgumentException("Account name cannot be empty!");
      } else {
         String[] var1 = zzakl;
         int var2 = zzakl.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            if (var1[var3].equals(var0.type)) {
               return;
            }
         }

         throw new IllegalArgumentException("Account type not supported");
      }
   }

   private static void zzah(Context var0) throws GoogleAuthException {
      try {
         zzo.zzah(var0.getApplicationContext());
      } catch (GooglePlayServicesRepairableException var2) {
         throw new GooglePlayServicesAvailabilityException(var2.getConnectionStatusCode(), var2.getMessage(), var2.getIntent());
      } catch (GooglePlayServicesNotAvailableException var3) {
         throw new GoogleAuthException(var3.getMessage());
      }
   }

   private static Object zzl(Object var0) throws IOException {
      if (var0 == null) {
         zzakn.zzf("GoogleAuthUtil", new Object[]{"Binder call returned null."});
         throw new IOException("Service unavailable.");
      } else {
         return var0;
      }
   }

   private static Object zza(Context var0, ComponentName var1, zzi var2) throws IOException, GoogleAuthException {
      com.google.android.gms.common.zza var3 = new com.google.android.gms.common.zza();
      zzae var4;
      if ((var4 = zzae.zzaC(var0)).zza(var1, var3, "GoogleAuthUtil")) {
         Object var5;
         try {
            var5 = var2.zzy(var3.zzoV());
         } catch (InterruptedException | RemoteException var9) {
            zzakn.zze("GoogleAuthUtil", new Object[]{"Error on service connection.", var9});
            throw new IOException("Error on service connection.", var9);
         } finally {
            var4.zzb(var1, var3, "GoogleAuthUtil");
         }

         return var5;
      } else {
         throw new IOException("Could not bind to service.");
      }
   }

   // $FF: synthetic method
   static Object zzm(Object var0) throws IOException {
      return zzl(var0);
   }

   // $FF: synthetic method
   static zzbgb zzmp() {
      return zzakn;
   }

   static {
      String[] var0 = new String[]{"GoogleAuthUtil"};
      zzakn = new zzbgb("Auth", var0);
   }
}
