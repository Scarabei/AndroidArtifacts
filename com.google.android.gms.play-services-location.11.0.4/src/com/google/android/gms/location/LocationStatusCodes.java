package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

/** @deprecated */
@Deprecated
public final class LocationStatusCodes {
   public static final int SUCCESS = 0;
   public static final int ERROR = 1;
   public static final int GEOFENCE_NOT_AVAILABLE = 1000;
   public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
   public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;

   public static int zzbj(int var0) {
      return (var0 < 0 || var0 > 1) && (1000 > var0 || var0 > 1002) ? 1 : var0;
   }

   public static Status zzbk(int var0) {
      int var1 = var0;
      switch(var0) {
      case 1:
         var1 = 13;
      default:
         return new Status(var1);
      }
   }
}
