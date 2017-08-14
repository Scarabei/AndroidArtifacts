package com.google.android.gms.nearby.messages;

import android.support.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Distance extends Comparable {
   Distance UNKNOWN = new com.google.android.gms.nearby.messages.internal.zze(1, Double.NaN);

   int getAccuracy();

   double getMeters();

   int compareTo(@NonNull Distance var1);

   @Retention(RetentionPolicy.SOURCE)
   public @interface Accuracy {
      int LOW = 1;
   }
}
