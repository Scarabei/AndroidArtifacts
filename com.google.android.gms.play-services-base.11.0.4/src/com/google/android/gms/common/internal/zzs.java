package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbha;

public final class zzs {
   private static final SimpleArrayMap zzaHo = new SimpleArrayMap();

   @Nullable
   public static String zzg(Context var0, int var1) {
      Resources var2 = var0.getResources();
      switch(var1) {
      case 1:
         return var2.getString(string.common_google_play_services_install_title);
      case 2:
         return var2.getString(string.common_google_play_services_update_title);
      case 3:
         return var2.getString(string.common_google_play_services_enable_title);
      case 4:
      case 6:
      case 18:
         return null;
      case 5:
         Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
         return zzz(var0, "common_google_play_services_invalid_account_title");
      case 7:
         Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
         return zzz(var0, "common_google_play_services_network_error_title");
      case 8:
         Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
         return null;
      case 9:
         Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
         return null;
      case 10:
         Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
         return null;
      case 11:
         Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
         return null;
      case 12:
      case 13:
      case 14:
      case 15:
      case 19:
      default:
         Log.e("GoogleApiAvailability", (new StringBuilder(33)).append("Unexpected error code ").append(var1).toString());
         return null;
      case 16:
         Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
         return null;
      case 17:
         Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
         return zzz(var0, "common_google_play_services_sign_in_failed_title");
      case 20:
         Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
         return zzz(var0, "common_google_play_services_restricted_profile_title");
      }
   }

   @NonNull
   public static String zzh(Context var0, int var1) {
      String var2;
      if (var1 == 6) {
         var2 = zzz(var0, "common_google_play_services_resolution_required_title");
      } else {
         var2 = zzg(var0, var1);
      }

      if (var2 == null) {
         var2 = var0.getResources().getString(string.common_google_play_services_notification_ticker);
      }

      return var2;
   }

   @NonNull
   public static String zzi(Context var0, int var1) {
      Resources var2 = var0.getResources();
      String var3 = zzaB(var0);
      switch(var1) {
      case 1:
         return var2.getString(string.common_google_play_services_install_text, new Object[]{var3});
      case 2:
         if (zzj.zzaH(var0)) {
            return var2.getString(string.common_google_play_services_wear_update_text);
         }

         return var2.getString(string.common_google_play_services_update_text, new Object[]{var3});
      case 3:
         return var2.getString(string.common_google_play_services_enable_text, new Object[]{var3});
      case 4:
      case 6:
      case 8:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 19:
      default:
         return var2.getString(string.common_google_play_services_unknown_issue, new Object[]{var3});
      case 5:
         return zzl(var0, "common_google_play_services_invalid_account_text", var3);
      case 7:
         return zzl(var0, "common_google_play_services_network_error_text", var3);
      case 9:
         return var2.getString(string.common_google_play_services_unsupported_text, new Object[]{var3});
      case 16:
         return zzl(var0, "common_google_play_services_api_unavailable_text", var3);
      case 17:
         return zzl(var0, "common_google_play_services_sign_in_failed_text", var3);
      case 18:
         return var2.getString(string.common_google_play_services_updating_text, new Object[]{var3});
      case 20:
         return zzl(var0, "common_google_play_services_restricted_profile_text", var3);
      }
   }

   @NonNull
   public static String zzj(Context var0, int var1) {
      return var1 == 6 ? zzl(var0, "common_google_play_services_resolution_required_text", zzaB(var0)) : zzi(var0, var1);
   }

   @NonNull
   public static String zzk(Context var0, int var1) {
      Resources var2 = var0.getResources();
      switch(var1) {
      case 1:
         return var2.getString(string.common_google_play_services_install_button);
      case 2:
         return var2.getString(string.common_google_play_services_update_button);
      case 3:
         return var2.getString(string.common_google_play_services_enable_button);
      default:
         return var2.getString(17039370);
      }
   }

   private static String zzaB(Context var0) {
      String var1 = var0.getPackageName();

      try {
         return zzbha.zzaP(var0).zzcM(var1).toString();
      } catch (NullPointerException | NameNotFoundException var3) {
         String var2;
         return TextUtils.isEmpty(var2 = var0.getApplicationInfo().name) ? var1 : var2;
      }
   }

   private static String zzl(Context var0, String var1, String var2) {
      Resources var3 = var0.getResources();
      String var4;
      if ((var4 = zzz(var0, var1)) == null) {
         var4 = var3.getString(string.common_google_play_services_unknown_issue);
      }

      return String.format(var3.getConfiguration().locale, var4, var2);
   }

   @Nullable
   private static String zzz(Context var0, String var1) {
      SimpleArrayMap var2 = zzaHo;
      synchronized(zzaHo) {
         String var3;
         if ((var3 = (String)zzaHo.get(var1)) != null) {
            return var3;
         } else {
            Resources var4;
            if ((var4 = GooglePlayServicesUtil.getRemoteResource(var0)) == null) {
               return null;
            } else {
               String var10001;
               String var10002;
               String var10003;
               int var5;
               if ((var5 = var4.getIdentifier(var1, "string", "com.google.android.gms")) == 0) {
                  var10002 = String.valueOf(var1);
                  if (var10002.length() != 0) {
                     var10001 = "Missing resource: ".concat(var10002);
                  } else {
                     var10003 = new String;
                     var10001 = var10003;
                     var10003.<init>("Missing resource: ");
                  }

                  Log.w("GoogleApiAvailability", var10001);
                  return null;
               } else if (TextUtils.isEmpty(var3 = var4.getString(var5))) {
                  var10002 = String.valueOf(var1);
                  if (var10002.length() != 0) {
                     var10001 = "Got empty resource: ".concat(var10002);
                  } else {
                     var10003 = new String;
                     var10001 = var10003;
                     var10003.<init>("Got empty resource: ");
                  }

                  Log.w("GoogleApiAvailability", var10001);
                  return null;
               } else {
                  zzaHo.put(var1, var3);
                  return var3;
               }
            }
         }
      }
   }
}
