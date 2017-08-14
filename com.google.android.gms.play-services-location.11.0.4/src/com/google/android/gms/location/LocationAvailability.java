package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Arrays;

public final class LocationAvailability extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   /** @deprecated */
   @Deprecated
   private int zzbhS;
   /** @deprecated */
   @Deprecated
   private int zzbhT;
   private long zzbhU;
   private int zzbhV;
   private zzy[] zzbhW;
   public static final Creator CREATOR = new zzp();

   LocationAvailability(int var1, int var2, int var3, long var4, zzy[] var6) {
      this.zzbhV = var1;
      this.zzbhS = var2;
      this.zzbhT = var3;
      this.zzbhU = var4;
      this.zzbhW = var6;
   }

   public final boolean isLocationAvailable() {
      return this.zzbhV < 1000;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbhS);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbhT);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbhU);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbhV);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbhW, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbhV, this.zzbhS, this.zzbhT, this.zzbhU, this.zzbhW});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         LocationAvailability var2 = (LocationAvailability)var1;
         return this.zzbhS == var2.zzbhS && this.zzbhT == var2.zzbhT && this.zzbhU == var2.zzbhU && this.zzbhV == var2.zzbhV && Arrays.equals(this.zzbhW, var2.zzbhW);
      } else {
         return false;
      }
   }

   public final String toString() {
      boolean var1 = this.isLocationAvailable();
      return (new StringBuilder(48)).append("LocationAvailability[isLocationAvailable: ").append(var1).append("]").toString();
   }

   public static boolean hasLocationAvailability(Intent var0) {
      return var0 == null ? false : var0.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
   }

   public static LocationAvailability extractLocationAvailability(Intent var0) {
      return !hasLocationAvailability(var0) ? null : (LocationAvailability)var0.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
   }
}
