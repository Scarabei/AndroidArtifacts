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

public final class CameraPosition extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zza();
   public final LatLng target;
   public final float zoom;
   public final float tilt;
   public final float bearing;

   public CameraPosition(LatLng var1, float var2, float var3, float var4) {
      zzbo.zzb(var1, "null camera target");
      zzbo.zzb(0.0F <= var3 && var3 <= 90.0F, "Tilt needs to be between 0 and 90 inclusive: %s", new Object[]{var3});
      this.target = var1;
      this.zoom = var2;
      this.tilt = var3 + 0.0F;
      this.bearing = ((double)var4 <= 0.0D ? var4 % 360.0F + 360.0F : var4) % 360.0F;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.target, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zoom);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.tilt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.bearing);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.target, this.zoom, this.tilt, this.bearing});
   }

   public static final CameraPosition fromLatLngZoom(LatLng var0, float var1) {
      return new CameraPosition(var0, var1, 0.0F, 0.0F);
   }

   public static CameraPosition.Builder builder() {
      return new CameraPosition.Builder();
   }

   public static CameraPosition.Builder builder(CameraPosition var0) {
      return new CameraPosition.Builder(var0);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof CameraPosition)) {
         return false;
      } else {
         CameraPosition var2 = (CameraPosition)var1;
         return this.target.equals(var2.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(var2.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(var2.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(var2.bearing);
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("target", this.target).zzg("zoom", this.zoom).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
   }

   public static CameraPosition createFromAttributes(Context var0, AttributeSet var1) {
      if (var1 == null) {
         return null;
      } else {
         TypedArray var2 = var0.getResources().obtainAttributes(var1, styleable.MapAttrs);
         float var3 = 0.0F;
         float var4 = 0.0F;
         if (var2.hasValue(styleable.MapAttrs_cameraTargetLat)) {
            var3 = var2.getFloat(styleable.MapAttrs_cameraTargetLat, 0.0F);
         }

         if (var2.hasValue(styleable.MapAttrs_cameraTargetLng)) {
            var4 = var2.getFloat(styleable.MapAttrs_cameraTargetLng, 0.0F);
         }

         LatLng var5 = new LatLng((double)var3, (double)var4);
         CameraPosition.Builder var6;
         (var6 = builder()).target(var5);
         if (var2.hasValue(styleable.MapAttrs_cameraZoom)) {
            var6.zoom(var2.getFloat(styleable.MapAttrs_cameraZoom, 0.0F));
         }

         if (var2.hasValue(styleable.MapAttrs_cameraBearing)) {
            var6.bearing(var2.getFloat(styleable.MapAttrs_cameraBearing, 0.0F));
         }

         if (var2.hasValue(styleable.MapAttrs_cameraTilt)) {
            var6.tilt(var2.getFloat(styleable.MapAttrs_cameraTilt, 0.0F));
         }

         return var6.build();
      }
   }

   public static final class Builder {
      private LatLng zzbnc;
      private float zzbnd;
      private float zzbne;
      private float zzbnf;

      public Builder() {
      }

      public Builder(CameraPosition var1) {
         this.zzbnc = var1.target;
         this.zzbnd = var1.zoom;
         this.zzbne = var1.tilt;
         this.zzbnf = var1.bearing;
      }

      public final CameraPosition.Builder target(LatLng var1) {
         this.zzbnc = var1;
         return this;
      }

      public final CameraPosition.Builder zoom(float var1) {
         this.zzbnd = var1;
         return this;
      }

      public final CameraPosition.Builder tilt(float var1) {
         this.zzbne = var1;
         return this;
      }

      public final CameraPosition.Builder bearing(float var1) {
         this.zzbnf = var1;
         return this;
      }

      public final CameraPosition build() {
         return new CameraPosition(this.zzbnc, this.zzbnd, this.zzbne, this.zzbnf);
      }
   }
}
