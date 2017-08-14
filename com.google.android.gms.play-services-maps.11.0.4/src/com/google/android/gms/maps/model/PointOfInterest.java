package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class PointOfInterest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzj();
   public final LatLng latLng;
   public final String placeId;
   public final String name;

   public PointOfInterest(LatLng var1, String var2, String var3) {
      this.latLng = var1;
      this.placeId = var2;
      this.name = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.latLng, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.placeId, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.name, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
