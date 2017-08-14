package com.google.android.gms.auth.api.credentials;

import android.accounts.Account;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;

public final class IdentityProviders {
   public static final String FACEBOOK = "https://www.facebook.com";
   public static final String GOOGLE = "https://accounts.google.com";
   public static final String LINKEDIN = "https://www.linkedin.com";
   public static final String MICROSOFT = "https://login.live.com";
   public static final String PAYPAL = "https://www.paypal.com";
   public static final String TWITTER = "https://twitter.com";
   public static final String YAHOO = "https://login.yahoo.com";

   @Nullable
   public static final String getIdentityProviderForAccount(@NonNull Account var0) {
      zzbo.zzb(var0, "account cannot be null");
      if ("com.google".equals(var0.type)) {
         return "https://accounts.google.com";
      } else {
         return "com.facebook.auth.login".equals(var0.type) ? "https://www.facebook.com" : null;
      }
   }
}
