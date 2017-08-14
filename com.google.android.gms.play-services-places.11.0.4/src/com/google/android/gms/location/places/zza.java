package com.google.android.gms.location.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class zza extends com.google.android.gms.common.internal.safeparcel.zza {
   public boolean isRestrictedToPlacesOpenNow() {
      return false;
   }

   public abstract Set getPlaceIds();

   static List zzh(Collection var0) {
      return (List)(var0 != null && !var0.isEmpty() ? new ArrayList(var0) : Collections.emptyList());
   }

   static Set zzC(List var0) {
      return var0 != null && !var0.isEmpty() ? Collections.unmodifiableSet(new HashSet(var0)) : Collections.emptySet();
   }
}
