package com.google.android.gms.plus;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzcqv;
import com.google.android.gms.internal.zzcqy;
import com.google.android.gms.internal.zzcqz;
import com.google.android.gms.internal.zzcra;
import com.google.android.gms.plus.internal.zzh;
import java.util.HashSet;
import java.util.Set;

/** @deprecated */
@Deprecated
public final class Plus {
   public static final zzf zzajR = new zzf();
   private static com.google.android.gms.common.api.Api.zza zzajS = new zzc();
   /** @deprecated */
   @Deprecated
   public static final Api API;
   public static final Scope SCOPE_PLUS_LOGIN;
   public static final Scope SCOPE_PLUS_PROFILE;
   /** @deprecated */
   @Deprecated
   public static final People PeopleApi;
   /** @deprecated */
   @Deprecated
   public static final Account AccountApi;
   /** @deprecated */
   @Deprecated
   private static zzb zzbAp;
   private static zza zzbAq;

   public static zzh zzc(GoogleApiClient var0, boolean var1) {
      zzbo.zzb(var0 != null, "GoogleApiClient parameter is required.");
      zzbo.zza(var0.isConnected(), "GoogleApiClient must be connected.");
      zzbo.zza(var0.zza(API), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
      boolean var2 = var0.hasConnectedApi(API);
      if (var1 && !var2) {
         throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
      } else {
         return var2 ? (zzh)var0.zza(zzajR) : null;
      }
   }

   static {
      API = new Api("Plus.API", zzajS, zzajR);
      SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
      SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
      PeopleApi = new zzcra();
      AccountApi = new zzcqv();
      zzbAp = new zzcqz();
      zzbAq = new zzcqy();
   }

   public abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(Plus.zzajR, var1);
      }
   }

   public static final class PlusOptions implements Optional {
      private String zzbAr;
      final Set zzbAs;

      private PlusOptions() {
         this.zzbAr = null;
         this.zzbAs = new HashSet();
      }

      private PlusOptions(Plus.PlusOptions.Builder var1) {
         this.zzbAr = var1.zzbAr;
         this.zzbAs = var1.zzbAs;
      }

      public static Plus.PlusOptions.Builder builder() {
         return new Plus.PlusOptions.Builder();
      }

      // $FF: synthetic method
      PlusOptions(Plus.PlusOptions.Builder var1, zzc var2) {
         this(var1);
      }

      // $FF: synthetic method
      PlusOptions(zzc var1) {
         this();
      }

      public static final class Builder {
         String zzbAr;
         final Set zzbAs = new HashSet();

         public final Plus.PlusOptions.Builder setServerClientId(String var1) {
            this.zzbAr = var1;
            return this;
         }

         public final Plus.PlusOptions.Builder addActivityTypes(String... var1) {
            zzbo.zzb(var1, "activityTypes may not be null.");

            for(int var2 = 0; var2 < var1.length; ++var2) {
               this.zzbAs.add(var1[var2]);
            }

            return this;
         }

         public final Plus.PlusOptions build() {
            return new Plus.PlusOptions(this, (zzc)null);
         }
      }
   }
}
