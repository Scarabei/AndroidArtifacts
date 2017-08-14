package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public final class LatLng extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzf();
   public final double latitude;
   public final double longitude;

   public LatLng(double var1, double var3) {
      if (-180.0D <= var3 && var3 < 180.0D) {
         this.longitude = var3;
      } else {
         this.longitude = ((var3 - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D;
      }

      this.latitude = Math.max(-90.0D, Math.min(90.0D, var1));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.latitude);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.longitude);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      long var2 = Double.doubleToLongBits(this.latitude);
      int var1 = 31 + (int)(var2 ^ var2 >>> 32);
      var2 = Double.doubleToLongBits(this.longitude);
      return var1 * 31 + (int)(var2 ^ var2 >>> 32);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LatLng)) {
         return false;
      } else {
         LatLng var2 = (LatLng)var1;
         return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(var2.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(var2.longitude);
      }
   }

   public final String toString() {
      double var1 = this.latitude;
      double var3 = this.longitude;
      return (new StringBuilder(60)).append("lat/lng: (").append(var1).append(",").append(var3).append(")").toString();
   }
}
