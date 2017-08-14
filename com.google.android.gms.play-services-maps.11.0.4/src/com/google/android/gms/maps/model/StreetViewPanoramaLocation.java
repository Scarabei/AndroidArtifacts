package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public class StreetViewPanoramaLocation extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzo();
   public final StreetViewPanoramaLink[] links;
   public final LatLng position;
   public final String panoId;

   public StreetViewPanoramaLocation(StreetViewPanoramaLink[] var1, LatLng var2, String var3) {
      this.links = var1;
      this.position = var2;
      this.panoId = var3;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.links, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.position, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.panoId, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.position, this.panoId});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof StreetViewPanoramaLocation)) {
         return false;
      } else {
         StreetViewPanoramaLocation var2 = (StreetViewPanoramaLocation)var1;
         return this.panoId.equals(var2.panoId) && this.position.equals(var2.position);
      }
   }

   public String toString() {
      return zzbe.zzt(this).zzg("panoId", this.panoId).zzg("position", this.position.toString()).toString();
   }
}
