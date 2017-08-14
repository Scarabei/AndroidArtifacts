package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GeofencingClient extends GoogleApi {
   public GeofencingClient(@NonNull Context var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   public GeofencingClient(@NonNull Activity var1) {
      super(var1, LocationServices.API, (ApiOptions)null, new zzbas());
   }

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   public Task addGeofences(GeofencingRequest var1, PendingIntent var2) {
      return zzbh.zzb(LocationServices.GeofencingApi.addGeofences(this.zzpi(), var1, var2));
   }

   public Task removeGeofences(PendingIntent var1) {
      return zzbh.zzb(LocationServices.GeofencingApi.removeGeofences(this.zzpi(), var1));
   }

   public Task removeGeofences(List var1) {
      return zzbh.zzb(LocationServices.GeofencingApi.removeGeofences(this.zzpi(), var1));
   }
}
