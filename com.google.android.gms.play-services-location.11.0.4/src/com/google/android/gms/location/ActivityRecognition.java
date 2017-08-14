package com.google.android.gms.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzcbt;

public class ActivityRecognition {
   public static final String CLIENT_NAME = "activity_recognition";
   private static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zza();
   public static final Api API;
   public static final ActivityRecognitionApi ActivityRecognitionApi;

   static {
      API = new Api("ActivityRecognition.API", zzajS, zzajR);
      ActivityRecognitionApi = new zzcbt();
   }

   public abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(ActivityRecognition.API, var1);
      }
   }
}
