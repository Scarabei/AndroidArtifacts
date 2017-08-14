package com.google.android.gms.appinvite;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Map;

public final class AppInviteInvitation {
   private static final String[] zzajT = new String[]{"jpg", "jpeg", "png"};

   public static String[] getInvitationIds(int var0, @NonNull Intent var1) {
      return var0 == -1 ? var1.getStringArrayExtra("com.google.android.gms.appinvite.RESULT_INVITATION_IDS") : null;
   }

   private static Bundle zzl(Map var0) {
      Bundle var1 = new Bundle();
      Iterator var2 = var0.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.putString(var3, (String)var0.get(var3));
      }

      return var1;
   }

   private static boolean zzbK(String var0) {
      for(int var1 = 0; var1 < zzajT.length; ++var1) {
         if (zzajT[var1].equals(var0)) {
            return true;
         }
      }

      return false;
   }

   public static final class IntentBuilder {
      public static final int MAX_MESSAGE_LENGTH = 100;
      public static final int MAX_EMAIL_HTML_CONTENT = 512000;
      public static final int MIN_CALL_TO_ACTION_TEXT_LENGTH = 2;
      public static final int MAX_CALL_TO_ACTION_TEXT_LENGTH = 20;
      private final Intent mIntent;
      private String zzajU;
      private String zzajV;

      public IntentBuilder(@NonNull CharSequence var1) {
         zzbo.zzu(var1);
         this.mIntent = new Intent("com.google.android.gms.appinvite.ACTION_APP_INVITE");
         this.mIntent.putExtra("com.google.android.gms.appinvite.TITLE", var1);
         this.mIntent.setPackage("com.google.android.gms");
      }

      public final AppInviteInvitation.IntentBuilder setAccount(Account var1) {
         if (var1 != null && "com.google".equals(var1.type)) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.ACCOUNT_NAME", var1);
         } else {
            this.mIntent.removeExtra("com.google.android.gms.appinvite.ACCOUNT_NAME");
         }

         return this;
      }

      public final AppInviteInvitation.IntentBuilder setMessage(CharSequence var1) {
         if (var1 != null && var1.length() > 100) {
            throw new IllegalArgumentException(String.format("Message must be %d chars or less.", Integer.valueOf(100)));
         } else {
            this.mIntent.putExtra("com.google.android.gms.appinvite.MESSAGE", var1);
            return this;
         }
      }

      public final AppInviteInvitation.IntentBuilder setEmailSubject(String var1) {
         this.zzajU = var1;
         return this;
      }

      public final AppInviteInvitation.IntentBuilder setEmailHtmlContent(String var1) {
         if (var1 != null && var1.getBytes().length > 512000) {
            throw new IllegalArgumentException(String.format("Email html content must be %d bytes or less.", 512000));
         } else {
            this.zzajV = var1;
            return this;
         }
      }

      public final AppInviteInvitation.IntentBuilder setDeepLink(Uri var1) {
         if (var1 != null) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.DEEP_LINK_URL", var1);
         } else {
            this.mIntent.removeExtra("com.google.android.gms.appinvite.DEEP_LINK_URL");
         }

         return this;
      }

      public final AppInviteInvitation.IntentBuilder setAdditionalReferralParameters(Map var1) {
         if (var1 != null) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI", AppInviteInvitation.zzl(var1));
         } else {
            this.mIntent.removeExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI");
         }

         return this;
      }

      public final AppInviteInvitation.IntentBuilder setGoogleAnalyticsTrackingId(String var1) {
         this.mIntent.putExtra("com.google.android.gms.appinvite.GOOGLE_ANALYTICS_TRACKING_ID", var1);
         return this;
      }

      public final AppInviteInvitation.IntentBuilder setAndroidMinimumVersionCode(int var1) {
         this.mIntent.putExtra("com.google.android.gms.appinvite.appMinimumVersionCode", var1);
         return this;
      }

      public final AppInviteInvitation.IntentBuilder setCustomImage(Uri var1) {
         zzbo.zzu(var1);
         zzbo.zzb(var1.isAbsolute(), "Image uri is not an absolute uri. Did you forget to add a scheme to the Uri?");
         String var2;
         boolean var3;
         zzbo.zzb((var3 = (var2 = var1.getScheme().toLowerCase()).equals("android.resource") || var2.equals("content") || var2.equals("file")) || var2.equals("http") || var2.equals("https"), "Image uri must be a content URI with scheme \"android.resource\", \"content\" or \"file\", or a network url with scheme \"http\" or \"https\".");
         if (!var3) {
            String var4;
            String var10000 = var4 = var1.toString();
            String var5;
            String var6;
            zzbo.zzb(TextUtils.isEmpty(var6 = (var5 = var10000.substring(var10000.lastIndexOf("/") + 1, var4.length())) == null ? null : (var5.lastIndexOf(".") == -1 ? null : var5.substring(var5.lastIndexOf(".") + 1, var5.length()).toLowerCase())) || AppInviteInvitation.zzbK(var6), String.valueOf(var6).concat(" images are not supported. Only jpg, jpeg, or png images are supported."));
         }

         Uri var7 = var1.buildUpon().scheme(var2).build();
         this.mIntent.setData(var7);
         if (var3) {
            this.mIntent.addFlags(1);
         }

         return this;
      }

      public final AppInviteInvitation.IntentBuilder setCallToActionText(CharSequence var1) {
         if (var1 != null && var1.length() >= 2 && var1.length() <= 20) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.BUTTON_TEXT", var1);
            return this;
         } else {
            throw new IllegalArgumentException(String.format("Text must be between %d and %d chars in length.", Integer.valueOf(2), Integer.valueOf(20)));
         }
      }

      public final AppInviteInvitation.IntentBuilder setOtherPlatformsTargetApplication(int var1, String var2) throws IllegalArgumentException {
         switch(var1) {
         case 1:
            this.mIntent.putExtra("com.google.android.gms.appinvite.iosTargetApplication", var2);
            break;
         case 2:
            this.mIntent.putExtra("com.google.android.gms.appinvite.androidTargetApplication", var2);
            break;
         default:
            throw new IllegalArgumentException("targetPlatform must be either PROJECT_PLATFORM_IOS or PROJECT_PLATFORM_ANDROID.");
         }

         return this;
      }

      public final Intent build() {
         if (!TextUtils.isEmpty(this.zzajU)) {
            zzbo.zzh(this.zzajV, "Email html content must be set when email subject is set.");
            zzbo.zzb(this.mIntent.getData() == null, "Custom image must not be set when email html content is set.");
            zzbo.zzb(TextUtils.isEmpty(this.mIntent.getCharSequenceExtra("com.google.android.gms.appinvite.BUTTON_TEXT")), "Call to action text must not be set when email html content is set.");
            this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_SUBJECT", this.zzajU);
            this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_CONTENT", this.zzajV);
         } else if (!TextUtils.isEmpty(this.zzajV)) {
            throw new IllegalArgumentException("Email subject must be set when email html content is set.");
         }

         return this.mIntent;
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface PlatformMode {
         int PROJECT_PLATFORM_IOS = 1;
         int PROJECT_PLATFORM_ANDROID = 2;
      }
   }
}
