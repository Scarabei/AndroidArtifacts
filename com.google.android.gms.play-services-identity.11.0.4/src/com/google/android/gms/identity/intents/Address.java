package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.internal.zzbay;

public final class Address {
   private static zzf zzajR = new zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zza();
   public static final Api API;

   public static void requestUserAddress(GoogleApiClient var0, UserAddressRequest var1, int var2) {
      var0.zzd(new zzb(var0, var1, var2));
   }

   static {
      API = new Api("Address.API", zzajS, zzajR);
   }

   abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(Address.API, var1);
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return var1;
      }
   }

   public static final class AddressOptions implements HasOptions {
      public final int theme;

      public AddressOptions() {
         this.theme = 0;
      }

      public AddressOptions(int var1) {
         this.theme = var1;
      }
   }
}
