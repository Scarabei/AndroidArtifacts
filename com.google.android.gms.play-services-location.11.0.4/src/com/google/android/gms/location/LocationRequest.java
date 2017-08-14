package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;

public final class LocationRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int PRIORITY_HIGH_ACCURACY = 100;
   public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
   public static final int PRIORITY_LOW_POWER = 104;
   public static final int PRIORITY_NO_POWER = 105;
   private int mPriority;
   private long zzbhX;
   private long zzbhY;
   private boolean zzaXd;
   private long zzbhG;
   private int zzbhZ;
   private float zzbia;
   private long zzbib;
   public static final Creator CREATOR = new zzq();

   public static LocationRequest create() {
      return new LocationRequest();
   }

   public LocationRequest() {
      this.mPriority = 102;
      this.zzbhX = 3600000L;
      this.zzbhY = 600000L;
      this.zzaXd = false;
      this.zzbhG = Long.MAX_VALUE;
      this.zzbhZ = Integer.MAX_VALUE;
      this.zzbia = 0.0F;
      this.zzbib = 0L;
   }

   public final LocationRequest setPriority(int var1) {
      switch(var1) {
      case 100:
      case 102:
      case 104:
      case 105:
         this.mPriority = var1;
         return this;
      case 101:
      case 103:
      default:
         throw new IllegalArgumentException((new StringBuilder(28)).append("invalid quality: ").append(var1).toString());
      }
   }

   public final int getPriority() {
      return this.mPriority;
   }

   public final LocationRequest setInterval(long var1) {
      zzI(var1);
      this.zzbhX = var1;
      if (!this.zzaXd) {
         this.zzbhY = (long)((double)this.zzbhX / 6.0D);
      }

      return this;
   }

   public final long getInterval() {
      return this.zzbhX;
   }

   public final LocationRequest setMaxWaitTime(long var1) {
      zzI(var1);
      this.zzbib = var1;
      return this;
   }

   public final long getMaxWaitTime() {
      long var1 = this.zzbib;
      if (this.zzbib < this.zzbhX) {
         var1 = this.zzbhX;
      }

      return var1;
   }

   public final LocationRequest setFastestInterval(long var1) {
      zzI(var1);
      this.zzaXd = true;
      this.zzbhY = var1;
      return this;
   }

   public final long getFastestInterval() {
      return this.zzbhY;
   }

   public final LocationRequest setExpirationDuration(long var1) {
      long var3 = SystemClock.elapsedRealtime();
      if (var1 > Long.MAX_VALUE - var3) {
         this.zzbhG = Long.MAX_VALUE;
      } else {
         this.zzbhG = var1 + var3;
      }

      if (this.zzbhG < 0L) {
         this.zzbhG = 0L;
      }

      return this;
   }

   public final LocationRequest setExpirationTime(long var1) {
      this.zzbhG = var1;
      if (this.zzbhG < 0L) {
         this.zzbhG = 0L;
      }

      return this;
   }

   public final long getExpirationTime() {
      return this.zzbhG;
   }

   public final LocationRequest setNumUpdates(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException((new StringBuilder(31)).append("invalid numUpdates: ").append(var1).toString());
      } else {
         this.zzbhZ = var1;
         return this;
      }
   }

   public final int getNumUpdates() {
      return this.zzbhZ;
   }

   public final LocationRequest setSmallestDisplacement(float var1) {
      if (var1 < 0.0F) {
         throw new IllegalArgumentException((new StringBuilder(37)).append("invalid displacement: ").append(var1).toString());
      } else {
         this.zzbia = var1;
         return this;
      }
   }

   public final float getSmallestDisplacement() {
      return this.zzbia;
   }

   private static void zzI(long var0) {
      if (var0 < 0L) {
         throw new IllegalArgumentException((new StringBuilder(38)).append("invalid interval: ").append(var0).toString());
      }
   }

   LocationRequest(int var1, long var2, long var4, boolean var6, long var7, int var9, float var10, long var11) {
      this.mPriority = var1;
      this.zzbhX = var2;
      this.zzbhY = var4;
      this.zzaXd = var6;
      this.zzbhG = var7;
      this.zzbhZ = var9;
      this.zzbia = var10;
      this.zzbib = var11;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.mPriority);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbhX);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbhY);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaXd);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbhG);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzbhZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbia);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbib);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      StringBuilder var1;
      StringBuilder var10000 = (var1 = new StringBuilder()).append("Request[");
      String var10001;
      switch(this.mPriority) {
      case 100:
         var10001 = "PRIORITY_HIGH_ACCURACY";
         break;
      case 101:
      case 103:
      default:
         var10001 = "???";
         break;
      case 102:
         var10001 = "PRIORITY_BALANCED_POWER_ACCURACY";
         break;
      case 104:
         var10001 = "PRIORITY_LOW_POWER";
         break;
      case 105:
         var10001 = "PRIORITY_NO_POWER";
      }

      var10000.append(var10001);
      if (this.mPriority != 105) {
         var1.append(" requested=");
         var1.append(this.zzbhX).append("ms");
      }

      var1.append(" fastest=");
      var1.append(this.zzbhY).append("ms");
      if (this.zzbib > this.zzbhX) {
         var1.append(" maxWait=");
         var1.append(this.zzbib).append("ms");
      }

      if (this.zzbia > 0.0F) {
         var1.append(" smallestDisplacement=");
         var1.append(this.zzbia).append("m");
      }

      if (this.zzbhG != Long.MAX_VALUE) {
         long var2 = this.zzbhG - SystemClock.elapsedRealtime();
         var1.append(" expireIn=");
         var1.append(var2).append("ms");
      }

      if (this.zzbhZ != Integer.MAX_VALUE) {
         var1.append(" num=").append(this.zzbhZ);
      }

      var1.append(']');
      return var1.toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mPriority, this.zzbhX, this.zzbia, this.zzbib});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LocationRequest)) {
         return false;
      } else {
         LocationRequest var2 = (LocationRequest)var1;
         return this.mPriority == var2.mPriority && this.zzbhX == var2.zzbhX && this.zzbhY == var2.zzbhY && this.zzaXd == var2.zzaXd && this.zzbhG == var2.zzbhG && this.zzbhZ == var2.zzbhZ && this.zzbia == var2.zzbia && this.getMaxWaitTime() == var2.getMaxWaitTime();
      }
   }
}
