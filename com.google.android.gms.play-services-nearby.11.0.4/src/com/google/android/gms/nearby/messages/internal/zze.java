package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.nearby.messages.Distance;
import java.util.Arrays;
import java.util.Locale;

public final class zze extends com.google.android.gms.common.internal.safeparcel.zza implements Distance {
   public static final Creator CREATOR = new zzf();
   private int zzaku;
   private int accuracy;
   private double zzbyZ;

   zze(int var1, int var2, double var3) {
      this.zzaku = var1;
      this.accuracy = var2;
      this.zzbyZ = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.accuracy);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbyZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zze(int var1, double var2) {
      this(1, 1, Double.NaN);
   }

   public final int getAccuracy() {
      return this.accuracy;
   }

   public final double getMeters() {
      return this.zzbyZ;
   }

   public final String toString() {
      Locale var10000 = Locale.US;
      Object[] var10002 = new Object[]{this.zzbyZ, null};
      String var10005;
      switch(this.accuracy) {
      case 1:
         var10005 = "LOW";
         break;
      default:
         var10005 = "UNKNOWN";
      }

      var10002[1] = var10005;
      return String.format(var10000, "(%.1fm, %s)", var10002);
   }

   public final int compareTo(@NonNull Distance var1) {
      return Double.isNaN(this.getMeters()) && Double.isNaN(var1.getMeters()) ? 0 : Double.compare(this.getMeters(), var1.getMeters());
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zze)) {
         return false;
      } else {
         zze var2 = (zze)var1;
         return this.getAccuracy() == var2.getAccuracy() && this.compareTo((Distance)var2) == 0;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getAccuracy(), this.getMeters()});
   }
}
