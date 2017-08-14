package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzccb;
import com.google.android.gms.internal.zzccq;
import com.google.android.gms.internal.zzcdj;
import com.google.android.gms.internal.zzcdu;

public class LocationServices {
   private static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzs();
   public static final Api API;
   public static final FusedLocationProviderApi FusedLocationApi;
   public static final GeofencingApi GeofencingApi;
   public static final SettingsApi SettingsApi;

   public static zzcdj zzg(GoogleApiClient var0) {
      zzbo.zzb(var0 != null, "GoogleApiClient parameter is required.");
      zzcdj var1;
      zzbo.zza((var1 = (zzcdj)var0.zza(zzajR)) != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
      return var1;
   }

   public static GeofencingClient getGeofencingClient(@NonNull Activity var0) {
      return new GeofencingClient(var0);
   }

   public static GeofencingClient getGeofencingClient(@NonNull Context var0) {
      return new GeofencingClient(var0);
   }

   public static SettingsClient getSettingsClient(@NonNull Activity var0) {
      return new SettingsClient(var0);
   }

   public static SettingsClient getSettingsClient(@NonNull Context var0) {
      return new SettingsClient(var0);
   }

   public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity var0) {
      return new FusedLocationProviderClient(var0);
   }

   public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context var0) {
      return new FusedLocationProviderClient(var0);
   }

   static {
      API = new Api("LocationServices.API", zzajS, zzajR);
      FusedLocationApi = new zzccb();
      GeofencingApi = new zzccq();
      SettingsApi = new zzcdu();
   }

   public abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(LocationServices.API, var1);
      }
   }
}
