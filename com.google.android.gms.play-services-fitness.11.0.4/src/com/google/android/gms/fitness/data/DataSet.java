package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbuj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DataSet extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   private final int versionCode;
   private final DataSource zzaTa;
   private final DataType zzaSZ;
   private final List zzaTB;
   private final List zzaTC;
   private boolean zzaTD = false;
   public static final Creator CREATOR = new zzi();

   DataSet(int var1, DataSource var2, DataType var3, List var4, List var5, boolean var6) {
      this.versionCode = var1;
      this.zzaTa = var2;
      this.zzaSZ = var2.getDataType();
      this.zzaTD = var6;
      this.zzaTB = new ArrayList(var4.size());
      this.zzaTC = var1 >= 2 ? var5 : Collections.singletonList(var2);
      Iterator var7 = var4.iterator();

      while(var7.hasNext()) {
         RawDataPoint var8 = (RawDataPoint)var7.next();
         this.zzaTB.add(new DataPoint(this.zzaTC, var8));
      }

   }

   private DataSet(DataSource var1) {
      this.versionCode = 3;
      this.zzaTa = (DataSource)zzbo.zzu(var1);
      this.zzaSZ = var1.getDataType();
      this.zzaTB = new ArrayList();
      this.zzaTC = new ArrayList();
      this.zzaTC.add(this.zzaTa);
   }

   public DataSet(RawDataSet var1, List var2) {
      this.versionCode = 3;
      int var7 = var1.zzaUZ;
      this.zzaTa = (DataSource)(var7 >= 0 && var7 < var2.size() ? var2.get(var7) : null);
      this.zzaSZ = this.zzaTa.getDataType();
      this.zzaTC = var2;
      this.zzaTD = var1.zzaTs;
      List var3 = var1.zzaVe;
      this.zzaTB = new ArrayList(var3.size());
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         RawDataPoint var5 = (RawDataPoint)var4.next();
         this.zzaTB.add(new DataPoint(this.zzaTC, var5));
      }

   }

   public static DataSet create(DataSource var0) {
      zzbo.zzb(var0, "DataSource should be specified");
      return new DataSet(var0);
   }

   public final DataPoint createDataPoint() {
      return DataPoint.create(this.zzaTa);
   }

   public final void add(DataPoint var1) {
      DataSource var2;
      zzbo.zzb((var2 = var1.getDataSource()).getStreamIdentifier().equals(this.zzaTa.getStreamIdentifier()), "Conflicting data sources found %s vs %s", new Object[]{var2, this.zzaTa});
      var1.zztK();
      zzb(var1);
      this.zza(var1);
   }

   private final void zza(DataPoint var1) {
      this.zzaTB.add(var1);
      DataSource var2;
      if ((var2 = var1.getOriginalDataSource()) != null && !this.zzaTC.contains(var2)) {
         this.zzaTC.add(var2);
      }

   }

   public final void addAll(Iterable var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         DataPoint var3 = (DataPoint)var2.next();
         this.add(var3);
      }

   }

   public final void zzb(Iterable var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         DataPoint var3 = (DataPoint)var2.next();
         this.zza(var3);
      }

   }

   public final DataSource getDataSource() {
      return this.zzaTa;
   }

   public final DataType getDataType() {
      return this.zzaTa.getDataType();
   }

   public final List getDataPoints() {
      return Collections.unmodifiableList(this.zzaTB);
   }

   public final boolean isEmpty() {
      return this.zzaTB.isEmpty();
   }

   public final boolean zztE() {
      return this.zzaTD;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof DataSet) {
            DataSet var3 = (DataSet)var1;
            if (zzbe.equal(this.getDataType(), var3.getDataType()) && zzbe.equal(this.zzaTa, var3.zzaTa) && zzbe.equal(this.zzaTB, var3.zzaTB) && this.zzaTD == var3.zzaTD) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaTa});
   }

   public final String toString() {
      List var1 = this.zztL();
      return String.format("DataSet{%s %s}", this.zzaTa.toDebugString(), this.zzaTB.size() < 10 ? var1 : String.format("%d data points, first 5: %s", this.zzaTB.size(), var1.subList(0, 5)));
   }

   public static void zzb(DataPoint var0) throws IllegalArgumentException {
      String var1;
      if ((var1 = zzbuj.zza(var0, zzf.zzaTt)) != null) {
         String var2 = String.valueOf(var0);
         Log.w("Fitness", (new StringBuilder(20 + String.valueOf(var2).length())).append("Invalid data point: ").append(var2).toString());
         throw new IllegalArgumentException(var1);
      }
   }

   private List zztL() {
      return this.zzA(this.zzaTC);
   }

   final List zzA(List var1) {
      ArrayList var2 = new ArrayList(this.zzaTB.size());
      Iterator var3 = this.zzaTB.iterator();

      while(var3.hasNext()) {
         DataPoint var4 = (DataPoint)var3.next();
         var2.add(new RawDataPoint(var4, var1));
      }

      return var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getDataSource(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDataType(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 3, this.zztL(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaTC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzaTD);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
