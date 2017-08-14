package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;

public final class zzx implements PlaceDetectionApi {
   public final PendingResult getCurrentPlace(GoogleApiClient var1, PlaceFilter var2) {
      return var1.zzd(new zzy(this, Places.PLACE_DETECTION_API, var1, var2));
   }

   public final PendingResult reportDeviceAtPlace(GoogleApiClient var1, PlaceReport var2) {
      return var1.zze(new zzz(this, Places.PLACE_DETECTION_API, var1, var2));
   }
}
