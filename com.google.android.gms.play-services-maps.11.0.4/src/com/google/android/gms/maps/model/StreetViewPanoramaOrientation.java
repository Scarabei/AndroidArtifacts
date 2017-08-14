package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class StreetViewPanoramaOrientation extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzp();
   public final float tilt;
   public final float bearing;

   public StreetViewPanoramaOrientation(float var1, float var2) {
      zzbo.zzb(-90.0F <= var1 && var1 <= 90.0F, (new StringBuilder(62)).append("Tilt needs to be between -90 and 90 inclusive: ").append(var1).toString());
      this.tilt = var1 + 0.0F;
      this.bearing = ((double)var2 <= 0.0D ? var2 % 360.0F + 360.0F : var2) % 360.0F;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.tilt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.bearing);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.tilt, this.bearing});
   }

   public static StreetViewPanoramaOrientation.Builder builder() {
      return new StreetViewPanoramaOrientation.Builder();
   }

   public static StreetViewPanoramaOrientation.Builder builder(StreetViewPanoramaOrientation var0) {
      return new StreetViewPanoramaOrientation.Builder(var0);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof StreetViewPanoramaOrientation)) {
         return false;
      } else {
         StreetViewPanoramaOrientation var2 = (StreetViewPanoramaOrientation)var1;
         return Float.floatToIntBits(this.tilt) == Float.floatToIntBits(var2.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(var2.bearing);
      }
   }

   public String toString() {
      return zzbe.zzt(this).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
   }

   public static final class Builder {
      public float bearing;
      public float tilt;

      public Builder() {
      }

      public Builder(StreetViewPanoramaOrientation var1) {
         this.bearing = var1.bearing;
         this.tilt = var1.tilt;
      }

      public final StreetViewPanoramaOrientation.Builder tilt(float var1) {
         this.tilt = var1;
         return this;
      }

      public final StreetViewPanoramaOrientation.Builder bearing(float var1) {
         this.bearing = var1;
         return this;
      }

      public final StreetViewPanoramaOrientation build() {
         return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
      }
   }
}
