package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.internal.zzac;
import com.google.android.gms.location.places.internal.zzx;

public class Places {
   private static com.google.android.gms.common.api.Api.zzf zzbjL = new com.google.android.gms.common.api.Api.zzf();
   private static com.google.android.gms.common.api.Api.zzf zzbjM = new com.google.android.gms.common.api.Api.zzf();
   public static final Api GEO_DATA_API;
   public static final Api PLACE_DETECTION_API;
   public static final GeoDataApi GeoDataApi;
   public static final PlaceDetectionApi PlaceDetectionApi;

   static {
      GEO_DATA_API = new Api("Places.GEO_DATA_API", new com.google.android.gms.location.places.internal.zzo(), zzbjL);
      PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzac(), zzbjM);
      GeoDataApi = new com.google.android.gms.location.places.internal.zzh();
      PlaceDetectionApi = new zzx();
   }
}
