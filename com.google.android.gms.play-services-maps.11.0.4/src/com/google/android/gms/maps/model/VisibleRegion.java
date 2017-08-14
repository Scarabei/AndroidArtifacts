package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class VisibleRegion extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzu();
   public final LatLng nearLeft;
   public final LatLng nearRight;
   public final LatLng farLeft;
   public final LatLng farRight;
   public final LatLngBounds latLngBounds;

   public VisibleRegion(LatLng var1, LatLng var2, LatLng var3, LatLng var4, LatLngBounds var5) {
      this.nearLeft = var1;
      this.nearRight = var2;
      this.farLeft = var3;
      this.farRight = var4;
      this.latLngBounds = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.nearLeft, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.nearRight, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.farLeft, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.farRight, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.latLngBounds, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof VisibleRegion)) {
         return false;
      } else {
         VisibleRegion var2 = (VisibleRegion)var1;
         return this.nearLeft.equals(var2.nearLeft) && this.nearRight.equals(var2.nearRight) && this.farLeft.equals(var2.farLeft) && this.farRight.equals(var2.farRight) && this.latLngBounds.equals(var2.latLngBounds);
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("nearLeft", this.nearLeft).zzg("nearRight", this.nearRight).zzg("farLeft", this.farLeft).zzg("farRight", this.farRight).zzg("latLngBounds", this.latLngBounds).toString();
   }
}
