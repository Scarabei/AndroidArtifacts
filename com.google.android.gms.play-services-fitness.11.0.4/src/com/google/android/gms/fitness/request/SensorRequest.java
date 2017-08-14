package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SensorRequest {
   public static final int ACCURACY_MODE_LOW = 1;
   public static final int ACCURACY_MODE_DEFAULT = 2;
   public static final int ACCURACY_MODE_HIGH = 3;
   private final DataSource zzaUd;
   private final DataType zzaUe;
   private final long zzaVj;
   private final long zzaWX;
   private final long zzaWW;
   private final int zzaVk;
   private final LocationRequest zzaXb;
   private final long zzaXc;

   private SensorRequest(SensorRequest.Builder var1) {
      this.zzaUd = var1.zzaUd;
      this.zzaUe = var1.zzaUe;
      this.zzaVj = var1.zzaVj;
      this.zzaWX = var1.zzaWX;
      this.zzaWW = var1.zzaWW;
      this.zzaVk = var1.zzaVk;
      this.zzaXb = null;
      this.zzaXc = var1.zzaXc;
   }

   private SensorRequest(DataSource var1, LocationRequest var2) {
      this.zzaXb = var2;
      this.zzaVj = TimeUnit.MILLISECONDS.toMicros(var2.getInterval());
      this.zzaWX = TimeUnit.MILLISECONDS.toMicros(var2.getFastestInterval());
      this.zzaWW = this.zzaVj;
      this.zzaUe = var1.getDataType();
      byte var10001;
      switch(var2.getPriority()) {
      case 100:
         var10001 = 3;
         break;
      case 104:
         var10001 = 1;
         break;
      default:
         var10001 = 2;
      }

      this.zzaVk = var10001;
      this.zzaUd = var1;
      long var3;
      if ((var3 = var2.getExpirationTime()) == Long.MAX_VALUE) {
         this.zzaXc = Long.MAX_VALUE;
      } else {
         this.zzaXc = TimeUnit.MILLISECONDS.toMicros(var3 - SystemClock.elapsedRealtime());
      }
   }

   public static SensorRequest fromLocationRequest(DataSource var0, LocationRequest var1) {
      return new SensorRequest(var0, var1);
   }

   public DataSource getDataSource() {
      return this.zzaUd;
   }

   public DataType getDataType() {
      return this.zzaUe;
   }

   public long getSamplingRate(TimeUnit var1) {
      return var1.convert(this.zzaVj, TimeUnit.MICROSECONDS);
   }

   public long getFastestRate(TimeUnit var1) {
      return var1.convert(this.zzaWX, TimeUnit.MICROSECONDS);
   }

   public long getMaxDeliveryLatency(TimeUnit var1) {
      return var1.convert(this.zzaWW, TimeUnit.MICROSECONDS);
   }

   public int getAccuracyMode() {
      return this.zzaVk;
   }

   public final long zztW() {
      return this.zzaXc;
   }

   public String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("dataSource", this.zzaUd).zzg("dataType", this.zzaUe).zzg("samplingRateMicros", this.zzaVj).zzg("deliveryLatencyMicros", this.zzaWW).zzg("timeOutMicros", this.zzaXc).toString();
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof SensorRequest) {
            SensorRequest var3 = (SensorRequest)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaUd, var3.zzaUd) && com.google.android.gms.common.internal.zzbe.equal(this.zzaUe, var3.zzaUe) && this.zzaVj == var3.zzaVj && this.zzaWX == var3.zzaWX && this.zzaWW == var3.zzaWW && this.zzaVk == var3.zzaVk && com.google.android.gms.common.internal.zzbe.equal(this.zzaXb, var3.zzaXb) && this.zzaXc == var3.zzaXc) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaUe, this.zzaVj, this.zzaWX, this.zzaWW, this.zzaVk, this.zzaXb, this.zzaXc});
   }

   // $FF: synthetic method
   SensorRequest(SensorRequest.Builder var1, zzap var2) {
      this(var1);
   }

   public static class Builder {
      private DataSource zzaUd;
      private DataType zzaUe;
      private long zzaVj = -1L;
      private long zzaWX = 0L;
      private long zzaWW = 0L;
      private boolean zzaXd = false;
      private int zzaVk = 2;
      private long zzaXc = Long.MAX_VALUE;

      public SensorRequest.Builder setDataSource(DataSource var1) {
         this.zzaUd = var1;
         return this;
      }

      public SensorRequest.Builder setDataType(DataType var1) {
         this.zzaUe = var1;
         return this;
      }

      public SensorRequest.Builder setSamplingRate(long var1, TimeUnit var3) {
         zzbo.zzb(var1 >= 0L, "Cannot use a negative sampling interval");
         this.zzaVj = var3.toMicros(var1);
         if (!this.zzaXd) {
            this.zzaWX = this.zzaVj / 2L;
         }

         return this;
      }

      public SensorRequest.Builder setFastestRate(int var1, TimeUnit var2) {
         zzbo.zzb(var1 >= 0, "Cannot use a negative interval");
         this.zzaXd = true;
         this.zzaWX = var2.toMicros((long)var1);
         return this;
      }

      public SensorRequest.Builder setMaxDeliveryLatency(int var1, TimeUnit var2) {
         zzbo.zzb(var1 >= 0, "Cannot use a negative delivery interval");
         this.zzaWW = var2.toMicros((long)var1);
         return this;
      }

      public SensorRequest.Builder setAccuracyMode(int var1) {
         int var10001;
         switch(var1) {
         case 1:
         case 3:
            var10001 = var1;
            break;
         default:
            var10001 = 2;
         }

         this.zzaVk = var10001;
         return this;
      }

      public SensorRequest.Builder setTimeout(long var1, TimeUnit var3) {
         zzbo.zzb(var1 > 0L, "Invalid time out value specified: %d", new Object[]{var1});
         zzbo.zzb(var3 != null, "Invalid time unit specified");
         this.zzaXc = var3.toMicros(var1);
         return this;
      }

      public SensorRequest build() {
         zzbo.zza(this.zzaUd != null || this.zzaUe != null, "Must call setDataSource() or setDataType()");
         zzbo.zza(this.zzaUe == null || this.zzaUd == null || this.zzaUe.equals(this.zzaUd.getDataType()), "Specified data type is incompatible with specified data source");
         return new SensorRequest(this, (zzap)null);
      }
   }
}
