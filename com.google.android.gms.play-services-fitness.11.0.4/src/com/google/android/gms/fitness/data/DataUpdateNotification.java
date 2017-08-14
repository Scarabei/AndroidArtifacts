package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DataUpdateNotification extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
   public static final int OPERATION_INSERT = 1;
   public static final int OPERATION_DELETE = 2;
   public static final int OPERATION_UPDATE = 3;
   public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
   private int zzaku;
   private final long zzaUa;
   private final long zzaUb;
   private final int zzaUc;
   private final DataSource zzaUd;
   private final DataType zzaUe;
   public static final Creator CREATOR = new zzn();

   DataUpdateNotification(int var1, long var2, long var4, int var6, DataSource var7, DataType var8) {
      this.zzaku = var1;
      this.zzaUa = var2;
      this.zzaUb = var4;
      this.zzaUc = var6;
      this.zzaUd = var7;
      this.zzaUe = var8;
   }

   public static DataUpdateNotification getDataUpdateNotification(Intent var0) {
      return (DataUpdateNotification)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "vnd.google.fitness.data_udpate_notification", CREATOR);
   }

   public long getUpdateStartTime(TimeUnit var1) {
      return var1.convert(this.zzaUa, TimeUnit.NANOSECONDS);
   }

   public long getUpdateEndTime(TimeUnit var1) {
      return var1.convert(this.zzaUb, TimeUnit.NANOSECONDS);
   }

   public int getOperationType() {
      return this.zzaUc;
   }

   public DataSource getDataSource() {
      return this.zzaUd;
   }

   public DataType getDataType() {
      return this.zzaUe;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof DataUpdateNotification) {
            DataUpdateNotification var3 = (DataUpdateNotification)var1;
            if (this.zzaUa == var3.zzaUa && this.zzaUb == var3.zzaUb && this.zzaUc == var3.zzaUc && zzbe.equal(this.zzaUd, var3.zzaUd) && zzbe.equal(this.zzaUe, var3.zzaUe)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUa, this.zzaUb, this.zzaUc, this.zzaUd, this.zzaUe});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("updateStartTimeNanos", this.zzaUa).zzg("updateEndTimeNanos", this.zzaUb).zzg("operationType", this.zzaUc).zzg("dataSource", this.zzaUd).zzg("dataType", this.zzaUe).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUa);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUb);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getOperationType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
