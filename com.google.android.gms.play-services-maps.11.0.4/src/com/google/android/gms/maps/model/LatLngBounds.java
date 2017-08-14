package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public final class LatLngBounds extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zze();
   public final LatLng southwest;
   public final LatLng northeast;

   public LatLngBounds(LatLng var1, LatLng var2) {
      zzbo.zzb(var1, "null southwest");
      zzbo.zzb(var2, "null northeast");
      zzbo.zzb(var2.latitude >= var1.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{var1.latitude, var2.latitude});
      this.southwest = var1;
      this.northeast = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.southwest, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.northeast, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static LatLngBounds.Builder builder() {
      return new LatLngBounds.Builder();
   }

   public final boolean contains(LatLng var1) {
      double var4 = var1.latitude;
      return this.southwest.latitude <= var4 && var4 <= this.northeast.latitude && this.zzg(var1.longitude);
   }

   public final LatLngBounds including(LatLng var1) {
      double var3 = Math.min(this.southwest.latitude, var1.latitude);
      double var5 = Math.max(this.northeast.latitude, var1.latitude);
      double var7 = this.northeast.longitude;
      double var9 = this.southwest.longitude;
      double var11 = var1.longitude;
      if (!this.zzg(var11)) {
         if (zza(var9, var11) < zzb(var7, var11)) {
            var9 = var11;
         } else {
            var7 = var11;
         }
      }

      return new LatLngBounds(new LatLng(var3, var9), new LatLng(var5, var7));
   }

   public final LatLng getCenter() {
      double var1 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
      double var3 = this.northeast.longitude;
      double var5 = this.southwest.longitude;
      double var7;
      if (this.southwest.longitude <= var3) {
         var7 = (var3 + var5) / 2.0D;
      } else {
         var7 = (var3 + 360.0D + var5) / 2.0D;
      }

      return new LatLng(var1, var7);
   }

   private static double zza(double var0, double var2) {
      return (var0 - var2 + 360.0D) % 360.0D;
   }

   private static double zzb(double var0, double var2) {
      return (var2 - var0 + 360.0D) % 360.0D;
   }

   private final boolean zzg(double var1) {
      if (this.southwest.longitude <= this.northeast.longitude) {
         return this.southwest.longitude <= var1 && var1 <= this.northeast.longitude;
      } else {
         return this.southwest.longitude <= var1 || var1 <= this.northeast.longitude;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.southwest, this.northeast});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LatLngBounds)) {
         return false;
      } else {
         LatLngBounds var2 = (LatLngBounds)var1;
         return this.southwest.equals(var2.southwest) && this.northeast.equals(var2.northeast);
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
   }

   public static LatLngBounds createFromAttributes(Context var0, AttributeSet var1) {
      if (var0 != null && var1 != null) {
         TypedArray var2 = var0.getResources().obtainAttributes(var1, styleable.MapAttrs);
         Float var3 = null;
         Float var4 = null;
         if (var2.hasValue(styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
            var3 = var2.getFloat(styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0F);
         }

         if (var2.hasValue(styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
            var4 = var2.getFloat(styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0F);
         }

         Float var5 = null;
         Float var6 = null;
         if (var2.hasValue(styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
            var5 = var2.getFloat(styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0F);
         }

         if (var2.hasValue(styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
            var6 = var2.getFloat(styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0F);
         }

         if (var3 != null && var4 != null && var5 != null && var6 != null) {
            LatLng var7 = new LatLng((double)var3.floatValue(), (double)var4.floatValue());
            LatLng var8 = new LatLng((double)var5.floatValue(), (double)var6.floatValue());
            return new LatLngBounds(var7, var8);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public static final class Builder {
      private double zzbny = Double.POSITIVE_INFINITY;
      private double zzbnz = Double.NEGATIVE_INFINITY;
      private double zzbnA = Double.NaN;
      private double zzbnB = Double.NaN;

      public final LatLngBounds.Builder include(LatLng var1) {
         this.zzbny = Math.min(this.zzbny, var1.latitude);
         this.zzbnz = Math.max(this.zzbnz, var1.latitude);
         double var2 = var1.longitude;
         if (Double.isNaN(this.zzbnA)) {
            this.zzbnA = var2;
         } else {
            if (this.zzbnA <= this.zzbnB ? this.zzbnA <= var2 && var2 <= this.zzbnB : this.zzbnA <= var2 || var2 <= this.zzbnB) {
               return this;
            }

            if (LatLngBounds.zza(this.zzbnA, var2) < LatLngBounds.zzb(this.zzbnB, var2)) {
               this.zzbnA = var2;
               return this;
            }
         }

         this.zzbnB = var2;
         return this;
      }

      public final LatLngBounds build() {
         zzbo.zza(!Double.isNaN(this.zzbnA), "no included points");
         return new LatLngBounds(new LatLng(this.zzbny, this.zzbnA), new LatLng(this.zzbnz, this.zzbnB));
      }
   }
}
