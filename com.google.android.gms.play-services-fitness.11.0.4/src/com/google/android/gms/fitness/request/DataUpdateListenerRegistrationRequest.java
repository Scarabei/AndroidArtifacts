package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;

public class DataUpdateListenerRegistrationRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private DataSource zzaUd;
   private DataType zzaUe;
   private final PendingIntent mPendingIntent;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzu();

   DataUpdateListenerRegistrationRequest(int var1, DataSource var2, DataType var3, PendingIntent var4, IBinder var5) {
      this.zzaku = var1;
      this.zzaUd = var2;
      this.zzaUe = var3;
      this.mPendingIntent = var4;
      this.zzaWo = zzbxh.zzW(var5);
   }

   private DataUpdateListenerRegistrationRequest(DataSource var1, DataType var2, PendingIntent var3, IBinder var4) {
      this.zzaku = 1;
      this.zzaUd = var1;
      this.zzaUe = var2;
      this.mPendingIntent = var3;
      this.zzaWo = zzbxh.zzW(var4);
   }

   public DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest var1, IBinder var2) {
      this(var1.zzaUd, var1.zzaUe, var1.mPendingIntent, var2);
   }

   private DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest.Builder var1) {
      this(var1.zzaUd, var1.zzaUe, var1.mPendingIntent, (IBinder)null);
   }

   public DataSource getDataSource() {
      return this.zzaUd;
   }

   public DataType getDataType() {
      return this.zzaUe;
   }

   public PendingIntent getIntent() {
      return this.mPendingIntent;
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataSource", this.zzaUd).zzg("dataType", this.zzaUe).zzg("pendingIntent", this.mPendingIntent).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getIntent(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataUpdateListenerRegistrationRequest) {
            DataUpdateListenerRegistrationRequest var3 = (DataUpdateListenerRegistrationRequest)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaUd, var3.zzaUd) && com.google.android.gms.common.internal.zzbe.equal(this.zzaUe, var3.zzaUe) && com.google.android.gms.common.internal.zzbe.equal(this.mPendingIntent, var3.mPendingIntent)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaUe, this.mPendingIntent});
   }

   // $FF: synthetic method
   DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest.Builder var1, zzt var2) {
      this(var1);
   }

   public static class Builder {
      private DataSource zzaUd;
      private DataType zzaUe;
      private PendingIntent mPendingIntent;

      public DataUpdateListenerRegistrationRequest.Builder setDataSource(DataSource var1) throws NullPointerException {
         zzbo.zzu(var1);
         this.zzaUd = var1;
         return this;
      }

      public DataUpdateListenerRegistrationRequest.Builder setDataType(DataType var1) {
         zzbo.zzu(var1);
         this.zzaUe = var1;
         return this;
      }

      public DataUpdateListenerRegistrationRequest.Builder setPendingIntent(PendingIntent var1) {
         zzbo.zzu(var1);
         this.mPendingIntent = var1;
         return this;
      }

      public DataUpdateListenerRegistrationRequest build() {
         zzbo.zza(this.zzaUd != null || this.zzaUe != null, "Set either dataSource or dataTYpe");
         zzbo.zzb(this.mPendingIntent, "pendingIntent must be set");
         return new DataUpdateListenerRegistrationRequest(this, (zzt)null);
      }
   }
}
