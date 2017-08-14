package com.google.android.gms.location;

import java.util.Comparator;

final class zzc implements Comparator {
   // $FF: synthetic method
   public final int compare(Object var1, Object var2) {
      DetectedActivity var10000 = (DetectedActivity)var1;
      DetectedActivity var4 = (DetectedActivity)var2;
      DetectedActivity var3 = var10000;
      int var5;
      return (var5 = var4.getConfidence().compareTo(var3.getConfidence())) == 0 ? var3.getType().compareTo(var4.getType()) : var5;
   }
}
