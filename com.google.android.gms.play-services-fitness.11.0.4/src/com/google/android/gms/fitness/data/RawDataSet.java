package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.internal.zzbuf;
import java.util.Arrays;
import java.util.List;

@KeepName
public final class RawDataSet extends com.google.android.gms.common.internal.safeparcel.zza {
   private int zzaku;
   public final int zzaUZ;
   private int zzaVd;
   public final List zzaVe;
   public final boolean zzaTs;
   public static final Creator CREATOR = new zzaa();

   public RawDataSet(int var1, int var2, int var3, List var4, boolean var5) {
      this.zzaku = var1;
      this.zzaUZ = var2;
      this.zzaVd = var3;
      this.zzaVe = var4;
      this.zzaTs = var5;
   }

   public RawDataSet(DataSet var1, List var2, List var3) {
      this.zzaku = 3;
      this.zzaVe = var1.zzA(var2);
      this.zzaTs = var1.zztE();
      this.zzaUZ = zzbuf.zza(var1.getDataSource(), var2);
      this.zzaVd = zzbuf.zza(var1.getDataType(), var3);
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof RawDataSet) {
            RawDataSet var3 = (RawDataSet)var1;
            if (this.zzaUZ == var3.zzaUZ && this.zzaTs == var3.zzaTs && zzbe.equal(this.zzaVe, var3.zzaVe)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUZ});
   }

   public final String toString() {
      return String.format("RawDataSet{%s@[%s]}", this.zzaUZ, this.zzaVe);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaUZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaVd);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaVe, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaTs);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
