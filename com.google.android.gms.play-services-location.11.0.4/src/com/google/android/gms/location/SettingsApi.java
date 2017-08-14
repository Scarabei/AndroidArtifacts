package com.google.android.gms.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface SettingsApi {
   PendingResult checkLocationSettings(GoogleApiClient var1, LocationSettingsRequest var2);
}
