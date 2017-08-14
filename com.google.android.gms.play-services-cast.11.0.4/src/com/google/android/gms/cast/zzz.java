package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzz extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzaa();
   private int zzapU;

   zzz(int var1) {
      this.zzapU = var1;
   }

   public zzz() {
      this(0);
   }

   public final String toString() {
      String var1;
      switch(this.zzapU) {
      case 0:
         var1 = "STRONG";
         break;
      case 2:
         var1 = "INVISIBLE";
         break;
      default:
         var1 = "UNKNOWN";
      }

      return String.format("joinOptions(connectionType=%s)", var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzapU);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzz)) {
         return false;
      } else {
         zzz var2 = (zzz)var1;
         return this.zzapU == var2.zzapU;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzapU});
   }
}
