package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbvv;
import com.google.android.gms.internal.zzbvw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataReadRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int NO_LIMIT = 0;
   private final int zzaku;
   private final List zzaTn;
   private final List zzaWr;
   private final long zzagZ;
   private final long zzaTo;
   private final List zzaWw;
   private final List zzaWx;
   private final int zzaTr;
   private final long zzaWy;
   private final DataSource zzaWz;
   private final int zzaWA;
   private final boolean zzaWB;
   private final boolean zzaWC;
   private final zzbvv zzaWD;
   private final List zzaWE;
   private final List zzaWF;
   public static final Creator CREATOR = new zzm();

   DataReadRequest(int var1, List var2, List var3, long var4, long var6, List var8, List var9, int var10, long var11, DataSource var13, int var14, boolean var15, boolean var16, IBinder var17, List var18, List var19) {
      this.zzaku = var1;
      this.zzaTn = var2;
      this.zzaWr = var3;
      this.zzagZ = var4;
      this.zzaTo = var6;
      this.zzaWw = var8;
      this.zzaWx = var9;
      this.zzaTr = var10;
      this.zzaWy = var11;
      this.zzaWz = var13;
      this.zzaWA = var14;
      this.zzaWB = var15;
      this.zzaWC = var16;
      this.zzaWD = var17 == null ? null : zzbvw.zzP(var17);
      this.zzaWE = var18 == null ? Collections.emptyList() : var18;
      this.zzaWF = var19 == null ? Collections.emptyList() : var19;
   }

   private DataReadRequest(DataReadRequest.Builder var1) {
      this(var1.zzaTn, var1.zzaWr, var1.zzagZ, var1.zzaTo, var1.zzaWw, var1.zzaWx, var1.zzaTr, var1.zzaWy, var1.zzaWz, var1.zzaWA, false, var1.zzaWC, (zzbvv)null, var1.zzaWE, var1.zzaWF);
   }

   public DataReadRequest(DataReadRequest var1, zzbvv var2) {
      this(var1.zzaTn, var1.zzaWr, var1.zzagZ, var1.zzaTo, var1.zzaWw, var1.zzaWx, var1.zzaTr, var1.zzaWy, var1.zzaWz, var1.zzaWA, var1.zzaWB, var1.zzaWC, var2, var1.zzaWE, var1.zzaWF);
   }

   private DataReadRequest(List var1, List var2, long var3, long var5, List var7, List var8, int var9, long var10, DataSource var12, int var13, boolean var14, boolean var15, zzbvv var16, List var17, List var18) {
      this(6, var1, var2, var3, var5, var7, var8, var9, var10, var12, var13, var14, var15, var16 == null ? null : var16.asBinder(), var17, var18);
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public List getDataTypes() {
      return this.zzaTn;
   }

   public List getDataSources() {
      return this.zzaWr;
   }

   public List getAggregatedDataTypes() {
      return this.zzaWw;
   }

   public List getAggregatedDataSources() {
      return this.zzaWx;
   }

   public int getBucketType() {
      return this.zzaTr;
   }

   public long getBucketDuration(TimeUnit var1) {
      return var1.convert(this.zzaWy, TimeUnit.MILLISECONDS);
   }

   public DataSource getActivityDataSource() {
      return this.zzaWz;
   }

   public int getLimit() {
      return this.zzaWA;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataReadRequest) {
            DataReadRequest var3 = (DataReadRequest)var1;
            if (this.zzaTn.equals(var3.zzaTn) && this.zzaWr.equals(var3.zzaWr) && this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && this.zzaTr == var3.zzaTr && this.zzaWx.equals(var3.zzaWx) && this.zzaWw.equals(var3.zzaWw) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWz, var3.zzaWz) && this.zzaWy == var3.zzaWy && this.zzaWC == var3.zzaWC && this.zzaWA == var3.zzaWA && this.zzaWB == var3.zzaWB && com.google.android.gms.common.internal.zzbe.equal(this.zzaWD, var3.zzaWD) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWE, var3.zzaWE) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWF, var3.zzaWF)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaTr, this.zzagZ, this.zzaTo});
   }

   public String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("DataReadRequest{");
      Iterator var2;
      DataType var3;
      if (!this.zzaTn.isEmpty()) {
         var2 = this.zzaTn.iterator();

         while(var2.hasNext()) {
            var3 = (DataType)var2.next();
            var1.append(var3.zztO()).append(" ");
         }
      }

      DataSource var4;
      if (!this.zzaWr.isEmpty()) {
         var2 = this.zzaWr.iterator();

         while(var2.hasNext()) {
            var4 = (DataSource)var2.next();
            var1.append(var4.toDebugString()).append(" ");
         }
      }

      if (this.zzaTr != 0) {
         var1.append("bucket by ").append(Bucket.zzaS(this.zzaTr));
         if (this.zzaWy > 0L) {
            var1.append(" >").append(this.zzaWy).append("ms");
         }

         var1.append(": ");
      }

      if (!this.zzaWw.isEmpty()) {
         var2 = this.zzaWw.iterator();

         while(var2.hasNext()) {
            var3 = (DataType)var2.next();
            var1.append(var3.zztO()).append(" ");
         }
      }

      if (!this.zzaWx.isEmpty()) {
         var2 = this.zzaWx.iterator();

         while(var2.hasNext()) {
            var4 = (DataSource)var2.next();
            var1.append(var4.toDebugString()).append(" ");
         }
      }

      var1.append(String.format("(%tF %tT - %tF %tT)", this.zzagZ, this.zzagZ, this.zzaTo, this.zzaTo));
      if (this.zzaWz != null) {
         var1.append("activities: ").append(this.zzaWz.toDebugString());
      }

      if (!this.zzaWF.isEmpty()) {
         var1.append("quality: ");
         var2 = this.zzaWF.iterator();

         while(var2.hasNext()) {
            int var5 = ((Integer)var2.next()).intValue();
            var1.append(DataSource.zzaV(var5)).append(" ");
         }
      }

      if (this.zzaWC) {
         var1.append(" +server");
      }

      var1.append("}");
      return var1.toString();
   }

   public List getFilteredDataQualityStandards() {
      return this.zzaWF;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getDataSources(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getAggregatedDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getAggregatedDataSources(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getBucketType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaWy);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getActivityDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.getLimit());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzaWB);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzaWC);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzaWD == null ? null : this.zzaWD.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.zzaWE, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.getFilteredDataQualityStandards(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataReadRequest(DataReadRequest.Builder var1, zzl var2) {
      this(var1);
   }

   public static class Builder {
      private List zzaTn = new ArrayList();
      private List zzaWr = new ArrayList();
      private List zzaWw = new ArrayList();
      private List zzaWx = new ArrayList();
      private DataSource zzaWz;
      private long zzagZ;
      private long zzaTo;
      private int zzaTr = 0;
      private long zzaWy = 0L;
      private int zzaWA = 0;
      private boolean zzaWB = false;
      private boolean zzaWC = false;
      private final List zzaWE = new ArrayList();
      private final List zzaWF = new ArrayList();

      public DataReadRequest.Builder read(DataSource var1) {
         zzbo.zzb(var1, "Attempting to add a null data source");
         zzbo.zzb(!this.zzaWx.contains(var1), "Cannot add the same data source as aggregated and detailed");
         if (!this.zzaWr.contains(var1)) {
            this.zzaWr.add(var1);
         }

         return this;
      }

      public DataReadRequest.Builder read(DataType var1) {
         zzbo.zzb(var1, "Attempting to use a null data type");
         zzbo.zza(!this.zzaWw.contains(var1), "Cannot add the same data type as aggregated and detailed");
         if (!this.zzaTn.contains(var1)) {
            this.zzaTn.add(var1);
         }

         return this;
      }

      public DataReadRequest.Builder aggregate(DataSource var1, DataType var2) {
         zzbo.zzb(var1, "Attempting to add a null data source");
         zzbo.zza(!this.zzaWr.contains(var1), "Cannot add the same data source for aggregated and detailed");
         DataType var3;
         List var4;
         zzbo.zzb(!(var4 = DataType.getAggregatesForInput(var3 = var1.getDataType())).isEmpty(), "Unsupported input data type specified for aggregation: %s", new Object[]{var3});
         zzbo.zzb(var4.contains(var2), "Invalid output aggregate data type specified: %s -> %s", new Object[]{var3, var2});
         if (!this.zzaWx.contains(var1)) {
            this.zzaWx.add(var1);
         }

         return this;
      }

      public DataReadRequest.Builder aggregate(DataType var1, DataType var2) {
         zzbo.zzb(var1, "Attempting to use a null data type");
         zzbo.zza(!this.zzaTn.contains(var1), "Cannot add the same data type as aggregated and detailed");
         List var3;
         zzbo.zzb(!(var3 = DataType.getAggregatesForInput(var1)).isEmpty(), "Unsupported input data type specified for aggregation: %s", new Object[]{var1});
         zzbo.zzb(var3.contains(var2), "Invalid output aggregate data type specified: %s -> %s", new Object[]{var1, var2});
         if (!this.zzaWw.contains(var1)) {
            this.zzaWw.add(var1);
         }

         return this;
      }

      public DataReadRequest.Builder bucketByTime(int var1, TimeUnit var2) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         this.zzaTr = 1;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder bucketByActivityType(int var1, TimeUnit var2) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         this.zzaTr = 3;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder bucketByActivityType(int var1, TimeUnit var2, DataSource var3) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         zzbo.zzb(var3 != null, "Invalid activity data source specified");
         zzbo.zzb(var3.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[]{var3});
         this.zzaWz = var3;
         this.zzaTr = 3;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder addFilteredDataQualityStandard(int var1) {
         zzbo.zzb(this.zzaWE.isEmpty(), "Cannot add data quality standard filter when filtering by device.");
         this.zzaWF.add(var1);
         return this;
      }

      public DataReadRequest.Builder bucketByActivitySegment(int var1, TimeUnit var2) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         this.zzaTr = 4;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder bucketByActivitySegment(int var1, TimeUnit var2, DataSource var3) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         zzbo.zzb(var3 != null, "Invalid activity data source specified");
         zzbo.zzb(var3.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[]{var3});
         this.zzaWz = var3;
         this.zzaTr = 4;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder bucketBySession(int var1, TimeUnit var2) {
         zzbo.zzb(this.zzaTr == 0, "Bucketing strategy already set to %s", new Object[]{this.zzaTr});
         zzbo.zzb(var1 > 0, "Must specify a valid minimum duration for an activity segment: %d", new Object[]{var1});
         this.zzaTr = 2;
         this.zzaWy = var2.toMillis((long)var1);
         return this;
      }

      public DataReadRequest.Builder setTimeRange(long var1, long var3, TimeUnit var5) {
         this.zzagZ = var5.toMillis(var1);
         this.zzaTo = var5.toMillis(var3);
         return this;
      }

      public DataReadRequest.Builder enableServerQueries() {
         this.zzaWC = true;
         return this;
      }

      public DataReadRequest.Builder setLimit(int var1) {
         zzbo.zzb(var1 > 0, "Invalid limit %d is specified", new Object[]{var1});
         this.zzaWA = var1;
         return this;
      }

      public DataReadRequest build() {
         zzbo.zza(!this.zzaWr.isEmpty() || !this.zzaTn.isEmpty() || !this.zzaWx.isEmpty() || !this.zzaWw.isEmpty(), "Must add at least one data source (aggregated or detailed)");
         zzbo.zza(this.zzagZ > 0L, "Invalid start time: %s", new Object[]{this.zzagZ});
         zzbo.zza(this.zzaTo > 0L && this.zzaTo > this.zzagZ, "Invalid end time: %s", new Object[]{this.zzaTo});
         boolean var1;
         zzbo.zza((var1 = this.zzaWx.isEmpty() && this.zzaWw.isEmpty()) && this.zzaTr == 0 || !var1 && this.zzaTr != 0, "Must specify a valid bucketing strategy while requesting aggregation");
         return new DataReadRequest(this, (zzl)null);
      }
   }
}
