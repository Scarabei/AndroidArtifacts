package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbue;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionInsertRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int versionCode;
   private final Session zzaXe;
   private final List zzaXf;
   private final List zzaXg;
   private final zzbxg zzaXh;
   public static final Creator CREATOR = new zzat();

   SessionInsertRequest(int var1, Session var2, List var3, List var4, IBinder var5) {
      this.versionCode = var1;
      this.zzaXe = var2;
      this.zzaXf = Collections.unmodifiableList(var3);
      this.zzaXg = Collections.unmodifiableList(var4);
      this.zzaXh = zzbxh.zzW(var5);
   }

   private SessionInsertRequest(SessionInsertRequest.Builder var1) {
      this(var1.zzaXe, var1.zzaXf, var1.zzaXg, (zzbxg)null);
   }

   public SessionInsertRequest(SessionInsertRequest var1, zzbxg var2) {
      this(var1.zzaXe, var1.zzaXf, var1.zzaXg, var2);
   }

   private SessionInsertRequest(Session var1, List var2, List var3, zzbxg var4) {
      this.versionCode = 3;
      this.zzaXe = var1;
      this.zzaXf = Collections.unmodifiableList(var2);
      this.zzaXg = Collections.unmodifiableList(var3);
      this.zzaXh = var4;
   }

   public Session getSession() {
      return this.zzaXe;
   }

   public List getDataSets() {
      return this.zzaXf;
   }

   public List getAggregateDataPoints() {
      return this.zzaXg;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof SessionInsertRequest) {
            SessionInsertRequest var3 = (SessionInsertRequest)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaXe, var3.zzaXe) && com.google.android.gms.common.internal.zzbe.equal(this.zzaXf, var3.zzaXf) && com.google.android.gms.common.internal.zzbe.equal(this.zzaXg, var3.zzaXg)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaXe, this.zzaXf, this.zzaXg});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("session", this.zzaXe).zzg("dataSets", this.zzaXf).zzg("aggregateDataPoints", this.zzaXg).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getSession(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getDataSets(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getAggregateDataPoints(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaXh == null ? null : this.zzaXh.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   SessionInsertRequest(SessionInsertRequest.Builder var1, zzas var2) {
      this(var1);
   }

   public static class Builder {
      private Session zzaXe;
      private List zzaXf = new ArrayList();
      private List zzaXg = new ArrayList();
      private List zzaXi = new ArrayList();

      public SessionInsertRequest.Builder setSession(Session var1) {
         this.zzaXe = var1;
         return this;
      }

      public SessionInsertRequest.Builder addDataSet(DataSet var1) {
         zzbo.zzb(var1 != null, "Must specify a valid data set.");
         DataSource var2 = var1.getDataSource();
         zzbo.zza(!this.zzaXi.contains(var2), "Data set for this data source %s is already added.", new Object[]{var2});
         zzbo.zzb(!var1.getDataPoints().isEmpty(), "No data points specified in the input data set.");
         this.zzaXi.add(var2);
         this.zzaXf.add(var1);
         return this;
      }

      public SessionInsertRequest.Builder addAggregateDataPoint(DataPoint var1) {
         zzbo.zzb(var1 != null, "Must specify a valid aggregate data point.");
         DataSource var2 = var1.getDataSource();
         zzbo.zza(!this.zzaXi.contains(var2), "Data set/Aggregate data point for this data source %s is already added.", new Object[]{var2});
         DataSet.zzb(var1);
         this.zzaXi.add(var2);
         this.zzaXg.add(var1);
         return this;
      }

      public SessionInsertRequest build() {
         zzbo.zza(this.zzaXe != null, "Must specify a valid session.");
         zzbo.zza(this.zzaXe.getEndTime(TimeUnit.MILLISECONDS) != 0L, "Must specify a valid end time, cannot insert a continuing session.");
         SessionInsertRequest.Builder var1 = this;
         Iterator var2 = this.zzaXf.iterator();

         while(var2.hasNext()) {
            Iterator var4 = ((DataSet)var2.next()).getDataPoints().iterator();

            while(var4.hasNext()) {
               DataPoint var5 = (DataPoint)var4.next();
               var1.zzd(var5);
            }
         }

         var2 = var1.zzaXg.iterator();

         while(var2.hasNext()) {
            DataPoint var3 = (DataPoint)var2.next();
            var1.zzd(var3);
         }

         return new SessionInsertRequest(this, (zzas)null);
      }

      private final void zzd(DataPoint var1) {
         long var4 = this.zzaXe.getStartTime(TimeUnit.NANOSECONDS);
         long var6 = this.zzaXe.getEndTime(TimeUnit.NANOSECONDS);
         long var8;
         if ((var8 = var1.getTimestamp(TimeUnit.NANOSECONDS)) != 0L) {
            TimeUnit var10 = TimeUnit.MILLISECONDS;
            if (var8 < var4 || var8 > var6) {
               var8 = zzbue.zza(var8, TimeUnit.NANOSECONDS, var10);
            }

            zzbo.zza(var8 >= var4 && var8 <= var6, "Data point %s has time stamp outside session interval [%d, %d]", new Object[]{var1, var4, var6});
            if (var1.getTimestamp(TimeUnit.NANOSECONDS) != var8) {
               Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", var1.getTimestamp(TimeUnit.NANOSECONDS), var8, var10));
               var1.setTimestamp(var8, TimeUnit.NANOSECONDS);
            }
         }

         var4 = this.zzaXe.getStartTime(TimeUnit.NANOSECONDS);
         var6 = this.zzaXe.getEndTime(TimeUnit.NANOSECONDS);
         var8 = var1.getStartTime(TimeUnit.NANOSECONDS);
         long var13 = var1.getEndTime(TimeUnit.NANOSECONDS);
         if (var8 != 0L && var13 != 0L) {
            TimeUnit var12 = TimeUnit.MILLISECONDS;
            if (var13 > var6) {
               var13 = zzbue.zza(var13, TimeUnit.NANOSECONDS, var12);
            }

            zzbo.zza(var8 >= var4 && var13 <= var6, "Data point %s has start and end times outside session interval [%d, %d]", new Object[]{var1, var4, var6});
            if (var13 != var1.getEndTime(TimeUnit.NANOSECONDS)) {
               Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", var1.getEndTime(TimeUnit.NANOSECONDS), var13, var12));
               var1.setTimeInterval(var8, var13, TimeUnit.NANOSECONDS);
            }
         }

      }
   }
}
