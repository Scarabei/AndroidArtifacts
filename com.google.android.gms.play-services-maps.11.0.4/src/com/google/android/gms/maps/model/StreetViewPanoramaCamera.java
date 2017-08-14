package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class StreetViewPanoramaCamera extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzm();
   public final float zoom;
   public final float tilt;
   public final float bearing;
   private StreetViewPanoramaOrientation zzbnW;

   public StreetViewPanoramaCamera(float var1, float var2, float var3) {
      zzbo.zzb(-90.0F <= var2 && var2 <= 90.0F, (new StringBuilder(62)).append("Tilt needs to be between -90 and 90 inclusive: ").append(var2).toString());
      this.zoom = (double)var1 <= 0.0D ? 0.0F : var1;
      this.tilt = var2 + 0.0F;
      this.bearing = ((double)var3 <= 0.0D ? var3 % 360.0F + 360.0F : var3) % 360.0F;
      this.zzbnW = (new StreetViewPanoramaOrientation.Builder()).tilt(var2).bearing(var3).build();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zoom);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.tilt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.bearing);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zoom, this.tilt, this.bearing});
   }

   public static StreetViewPanoramaCamera.Builder builder() {
      return new StreetViewPanoramaCamera.Builder();
   }

   public static StreetViewPanoramaCamera.Builder builder(StreetViewPanoramaCamera var0) {
      return new StreetViewPanoramaCamera.Builder(var0);
   }

   public StreetViewPanoramaOrientation getOrientation() {
      return this.zzbnW;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof StreetViewPanoramaCamera)) {
         return false;
      } else {
         StreetViewPanoramaCamera var2 = (StreetViewPanoramaCamera)var1;
         return Float.floatToIntBits(this.zoom) == Float.floatToIntBits(var2.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(var2.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(var2.bearing);
      }
   }

   public String toString() {
      return zzbe.zzt(this).zzg("zoom", this.zoom).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
   }

   public static final class Builder {
      public float bearing;
      public float tilt;
      public float zoom;

      public Builder() {
      }

      public Builder(StreetViewPanoramaCamera var1) {
         this.zoom = var1.zoom;
         this.bearing = var1.bearing;
         this.tilt = var1.tilt;
      }

      public final StreetViewPanoramaCamera.Builder zoom(float var1) {
         this.zoom = var1;
         return this;
      }

      public final StreetViewPanoramaCamera.Builder orientation(StreetViewPanoramaOrientation var1) {
         this.tilt = var1.tilt;
         this.bearing = var1.bearing;
         return this;
      }

      public final StreetViewPanoramaCamera.Builder tilt(float var1) {
         this.tilt = var1;
         return this;
      }

      public final StreetViewPanoramaCamera.Builder bearing(float var1) {
         this.bearing = var1;
         return this;
      }

      public final StreetViewPanoramaCamera build() {
         return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
      }
   }
}
