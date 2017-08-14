package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.Arrays;

public class DailyTotalResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final Status mStatus;
   private final DataSet zzaVi;
   public static final Creator CREATOR = new zzb();

   DailyTotalResult(int var1, Status var2, DataSet var3) {
      this.zzaku = var1;
      this.mStatus = var2;
      this.zzaVi = var3;
   }

   private DailyTotalResult(DataSet var1, Status var2) {
      this.zzaku = 1;
      this.mStatus = var2;
      this.zzaVi = var1;
   }

   public static DailyTotalResult zza(Status var0, DataType var1) {
      DataSource var2 = (new DataSource.Builder()).setDataType(var1).setType(1).build();
      return new DailyTotalResult(DataSet.create(var2), var0);
   }

   @Nullable
   public DataSet getTotal() {
      return this.zzaVi;
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DailyTotalResult) {
            DailyTotalResult var3 = (DailyTotalResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaVi, var3.zzaVi)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaVi});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("dataPoint", this.zzaVi).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getTotal(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
