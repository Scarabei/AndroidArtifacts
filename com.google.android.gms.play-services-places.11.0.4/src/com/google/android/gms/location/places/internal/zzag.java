package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.Arrays;

public final class zzag extends com.google.android.gms.common.internal.safeparcel.zza implements PlaceLikelihood {
   public static final Creator CREATOR = new zzah();
   private PlaceEntity zzbkI;
   private float zzbkJ;

   zzag(PlaceEntity var1, float var2) {
      this.zzbkI = var1;
      this.zzbkJ = var2;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzag)) {
         return false;
      } else {
         zzag var2 = (zzag)var1;
         return this.zzbkI.equals(var2.zzbkI) && this.zzbkJ == var2.zzbkJ;
      }
   }

   public final float getLikelihood() {
      return this.zzbkJ;
   }

   public final Place getPlace() {
      return this.zzbkI;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbkI, this.zzbkJ});
   }

   public final boolean isDataValid() {
      return true;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("place", this.zzbkI).zzg("likelihood", this.zzbkJ).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbkI, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbkJ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
