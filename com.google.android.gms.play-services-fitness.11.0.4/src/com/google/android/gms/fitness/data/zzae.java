package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzae extends com.google.android.gms.common.internal.safeparcel.zza {
   private int zzaku;
   private final Session zzaTe;
   private final DataSet zzaVi;
   public static final Creator CREATOR = new zzaf();

   zzae(int var1, Session var2, DataSet var3) {
      this.zzaku = var1;
      this.zzaTe = var2;
      this.zzaVi = var3;
   }

   public final Session getSession() {
      return this.zzaTe;
   }

   public final DataSet getDataSet() {
      return this.zzaVi;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzae) {
            zzae var3 = (zzae)var1;
            if (zzbe.equal(this.zzaTe, var3.zzaTe) && zzbe.equal(this.zzaVi, var3.zzaVi)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaTe, this.zzaVi});
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("session", this.zzaTe).zzg("dataSet", this.zzaVi).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaTe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaVi, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
