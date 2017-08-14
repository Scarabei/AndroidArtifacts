package com.google.android.gms.ads.mediation;

import android.location.Location;
import java.util.Date;
import java.util.Set;

public interface MediationAdRequest {
   int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
   int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
   int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;

   Date getBirthday();

   int getGender();

   Set getKeywords();

   Location getLocation();

   int taggedForChildDirectedTreatment();

   boolean isTesting();

   boolean isDesignedForFamilies();
}
