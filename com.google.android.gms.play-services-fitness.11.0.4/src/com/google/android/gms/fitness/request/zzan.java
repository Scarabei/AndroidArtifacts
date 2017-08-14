package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class zzan extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private DataSource zzaUd;
   private DataType zzaUe;
   private com.google.android.gms.fitness.data.zzt zzaWV;
   private final long zzaVj;
   private final long zzaWW;
   private final PendingIntent mPendingIntent;
   private final long zzaWX;
   private final int zzaVk;
   private final List zzaWY;
   private final long zzaWZ;
   private final List zzaXa;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzao();

   zzan(int var1, DataSource var2, DataType var3, IBinder var4, int var5, int var6, long var7, long var9, PendingIntent var11, long var12, int var14, List var15, long var16, IBinder var18) {
      this.zzaku = var1;
      this.zzaUd = var2;
      this.zzaUe = var3;
      this.zzaWV = var4 == null ? null : com.google.android.gms.fitness.data.zzu.zzN(var4);
      this.zzaVj = var7 == 0L ? (long)var5 : var7;
      this.zzaWX = var12;
      this.zzaWW = var9 == 0L ? (long)var6 : var9;
      this.zzaWY = var15;
      this.mPendingIntent = var11;
      this.zzaVk = var14;
      this.zzaXa = Collections.emptyList();
      this.zzaWZ = var16;
      this.zzaWo = zzbxh.zzW(var18);
   }

   public zzan(SensorRequest var1, com.google.android.gms.fitness.data.zzt var2, PendingIntent var3, zzbxg var4) {
      this(var1.getDataSource(), var1.getDataType(), var2, var3, var1.getSamplingRate(TimeUnit.MICROSECONDS), var1.getFastestRate(TimeUnit.MICROSECONDS), var1.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), var1.getAccuracyMode(), (List)null, Collections.emptyList(), var1.zztW(), var4);
   }

   private zzan(DataSource var1, DataType var2, com.google.android.gms.fitness.data.zzt var3, PendingIntent var4, long var5, long var7, long var9, int var11, List var12, List var13, long var14, zzbxg var16) {
      this.zzaku = 6;
      this.zzaUd = var1;
      this.zzaUe = var2;
      this.zzaWV = var3;
      this.mPendingIntent = var4;
      this.zzaVj = var5;
      this.zzaWX = var7;
      this.zzaWW = var9;
      this.zzaVk = var11;
      this.zzaWY = null;
      this.zzaXa = var13;
      this.zzaWZ = var14;
      this.zzaWo = var16;
   }

   public final String toString() {
      return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.zzaUe, this.zzaUd, this.zzaVj, this.zzaWX, this.zzaWW);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWV == null ? null : this.zzaWV.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, 0);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, 0);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaVj);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaWW);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.mPendingIntent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaWX);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.zzaVk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.zzaWY, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzaWZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof zzan) {
            zzan var3 = (zzan)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaUd, var3.zzaUd) && com.google.android.gms.common.internal.zzbe.equal(this.zzaUe, var3.zzaUe) && this.zzaVj == var3.zzaVj && this.zzaWX == var3.zzaWX && this.zzaWW == var3.zzaWW && this.zzaVk == var3.zzaVk && com.google.android.gms.common.internal.zzbe.equal(this.zzaWY, var3.zzaWY)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaUe, this.zzaWV, this.zzaVj, this.zzaWX, this.zzaWW, this.zzaVk, this.zzaWY});
   }
}
