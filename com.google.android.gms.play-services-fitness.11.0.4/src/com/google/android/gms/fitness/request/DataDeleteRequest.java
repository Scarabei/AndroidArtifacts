package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDeleteRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final long zzagZ;
   private final long zzaTo;
   private final List zzaWr;
   private final List zzaTn;
   private final List zzaWs;
   private final boolean zzaWt;
   private final boolean zzaWu;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzi();

   DataDeleteRequest(int var1, long var2, long var4, List var6, List var7, List var8, boolean var9, boolean var10, IBinder var11) {
      this.zzaku = var1;
      this.zzagZ = var2;
      this.zzaTo = var4;
      this.zzaWr = Collections.unmodifiableList(var6);
      this.zzaTn = Collections.unmodifiableList(var7);
      this.zzaWs = var8;
      this.zzaWt = var9;
      this.zzaWu = var10;
      this.zzaWo = zzbxh.zzW(var11);
   }

   private DataDeleteRequest(DataDeleteRequest.Builder var1) {
      this(var1.zzagZ, var1.zzaTo, var1.zzaWr, var1.zzaTn, var1.zzaWs, var1.zzaWt, var1.zzaWu, (zzbxg)null);
   }

   public DataDeleteRequest(DataDeleteRequest var1, zzbxg var2) {
      this(var1.zzagZ, var1.zzaTo, var1.zzaWr, var1.zzaTn, var1.zzaWs, var1.zzaWt, var1.zzaWu, var2);
   }

   private DataDeleteRequest(long var1, long var3, List var5, List var6, List var7, boolean var8, boolean var9, zzbxg var10) {
      this.zzaku = 3;
      this.zzagZ = var1;
      this.zzaTo = var3;
      this.zzaWr = Collections.unmodifiableList(var5);
      this.zzaTn = Collections.unmodifiableList(var6);
      this.zzaWs = var7;
      this.zzaWt = var8;
      this.zzaWu = var9;
      this.zzaWo = var10;
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public List getDataSources() {
      return this.zzaWr;
   }

   public List getDataTypes() {
      return this.zzaTn;
   }

   public List getSessions() {
      return this.zzaWs;
   }

   public boolean deleteAllData() {
      return this.zzaWt;
   }

   public boolean deleteAllSessions() {
      return this.zzaWu;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof DataDeleteRequest) {
            DataDeleteRequest var3 = (DataDeleteRequest)var1;
            if (this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && com.google.android.gms.common.internal.zzbe.equal(this.zzaWr, var3.zzaWr) && com.google.android.gms.common.internal.zzbe.equal(this.zzaTn, var3.zzaTn) && com.google.android.gms.common.internal.zzbe.equal(this.zzaWs, var3.zzaWs) && this.zzaWt == var3.zzaWt && this.zzaWu == var3.zzaWu) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzagZ, this.zzaTo});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("startTimeMillis", this.zzagZ).zzg("endTimeMillis", this.zzaTo).zzg("dataSources", this.zzaWr).zzg("dateTypes", this.zzaTn).zzg("sessions", this.zzaWs).zzg("deleteAllData", this.zzaWt).zzg("deleteAllSessions", this.zzaWu).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getDataSources(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getSessions(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaWt);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaWu);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   DataDeleteRequest(DataDeleteRequest.Builder var1, zzh var2) {
      this(var1);
   }

   public static class Builder {
      private long zzagZ;
      private long zzaTo;
      private List zzaWr = new ArrayList();
      private List zzaTn = new ArrayList();
      private List zzaWs = new ArrayList();
      private boolean zzaWt = false;
      private boolean zzaWu = false;

      public DataDeleteRequest.Builder setTimeInterval(long var1, long var3, TimeUnit var5) {
         zzbo.zzb(var1 > 0L, "Invalid start time :%d", new Object[]{var1});
         zzbo.zzb(var3 > var1, "Invalid end time :%d", new Object[]{var3});
         this.zzagZ = var5.toMillis(var1);
         this.zzaTo = var5.toMillis(var3);
         return this;
      }

      public DataDeleteRequest.Builder deleteAllData() {
         zzbo.zzb(this.zzaTn.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
         zzbo.zzb(this.zzaWr.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
         this.zzaWt = true;
         return this;
      }

      public DataDeleteRequest.Builder addDataType(DataType var1) {
         zzbo.zzb(!this.zzaWt, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
         zzbo.zzb(var1 != null, "Must specify a valid data type");
         if (!this.zzaTn.contains(var1)) {
            this.zzaTn.add(var1);
         }

         return this;
      }

      public DataDeleteRequest.Builder addDataSource(DataSource var1) {
         zzbo.zzb(!this.zzaWt, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
         zzbo.zzb(var1 != null, "Must specify a valid data source");
         if (!this.zzaWr.contains(var1)) {
            this.zzaWr.add(var1);
         }

         return this;
      }

      public DataDeleteRequest.Builder addSession(Session var1) {
         zzbo.zzb(!this.zzaWu, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
         zzbo.zzb(var1 != null, "Must specify a valid session");
         zzbo.zzb(var1.getEndTime(TimeUnit.MILLISECONDS) > 0L, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
         this.zzaWs.add(var1);
         return this;
      }

      public DataDeleteRequest.Builder deleteAllSessions() {
         zzbo.zzb(this.zzaWs.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
         this.zzaWu = true;
         return this;
      }

      public DataDeleteRequest build() {
         zzbo.zza(this.zzagZ > 0L && this.zzaTo > this.zzagZ, "Must specify a valid time interval");
         boolean var1 = this.zzaWt || !this.zzaWr.isEmpty() || !this.zzaTn.isEmpty();
         boolean var2 = this.zzaWu || !this.zzaWs.isEmpty();
         zzbo.zza(var1 || var2, "No data or session marked for deletion");
         DataDeleteRequest.Builder var3 = this;
         if (!this.zzaWs.isEmpty()) {
            Iterator var4 = this.zzaWs.iterator();

            while(var4.hasNext()) {
               Session var5;
               zzbo.zza((var5 = (Session)var4.next()).getStartTime(TimeUnit.MILLISECONDS) >= var3.zzagZ && var5.getEndTime(TimeUnit.MILLISECONDS) <= var3.zzaTo, "Session %s is outside the time interval [%d, %d]", new Object[]{var5, var3.zzagZ, var3.zzaTo});
            }
         }

         return new DataDeleteRequest(this, (zzh)null);
      }
   }
}
