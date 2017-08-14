package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.data.zzu;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FitnessSensorServiceRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int UNSPECIFIED = -1;
   private final int zzaku;
   private final DataSource zzaUd;
   private final zzt zzaWV;
   private final long zzaXE;
   private final long zzaXF;
   public static final Creator CREATOR = new zzb();

   FitnessSensorServiceRequest(int var1, DataSource var2, IBinder var3, long var4, long var6) {
      this.zzaku = var1;
      this.zzaUd = var2;
      this.zzaWV = zzu.zzN(var3);
      this.zzaXE = var4;
      this.zzaXF = var6;
   }

   public DataSource getDataSource() {
      return this.zzaUd;
   }

   public SensorEventDispatcher getDispatcher() {
      return new zzc(this.zzaWV);
   }

   public long getSamplingRate(TimeUnit var1) {
      return this.zzaXE == -1L ? -1L : var1.convert(this.zzaXE, TimeUnit.MICROSECONDS);
   }

   public long getBatchInterval(TimeUnit var1) {
      return var1.convert(this.zzaXF, TimeUnit.MICROSECONDS);
   }

   public String toString() {
      return String.format("FitnessSensorServiceRequest{%s}", this.zzaUd);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getDataSource(), var2, false);
      zzd.zza(var1, 2, this.zzaWV.asBinder(), false);
      zzd.zza(var1, 3, this.zzaXE);
      zzd.zza(var1, 4, this.zzaXF);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof FitnessSensorServiceRequest) {
            FitnessSensorServiceRequest var3 = (FitnessSensorServiceRequest)var1;
            if (zzbe.equal(this.zzaUd, var3.zzaUd) && this.zzaXE == var3.zzaXE && this.zzaXF == var3.zzaXF) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaXE, this.zzaXF});
   }
}
