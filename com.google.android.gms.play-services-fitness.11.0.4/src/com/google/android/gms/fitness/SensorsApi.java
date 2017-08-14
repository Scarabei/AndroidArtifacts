package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;

public interface SensorsApi {
   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult findDataSources(GoogleApiClient var1, DataSourcesRequest var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult add(GoogleApiClient var1, SensorRequest var2, OnDataPointListener var3);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult add(GoogleApiClient var1, SensorRequest var2, PendingIntent var3);

   PendingResult remove(GoogleApiClient var1, OnDataPointListener var2);

   PendingResult remove(GoogleApiClient var1, PendingIntent var2);
}
