package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface PlaceDetectionApi {
   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getCurrentPlace(GoogleApiClient var1, @Nullable PlaceFilter var2);

   PendingResult reportDeviceAtPlace(GoogleApiClient var1, PlaceReport var2);
}
