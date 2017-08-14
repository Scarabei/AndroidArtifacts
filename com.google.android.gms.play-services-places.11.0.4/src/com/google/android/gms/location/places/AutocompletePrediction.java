package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface AutocompletePrediction extends Freezable {
   CharSequence getFullText(@Nullable CharacterStyle var1);

   CharSequence getPrimaryText(@Nullable CharacterStyle var1);

   CharSequence getSecondaryText(@Nullable CharacterStyle var1);

   @Nullable
   String getPlaceId();

   @Nullable
   List getPlaceTypes();
}
