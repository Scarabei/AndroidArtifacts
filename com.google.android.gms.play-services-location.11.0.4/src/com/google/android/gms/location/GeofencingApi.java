package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import java.util.List;

public interface GeofencingApi {
   /** @deprecated */
   @Deprecated
   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult addGeofences(GoogleApiClient var1, List var2, PendingIntent var3);

   @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
   PendingResult addGeofences(GoogleApiClient var1, GeofencingRequest var2, PendingIntent var3);

   PendingResult removeGeofences(GoogleApiClient var1, PendingIntent var2);

   PendingResult removeGeofences(GoogleApiClient var1, List var2);
}
