package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsApi;

public final class zzcdu implements SettingsApi {
   public final PendingResult checkLocationSettings(GoogleApiClient var1, LocationSettingsRequest var2) {
      return var1.zzd(new zzcdv(this, var1, var2, (String)null));
   }
}
