package com.google.android.gms.appinvite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/** @deprecated */
@Deprecated
public class AppInviteReferral {
   public static boolean hasReferral(Intent var0) {
      return var0 != null && var0.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE") != null;
   }

   public static boolean isOpenedFromPlayStore(Intent var0) {
      return hasReferral(var0) && var0.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE").getBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", false);
   }

   public static String getInvitationId(Intent var0) {
      Bundle var1;
      return var0 != null && (var1 = var0.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE")) != null ? var1.getString("com.google.android.gms.appinvite.INVITATION_ID") : null;
   }

   public static String getDeepLink(Intent var0) {
      Bundle var1;
      return var0 != null && (var1 = var0.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE")) != null ? var1.getString("com.google.android.gms.appinvite.DEEP_LINK") : null;
   }

   /** @deprecated */
   @Deprecated
   public static Intent addPlayStoreReferrerToIntent(Intent var0, Intent var1) {
      Bundle var2;
      if ((var2 = zzd(var0)) != null && var1 != null) {
         var1.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", var2);
      }

      return var1;
   }

   /** @deprecated */
   @Deprecated
   public static Intent addReferralDataToIntent(String var0, String var1, Intent var2) {
      return var2 == null ? null : var2.putExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE", zzb(var0, var1, false));
   }

   private static Bundle zzb(String var0, String var1, boolean var2) {
      Bundle var3;
      (var3 = new Bundle()).putString("com.google.android.gms.appinvite.INVITATION_ID", var0);
      if (var1 != null) {
         var3.putString("com.google.android.gms.appinvite.DEEP_LINK", var1);
      }

      var3.putBoolean("com.google.android.gms.appinvite.OPENED_FROM_PLAY_STORE", var2);
      return var3;
   }

   private static Bundle zzd(Intent var0) {
      if (var0 != null && var0.getAction().equals("com.android.vending.INSTALL_REFERRER") && var0.getStringExtra("referrer") != null) {
         String var1 = var0.getStringExtra("referrer");

         String var10001;
         String var10002;
         String var10003;
         try {
            var1 = URLDecoder.decode(var1, "UTF-8");
         } catch (UnsupportedEncodingException var5) {
            var10002 = String.valueOf(var1);
            if (var10002.length() != 0) {
               var10001 = "Error parsing Play Store referrer URL: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Error parsing Play Store referrer URL: ");
            }

            Log.e("AppInviteRef", var10001);
            return null;
         }

         var10001 = String.valueOf(var1);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "s://a.b.c?".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("s://a.b.c?");
         }

         Uri var2;
         String var3 = (var2 = Uri.parse(var10000)).getQueryParameter("invitation_id");
         String var4 = var2.getQueryParameter("deep_link_id");
         if (var3 == null && var4 == null) {
            var10002 = String.valueOf(var1);
            if (var10002.length() != 0) {
               var10001 = "Missing  Referrer query params: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Missing  Referrer query params: ");
            }

            Log.w("AppInviteRef", var10001);
            return null;
         } else {
            return zzb(var3, var4, true);
         }
      } else {
         return null;
      }
   }
}
