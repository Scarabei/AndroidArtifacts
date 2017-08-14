package com.google.android.gms.location.places;

import com.google.android.gms.location.places.internal.zzag;
import java.util.Comparator;

final class zzi implements Comparator {
   // $FF: synthetic method
   public final int compare(Object var1, Object var2) {
      zzag var10000 = (zzag)var1;
      zzag var3 = (zzag)var2;
      return -Float.compare(var10000.getLikelihood(), var3.getLikelihood());
   }
}
