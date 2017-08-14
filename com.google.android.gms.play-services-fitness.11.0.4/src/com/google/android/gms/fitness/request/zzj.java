package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;

public final class zzj extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final DataSet zzaVi;
   private final zzbxg zzaWo;
   private final boolean zzaWv;
   public static final Creator CREATOR = new zzk();

   zzj(int var1, DataSet var2, IBinder var3, boolean var4) {
      this.zzaku = var1;
      this.zzaVi = var2;
      this.zzaWo = zzbxh.zzW(var3);
      this.zzaWv = var4;
   }

   public zzj(DataSet var1, zzbxg var2, boolean var3) {
      this.zzaku = 4;
      this.zzaVi = var1;
      this.zzaWo = var2;
      this.zzaWv = var3;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzj) {
            zzj var2 = (zzj)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaVi, var2.zzaVi)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaVi});
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataSet", this.zzaVi).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaVi, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaWv);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
