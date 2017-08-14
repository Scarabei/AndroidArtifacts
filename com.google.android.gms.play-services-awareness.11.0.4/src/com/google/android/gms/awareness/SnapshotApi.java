package com.google.android.gms.awareness;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import java.util.Collection;

public interface SnapshotApi {
   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getBeaconState(GoogleApiClient var1, Collection var2);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getBeaconState(GoogleApiClient var1, BeaconState.TypeFilter... var2);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getTimeIntervals(GoogleApiClient var1);

   @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
   PendingResult getDetectedActivity(GoogleApiClient var1);

   PendingResult getHeadphoneState(GoogleApiClient var1);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getLocation(GoogleApiClient var1);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getPlaces(GoogleApiClient var1);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult getWeather(GoogleApiClient var1);
}
