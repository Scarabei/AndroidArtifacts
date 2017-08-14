package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public class StreetViewPanoramaLink extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzn();
   public final String panoId;
   public final float bearing;

   public StreetViewPanoramaLink(String var1, float var2) {
      this.panoId = var1;
      this.bearing = ((double)var2 <= 0.0D ? var2 % 360.0F + 360.0F : var2) % 360.0F;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.panoId, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.bearing);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.panoId, this.bearing});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof StreetViewPanoramaLink)) {
         return false;
      } else {
         StreetViewPanoramaLink var2 = (StreetViewPanoramaLink)var1;
         return this.panoId.equals(var2.panoId) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(var2.bearing);
      }
   }

   public String toString() {
      return zzbe.zzt(this).zzg("panoId", this.panoId).zzg("bearing", this.bearing).toString();
   }
}
