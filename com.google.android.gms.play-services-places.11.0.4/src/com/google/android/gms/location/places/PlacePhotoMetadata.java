package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.Freezable;

public interface PlacePhotoMetadata extends Freezable {
   int getMaxWidth();

   int getMaxHeight();

   CharSequence getAttributions();

   PendingResult getPhoto(GoogleApiClient var1);

   PendingResult getScaledPhoto(GoogleApiClient var1, int var2, int var3);
}
