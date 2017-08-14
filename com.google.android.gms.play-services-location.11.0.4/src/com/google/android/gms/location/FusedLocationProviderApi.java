package com.google.android.gms.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface FusedLocationProviderApi {
   /** @deprecated */
   @Deprecated
   String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
   String KEY_MOCK_LOCATION = "mockLocation";

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   Location getLastLocation(GoogleApiClient var1);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   LocationAvailability getLocationAvailability(GoogleApiClient var1);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationListener var3);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationListener var3, Looper var4);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, LocationCallback var3, Looper var4);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult requestLocationUpdates(GoogleApiClient var1, LocationRequest var2, PendingIntent var3);

   PendingResult removeLocationUpdates(GoogleApiClient var1, LocationListener var2);

   PendingResult removeLocationUpdates(GoogleApiClient var1, PendingIntent var2);

   PendingResult removeLocationUpdates(GoogleApiClient var1, LocationCallback var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult setMockMode(GoogleApiClient var1, boolean var2);

   @RequiresPermission(
      anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
   )
   PendingResult setMockLocation(GoogleApiClient var1, Location var2);

   PendingResult flushLocations(GoogleApiClient var1);
}
