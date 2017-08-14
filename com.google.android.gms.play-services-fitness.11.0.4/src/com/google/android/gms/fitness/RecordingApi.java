package com.google.android.gms.fitness;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;

public interface RecordingApi {
   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult subscribe(GoogleApiClient var1, DataType var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"},
      conditional = true
   )
   PendingResult subscribe(GoogleApiClient var1, DataSource var2);

   PendingResult unsubscribe(GoogleApiClient var1, DataType var2);

   PendingResult unsubscribe(GoogleApiClient var1, DataSource var2);

   PendingResult unsubscribe(GoogleApiClient var1, Subscription var2);

   PendingResult listSubscriptions(GoogleApiClient var1);

   PendingResult listSubscriptions(GoogleApiClient var1, DataType var2);
}
