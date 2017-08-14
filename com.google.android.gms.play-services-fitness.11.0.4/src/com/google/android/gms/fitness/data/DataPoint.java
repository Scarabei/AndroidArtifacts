package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataPoint extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   private final int versionCode;
   private final DataSource zzaTa;
   private long zzaTv;
   private long zzaTw;
   private final Value[] zzaTx;
   private DataSource zzaTy;
   private long zzaTz;
   private long zzaTA;
   public static final Creator CREATOR = new zzh();

   DataPoint(int var1, DataSource var2, long var3, long var5, Value[] var7, DataSource var8, long var9, long var11) {
      this.versionCode = var1;
      this.zzaTa = var2;
      this.zzaTy = var8;
      this.zzaTv = var3;
      this.zzaTw = var5;
      this.zzaTx = var7;
      this.zzaTz = var9;
      this.zzaTA = var11;
   }

   DataPoint(List var1, RawDataPoint var2) {
      this(zzc(var1, var2.zzaUZ), zzc(var1, var2.zzaVa), var2);
   }

   private DataPoint(DataSource var1, DataSource var2, RawDataPoint var3) {
      this(4, var1, zza(var3.zzaUW, 0L), zza(var3.zzaUX, 0L), var3.zzaUY, var2, zza(var3.zzaVb, 0L), zza(var3.zzaVc, 0L));
   }

   private static DataSource zzc(List var0, int var1) {
      return var1 >= 0 && var1 < var0.size() ? (DataSource)var0.get(var1) : null;
   }

   private DataPoint(DataSource var1) {
      this.versionCode = 4;
      this.zzaTa = (DataSource)zzbo.zzb(var1, "Data source cannot be null");
      List var2 = var1.getDataType().getFields();
      this.zzaTx = new Value[var2.size()];
      int var3 = 0;

      for(Iterator var4 = var2.iterator(); var4.hasNext(); ++var3) {
         Field var5 = (Field)var4.next();
         this.zzaTx[var3] = new Value(var5.getFormat());
      }

   }

   public static DataPoint create(DataSource var0) {
      return new DataPoint(var0);
   }

   public static DataPoint extract(Intent var0) {
      return var0 == null ? null : (DataPoint)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
   }

   public final DataPoint setTimestamp(long var1, TimeUnit var3) {
      this.zzaTv = var3.toNanos(var1);
      if (this.getDataType() == DataType.TYPE_LOCATION_SAMPLE && zzbue.zza(var3)) {
         Log.w("Fitness", "Storing location at more than millisecond granularity is not supported. Extra precision is lost as the value is converted to milliseconds.");
         this.zzaTv = zzbue.zza(this.zzaTv, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS);
      }

      return this;
   }

   public final DataPoint setTimeInterval(long var1, long var3, TimeUnit var5) {
      this.zzaTw = var5.toNanos(var1);
      this.zzaTv = var5.toNanos(var3);
      return this;
   }

   public final Value zzaT(int var1) {
      DataType var2 = this.getDataType();
      if (var1 >= 0 && var1 < var2.getFields().size()) {
         return this.zzaTx[var1];
      } else {
         throw new IllegalArgumentException(String.format("fieldIndex %s is out of range for %s", var1, var2));
      }
   }

   public final Value getValue(Field var1) {
      int var2 = this.getDataType().indexOf(var1);
      return this.zzaTx[var2];
   }

   public final Value[] zztG() {
      return this.zzaTx;
   }

   public final DataPoint setFloatValues(float... var1) {
      this.zzaU(var1.length);

      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.zzaTx[var2].setFloat(var1[var2]);
      }

      return this;
   }

   public final DataPoint setIntValues(int... var1) {
      this.zzaU(var1.length);

      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.zzaTx[var2].setInt(var1[var2]);
      }

      return this;
   }

   private final void zzaU(int var1) {
      List var2;
      int var3 = (var2 = this.getDataType().getFields()).size();
      zzbo.zzb(var1 == var3, "Attempting to insert %s values, but needed %s: %s", new Object[]{var1, var3, var2});
   }

   public final DataType getDataType() {
      return this.zzaTa.getDataType();
   }

   public final DataSource getDataSource() {
      return this.zzaTa;
   }

   public final DataSource getOriginalDataSource() {
      return this.zzaTy != null ? this.zzaTy : this.zzaTa;
   }

   public final DataSource zztH() {
      return this.zzaTy;
   }

   public final long getTimestamp(TimeUnit var1) {
      return var1.convert(this.zzaTv, TimeUnit.NANOSECONDS);
   }

   public final long zztI() {
      return this.zzaTz;
   }

   public final long zztJ() {
      return this.zzaTA;
   }

   public final long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzaTw, TimeUnit.NANOSECONDS);
   }

   public final long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTv, TimeUnit.NANOSECONDS);
   }

   public final void zztK() {
      DataSource var1 = this.getDataSource();
      zzbo.zzb(this.getDataType().getName().equals(var1.getDataType().getName()), "Conflicting data types found %s vs %s", new Object[]{this.getDataType(), this.getDataType()});
      zzbo.zzb(this.zzaTv > 0L, "Data point does not have the timestamp set: %s", new Object[]{this});
      zzbo.zzb(this.zzaTw <= this.zzaTv, "Data point with start time greater than end time found: %s", new Object[]{this});
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataPoint) {
            DataPoint var3 = (DataPoint)var1;
            if (zzbe.equal(this.zzaTa, var3.zzaTa) && this.zzaTv == var3.zzaTv && this.zzaTw == var3.zzaTw && Arrays.equals(this.zzaTx, var3.zzaTx) && zzbe.equal(this.getOriginalDataSource(), var3.getOriginalDataSource())) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaTa, this.zzaTv, this.zzaTw});
   }

   public final String toString() {
      return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", Arrays.toString(this.zzaTx), this.zzaTw, this.zzaTv, this.zzaTz, this.zzaTA, this.zzaTa.toDebugString(), this.zzaTy != null ? this.zzaTy.toDebugString() : "N/A");
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaTv);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaTw);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaTx, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaTy, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaTz);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaTA);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   private static long zza(Long var0, long var1) {
      return var0 != null ? var0.longValue() : 0L;
   }
}
