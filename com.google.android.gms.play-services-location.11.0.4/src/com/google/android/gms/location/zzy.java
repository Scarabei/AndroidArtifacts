package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzy extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzz();
   private int zzbit;
   private int zzbiu;
   private long zzbiv;
   private long zzbiw;

   zzy(int var1, int var2, long var3, long var5) {
      this.zzbit = var1;
      this.zzbiu = var2;
      this.zzbiv = var3;
      this.zzbiw = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbit);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbiu);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbiv);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbiw);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbiu, this.zzbit, this.zzbiw, this.zzbiv});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzy var2 = (zzy)var1;
         return this.zzbit == var2.zzbit && this.zzbiu == var2.zzbiu && this.zzbiv == var2.zzbiv && this.zzbiw == var2.zzbiw;
      } else {
         return false;
      }
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder("NetworkLocationStatus:")).append(" Wifi status: ").append(this.zzbit).append(" Cell status: ").append(this.zzbiu).append(" elapsed time NS: ").append(this.zzbiw).append(" system time ms: ").append(this.zzbiv);
      return var1.toString();
   }
}
