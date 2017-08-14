package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class Strategy extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzf();
   public static final Strategy P2P_CLUSTER = new Strategy(1, 3);
   public static final Strategy P2P_STAR = new Strategy(1, 2);
   private final int zzbwC;
   private final int zzbwD;

   Strategy(int var1, int var2) {
      this.zzbwC = var1;
      this.zzbwD = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzbwC);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbwD);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwC, this.zzbwD});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Strategy)) {
         return false;
      } else {
         Strategy var2 = (Strategy)var1;
         return this.zzbwC == var2.zzbwC && this.zzbwD == var2.zzbwD;
      }
   }

   public final String toString() {
      return String.format("Strategy(%s){connectionType=%d, topology=%d}", P2P_CLUSTER.equals(this) ? "P2P_CLUSTER" : (P2P_STAR.equals(this) ? "P2P_STAR" : "UNKNOWN"), this.zzbwC, this.zzbwD);
   }
}
