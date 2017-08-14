package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.tasks.Task;

public class SettingsClient extends GoogleApi {
   public SettingsClient(@NonNull Context var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   public SettingsClient(@NonNull Activity var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   public Task checkLocationSettings(LocationSettingsRequest var1) {
      return zzbh.zza(LocationServices.SettingsApi.checkLocationSettings(this.zzpi(), var1), new LocationSettingsResponse());
   }
}
