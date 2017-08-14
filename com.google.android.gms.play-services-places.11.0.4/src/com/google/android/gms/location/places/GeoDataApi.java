package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.maps.model.LatLngBounds;

public interface GeoDataApi {
   PendingResult addPlace(GoogleApiClient var1, AddPlaceRequest var2);

   PendingResult getPlaceById(GoogleApiClient var1, String... var2);

   PendingResult getAutocompletePredictions(GoogleApiClient var1, String var2, LatLngBounds var3, AutocompleteFilter var4);

   PendingResult getPlacePhotos(GoogleApiClient var1, String var2);
}
