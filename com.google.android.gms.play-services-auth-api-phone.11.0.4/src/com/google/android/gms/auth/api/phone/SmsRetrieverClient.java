package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.tasks.Task;

public abstract class SmsRetrieverClient extends GoogleApi implements SmsRetrieverApi {
   private static final zzf zzajR = new zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zza();
   private static final Api API;

   public SmsRetrieverClient(@NonNull Context var1) {
      super(var1, API, (ApiOptions)null, new zzbas());
   }

   public SmsRetrieverClient(@NonNull Activity var1) {
      super(var1, API, (ApiOptions)null, new zzbas());
   }

   public abstract Task startSmsRetriever();

   static {
      API = new Api("SmsRetriever.API", zzajS, zzajR);
   }
}
