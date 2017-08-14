package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbxa;
import com.google.android.gms.internal.zzbxb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionReadRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String zzaXj;
   private final String mSessionId;
   private final long zzagZ;
   private final long zzaTo;
   private final List zzaTn;
   private final List zzaWr;
   private boolean zzaXk;
   private final boolean zzaWC;
   private final List zzaXl;
   private final zzbxa zzaXm;
   public static final Creator CREATOR = new zzav();

   SessionReadRequest(int var1, String var2, String var3, long var4, long var6, List var8, List var9, boolean var10, boolean var11, List var12, IBinder var13) {
      this.zzaku = var1;
      this.zzaXj = var2;
      this.mSessionId = var3;
      this.zzagZ = var4;
      this.zzaTo = var6;
      this.zzaTn = var8;
      this.zzaWr = var9;
      this.zzaXk = var10;
      this.zzaWC = var11;
      this.zzaXl = var12;
      this.zzaXm = zzbxb.zzU(var13);
   }

   private SessionReadRequest(SessionReadRequest.Builder var1) {
      this(var1.zzaXj, var1.mSessionId, var1.zzagZ, var1.zzaTo, var1.zzaTn, var1.zzaWr, var1.zzaXk, var1.zzaWC, var1.zzaXl, (zzbxa)null);
   }

   public SessionReadRequest(SessionReadRequest var1, zzbxa var2) {
      this(var1.zzaXj, var1.mSessionId, var1.zzagZ, var1.zzaTo, var1.zzaTn, var1.zzaWr, var1.zzaXk, var1.zzaWC, var1.zzaXl, var2);
   }

   private SessionReadRequest(String var1, String var2, long var3, long var5, List var7, List var8, boolean var9, boolean var10, List var11, zzbxa var12) {
      this(5, var1, var2, var3, var5, var7, var8, var9, var10, var11, var12 == null ? null : var12.asBinder());
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public String getSessionName() {
      return this.zzaXj;
   }

   public String getSessionId() {
      return this.mSessionId;
   }

   public List getDataTypes() {
      return this.zzaTn;
   }

   public List getDataSources() {
      return this.zzaWr;
   }

   public boolean includeSessionsFromAllApps() {
      return this.zzaXk;
   }

   public List getExcludedPackages() {
      return this.zzaXl;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof SessionReadRequest)) {
            return false;
         }

         SessionReadRequest var3 = (SessionReadRequest)var1;
         if (!com.google.android.gms.common.internal.zzbe.equal(this.zzaXj, var3.zzaXj) || !this.mSessionId.equals(var3.mSessionId) || this.zzagZ != var3.zzagZ || this.zzaTo != var3.zzaTo || !com.google.android.gms.common.internal.zzbe.equal(this.zzaTn, var3.zzaTn) || !com.google.android.gms.common.internal.zzbe.equal(this.zzaWr, var3.zzaWr) || this.zzaXk != var3.zzaXk || !this.zzaXl.equals(var3.zzaXl) || this.zzaWC != var3.zzaWC) {
            return false;
         }
      }

      return true;
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaXj, this.mSessionId, this.zzagZ, this.zzaTo});
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("sessionName", this.zzaXj).zzg("sessionId", this.mSessionId).zzg("startTimeMillis", this.zzagZ).zzg("endTimeMillis", this.zzaTo).zzg("dataTypes", this.zzaTn).zzg("dataSources", this.zzaWr).zzg("sessionsFromAllApps", this.zzaXk).zzg("excludedPackages", this.zzaXl).zzg("useServer", this.zzaWC).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getSessionName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getSessionId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getDataTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getDataSources(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaXk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaWC);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 9, this.getExcludedPackages(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzaXm == null ? null : this.zzaXm.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   SessionReadRequest(SessionReadRequest.Builder var1, zzau var2) {
      this(var1);
   }

   public static class Builder {
      private String zzaXj;
      private String mSessionId;
      private long zzagZ = 0L;
      private long zzaTo = 0L;
      private List zzaTn = new ArrayList();
      private List zzaWr = new ArrayList();
      private boolean zzaXk = false;
      private boolean zzaWC = false;
      private List zzaXl = new ArrayList();

      public SessionReadRequest.Builder setTimeInterval(long var1, long var3, TimeUnit var5) {
         this.zzagZ = var5.toMillis(var1);
         this.zzaTo = var5.toMillis(var3);
         return this;
      }

      public SessionReadRequest.Builder setSessionName(String var1) {
         this.zzaXj = var1;
         return this;
      }

      public SessionReadRequest.Builder setSessionId(String var1) {
         this.mSessionId = var1;
         return this;
      }

      public SessionReadRequest.Builder read(DataSource var1) {
         zzbo.zzb(var1, "Attempting to add a null data source");
         if (!this.zzaWr.contains(var1)) {
            this.zzaWr.add(var1);
         }

         return this;
      }

      public SessionReadRequest.Builder read(DataType var1) {
         zzbo.zzb(var1, "Attempting to use a null data type");
         if (!this.zzaTn.contains(var1)) {
            this.zzaTn.add(var1);
         }

         return this;
      }

      public SessionReadRequest.Builder readSessionsFromAllApps() {
         this.zzaXk = true;
         return this;
      }

      public SessionReadRequest.Builder excludePackage(String var1) {
         zzbo.zzb(var1, "Attempting to use a null package name");
         if (!this.zzaXl.contains(var1)) {
            this.zzaXl.add(var1);
         }

         return this;
      }

      public SessionReadRequest.Builder enableServerQueries() {
         this.zzaWC = true;
         return this;
      }

      public SessionReadRequest build() {
         zzbo.zzb(this.zzagZ > 0L, "Invalid start time: %s", new Object[]{this.zzagZ});
         zzbo.zzb(this.zzaTo > 0L && this.zzaTo > this.zzagZ, "Invalid end time: %s", new Object[]{this.zzaTo});
         return new SessionReadRequest(this, (zzau)null);
      }
   }
}
