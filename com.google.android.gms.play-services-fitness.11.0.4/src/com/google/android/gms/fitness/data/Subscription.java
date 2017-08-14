package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class Subscription extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final DataSource zzaUd;
   private final DataType zzaUe;
   private final long zzaVj;
   private final int zzaVk;
   public static final Creator CREATOR = new zzah();

   Subscription(int var1, DataSource var2, DataType var3, long var4, int var6) {
      this.zzaku = var1;
      this.zzaUd = var2;
      this.zzaUe = var3;
      this.zzaVj = var4;
      this.zzaVk = var6;
   }

   private Subscription(Subscription.zza var1) {
      this.zzaku = 1;
      this.zzaUe = var1.zzaUe;
      this.zzaUd = var1.zzaUd;
      this.zzaVj = var1.zzaVj;
      this.zzaVk = var1.zzaVk;
   }

   public DataSource getDataSource() {
      return this.zzaUd;
   }

   public DataType getDataType() {
      return this.zzaUe;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Subscription) {
            Subscription var3 = (Subscription)var1;
            if (zzbe.equal(this.zzaUd, var3.zzaUd) && zzbe.equal(this.zzaUe, var3.zzaUe) && this.zzaVj == var3.zzaVj && this.zzaVk == var3.zzaVk) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaUd, this.zzaVj, this.zzaVk});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("dataSource", this.zzaUd).zzg("dataType", this.zzaUe).zzg("samplingIntervalMicros", this.zzaVj).zzg("accuracyMode", this.zzaVk).toString();
   }

   public String toDebugString() {
      return String.format("Subscription{%s}", this.zzaUd == null ? this.zzaUe.getName() : this.zzaUd.toDebugString());
   }

   public final DataType zztP() {
      return this.zzaUe == null ? this.zzaUd.getDataType() : this.zzaUe;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaVj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaVk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   Subscription(Subscription.zza var1, zzag var2) {
      this(var1);
   }

   public static class zza {
      private DataSource zzaUd;
      private DataType zzaUe;
      private long zzaVj = -1L;
      private int zzaVk = 2;

      public final Subscription.zza zza(DataSource var1) {
         this.zzaUd = var1;
         return this;
      }

      public final Subscription.zza zza(DataType var1) {
         this.zzaUe = var1;
         return this;
      }

      public final Subscription zztQ() {
         zzbo.zza(this.zzaUd != null || this.zzaUe != null, "Must call setDataSource() or setDataType()");
         zzbo.zza(this.zzaUe == null || this.zzaUd == null || this.zzaUe.equals(this.zzaUd.getDataType()), "Specified data type is incompatible with specified data source");
         return new Subscription(this, (zzag)null);
      }
   }
}
