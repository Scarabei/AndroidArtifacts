package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.fitness.data.DataType;
import java.util.Arrays;

public class DataTypeResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final Status mStatus;
   private final DataType zzaUe;
   public static final Creator CREATOR = new zze();

   DataTypeResult(int var1, Status var2, DataType var3) {
      this.zzaku = var1;
      this.mStatus = var2;
      this.zzaUe = var3;
   }

   private DataTypeResult(Status var1, DataType var2) {
      this.zzaku = 2;
      this.mStatus = var1;
      this.zzaUe = null;
   }

   public static DataTypeResult zzC(Status var0) {
      return new DataTypeResult(var0, (DataType)null);
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public DataType getDataType() {
      return this.zzaUe;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataTypeResult) {
            DataTypeResult var3 = (DataTypeResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaUe, var3.zzaUe)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaUe});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("dataType", this.zzaUe).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
