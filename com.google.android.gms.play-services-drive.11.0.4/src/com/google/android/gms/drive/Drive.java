package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzblo;
import com.google.android.gms.internal.zzbmh;
import com.google.android.gms.internal.zzbmw;
import com.google.android.gms.internal.zzbnh;
import com.google.android.gms.internal.zzbnz;

public final class Drive {
   public static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   public static final Scope SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
   public static final Scope SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
   private static Scope zzaMa = new Scope("https://www.googleapis.com/auth/drive");
   private static Scope zzaMb = new Scope("https://www.googleapis.com/auth/drive.apps");
   public static final Api API;
   private static Api zzaMc;
   public static final DriveApi DriveApi;
   private static zzi zzaMd;
   private static zzk zzaMe;
   public static final DrivePreferencesApi DrivePreferencesApi;

   static {
      API = new Api("Drive.API", new zze(), zzajR);
      zzaMc = new Api("Drive.INTERNAL_API", new zzf(), zzajR);
      DriveApi = new zzblo();
      zzaMd = new zzbmw();
      zzaMe = new zzbnz();
      DrivePreferencesApi = new zzbnh();
   }

   public static class zzb implements Optional {
      private final Bundle mBundle;

      public final Bundle zzsL() {
         return this.mBundle;
      }
   }

   public abstract static class zza extends com.google.android.gms.common.api.Api.zza {
      protected abstract Bundle zza(ApiOptions var1);

      // $FF: synthetic method
      public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
         ApiOptions var11 = (ApiOptions)var4;
         return new zzbmh(var1, var2, var3, var5, var6, this.zza(var11));
      }
   }
}
