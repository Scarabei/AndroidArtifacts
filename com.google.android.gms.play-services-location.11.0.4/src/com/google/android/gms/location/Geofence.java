package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzcdr;

public interface Geofence {
   int GEOFENCE_TRANSITION_ENTER = 1;
   int GEOFENCE_TRANSITION_EXIT = 2;
   int GEOFENCE_TRANSITION_DWELL = 4;
   long NEVER_EXPIRE = -1L;

   String getRequestId();

   public static final class Builder {
      private String zzQx = null;
      private int zzbhF = 0;
      private long zzbhG = Long.MIN_VALUE;
      private short zzbhH = -1;
      private double zzbhI;
      private double zzbhJ;
      private float zzbhK;
      private int zzbhL = 0;
      private int zzbhM = -1;

      public final Geofence.Builder setRequestId(String var1) {
         this.zzQx = var1;
         return this;
      }

      public final Geofence.Builder setTransitionTypes(int var1) {
         this.zzbhF = var1;
         return this;
      }

      public final Geofence.Builder setExpirationDuration(long var1) {
         if (var1 < 0L) {
            this.zzbhG = -1L;
         } else {
            this.zzbhG = SystemClock.elapsedRealtime() + var1;
         }

         return this;
      }

      public final Geofence.Builder setCircularRegion(double var1, double var3, float var5) {
         this.zzbhH = 1;
         this.zzbhI = var1;
         this.zzbhJ = var3;
         this.zzbhK = var5;
         return this;
      }

      public final Geofence.Builder setNotificationResponsiveness(int var1) {
         this.zzbhL = var1;
         return this;
      }

      public final Geofence.Builder setLoiteringDelay(int var1) {
         this.zzbhM = var1;
         return this;
      }

      public final Geofence build() {
         if (this.zzQx == null) {
            throw new IllegalArgumentException("Request ID not set.");
         } else if (this.zzbhF == 0) {
            throw new IllegalArgumentException("Transitions types not set.");
         } else if ((this.zzbhF & 4) != 0 && this.zzbhM < 0) {
            throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
         } else if (this.zzbhG == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Expiration not set.");
         } else if (this.zzbhH == -1) {
            throw new IllegalArgumentException("Geofence region not set.");
         } else if (this.zzbhL < 0) {
            throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
         } else {
            return new zzcdr(this.zzQx, this.zzbhF, (short)1, this.zzbhI, this.zzbhJ, this.zzbhK, this.zzbhG, this.zzbhL, this.zzbhM);
         }
      }
   }
}
