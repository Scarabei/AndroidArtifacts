package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.internal.zzaqy;
import com.google.android.gms.internal.zzaqz;
import com.google.android.gms.internal.zzarg;
import com.google.android.gms.internal.zzasn;

public final class Auth {
   public static final zzf zzakE = new zzf();
   private static zzf zzakF = new zzf();
   public static final zzf zzakG = new zzf();
   private static final com.google.android.gms.common.api.Api.zza zzakH = new zza();
   private static final com.google.android.gms.common.api.Api.zza zzakI = new zzb();
   private static final com.google.android.gms.common.api.Api.zza zzakJ = new zzc();
   @KeepForSdk
   public static final Api PROXY_API;
   public static final Api CREDENTIALS_API;
   public static final Api GOOGLE_SIGN_IN_API;
   private static Api zzakK;
   @KeepForSdk
   public static final ProxyApi ProxyApi;
   public static final CredentialsApi CredentialsApi;
   private static zzaqy zzakL;
   public static final GoogleSignInApi GoogleSignInApi;

   static {
      PROXY_API = zzd.API;
      CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", zzakH, zzakE);
      GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", zzakJ, zzakG);
      zzakK = new Api("Auth.ACCOUNT_STATUS_API", zzakI, zzakF);
      ProxyApi = new zzasn();
      CredentialsApi = new zzarg();
      zzakL = new zzaqz();
      GoogleSignInApi = new com.google.android.gms.auth.api.signin.internal.zzc();
   }

   public static final class AuthCredentialsOptions implements Optional {
      private final String zzakM;
      private final PasswordSpecification zzakN;

      public final PasswordSpecification zzmr() {
         return this.zzakN;
      }

      public final Bundle zzmo() {
         Bundle var1;
         (var1 = new Bundle()).putString("consumer_package", this.zzakM);
         var1.putParcelable("password_specification", this.zzakN);
         return var1;
      }

      public static class Builder {
         @NonNull
         private PasswordSpecification zzakN;

         public Builder() {
            this.zzakN = PasswordSpecification.zzalo;
         }
      }
   }
}
