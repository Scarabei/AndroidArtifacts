package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class DataUpdateRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final long zzagZ;
   private final long zzaTo;
   private final DataSet zzaVi;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzy();

   DataUpdateRequest(int var1, long var2, long var4, DataSet var6, IBinder var7) {
      this.zzaku = var1;
      this.zzagZ = var2;
      this.zzaTo = var4;
      this.zzaVi = var6;
      this.zzaWo = zzbxh.zzW(var7);
   }

   private DataUpdateRequest(DataUpdateRequest.Builder var1) {
      this(var1.zzagZ, var1.zzaTo, var1.zzaVi, (IBinder)null);
   }

   private DataUpdateRequest(long var1, long var3, DataSet var5, IBinder var6) {
      this.zzaku = 1;
      this.zzagZ = var1;
      this.zzaTo = var3;
      this.zzaVi = var5;
      this.zzaWo = zzbxh.zzW(var6);
   }

   public DataUpdateRequest(DataUpdateRequest var1, IBinder var2) {
      this(var1.zzagZ, var1.zzaTo, var1.getDataSet(), var2);
   }

   public final long zzmc() {
      return this.zzagZ;
   }

   public final long zztU() {
      return this.zzaTo;
   }

   public DataSet getDataSet() {
      return this.zzaVi;
   }

   public IBinder getCallbackBinder() {
      return this.zzaWo == null ? null : this.zzaWo.asBinder();
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof DataUpdateRequest) {
            DataUpdateRequest var3 = (DataUpdateRequest)var1;
            if (this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && com.google.android.gms.common.internal.zzbe.equal(this.zzaVi, var3.zzaVi)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzagZ, this.zzaTo, this.zzaVi});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("startTimeMillis", this.zzagZ).zzg("endTimeMillis", this.zzaTo).zzg("dataSet", this.zzaVi).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getDataSet(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getCallbackBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataUpdateRequest(DataUpdateRequest.Builder var1, zzx var2) {
      this(var1);
   }

   public static class Builder {
      private long zzagZ;
      private long zzaTo;
      private DataSet zzaVi;

      public DataUpdateRequest.Builder setTimeInterval(long var1, long var3, TimeUnit var5) {
         zzbo.zzb(var1 > 0L, "Invalid start time :%d", new Object[]{var1});
         zzbo.zzb(var3 >= var1, "Invalid end time :%d", new Object[]{var3});
         this.zzagZ = var5.toMillis(var1);
         this.zzaTo = var5.toMillis(var3);
         return this;
      }

      public DataUpdateRequest.Builder setDataSet(DataSet var1) {
         zzbo.zzb(var1, "Must set the data set");
         this.zzaVi = var1;
         return this;
      }

      public DataUpdateRequest build() {
         DataUpdateRequest.Builder var1 = this;
         zzbo.zza(this.zzagZ, "Must set a non-zero value for startTimeMillis/startTime");
         zzbo.zza(this.zzaTo, "Must set a non-zero value for endTimeMillis/endTime");
         zzbo.zzb(this.zzaVi, "Must set the data set");
         Iterator var2 = this.zzaVi.getDataPoints().iterator();

         while(var2.hasNext()) {
            DataPoint var3;
            long var4 = (var3 = (DataPoint)var2.next()).getStartTime(TimeUnit.MILLISECONDS);
            long var6 = var3.getEndTime(TimeUnit.MILLISECONDS);
            zzbo.zza(var4 <= var6 && (var4 == 0L || var4 >= var1.zzagZ) && (var4 == 0L || var4 <= var1.zzaTo) && var6 <= var1.zzaTo && var6 >= var1.zzagZ, "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", new Object[]{var4, var6, var1.zzagZ, var1.zzaTo});
         }

         return new DataUpdateRequest(this, (zzx)null);
      }
   }
}
