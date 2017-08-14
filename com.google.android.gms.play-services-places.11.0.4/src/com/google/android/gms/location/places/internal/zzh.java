package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzh implements GeoDataApi {
   public final PendingResult addPlace(GoogleApiClient var1, AddPlaceRequest var2) {
      return var1.zze(new zzi(this, Places.GEO_DATA_API, var1, var2));
   }

   public final PendingResult getPlaceById(GoogleApiClient var1, String... var2) {
      zzbo.zzaf(var2 != null && var2.length > 0);
      return var1.zzd(new zzk(this, Places.GEO_DATA_API, var1, var2));
   }

   public final PendingResult getAutocompletePredictions(GoogleApiClient var1, String var2, LatLngBounds var3, AutocompleteFilter var4) {
      return var1.zzd(new zzl(this, Places.GEO_DATA_API, var1, var2, var3, var4));
   }

   public final PendingResult getPlacePhotos(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzj(this, Places.GEO_DATA_API, var1, var2));
   }
}
