package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataReadResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   private final int zzaku;
   private final List zzaTq;
   private final Status mStatus;
   private final List zzaXu;
   private int zzaXv;
   private final List zzaXw;
   private final List zzaXx;
   public static final Creator CREATOR = new zzc();

   DataReadResult(int var1, List var2, Status var3, List var4, int var5, List var6, List var7) {
      this.zzaku = var1;
      this.mStatus = var3;
      this.zzaXv = var5;
      this.zzaXw = var6;
      this.zzaXx = var7;
      this.zzaTq = new ArrayList(var2.size());
      Iterator var8 = var2.iterator();

      while(var8.hasNext()) {
         RawDataSet var9 = (RawDataSet)var8.next();
         this.zzaTq.add(new DataSet(var9, var6));
      }

      this.zzaXu = new ArrayList(var4.size());
      var8 = var4.iterator();

      while(var8.hasNext()) {
         RawBucket var10 = (RawBucket)var8.next();
         this.zzaXu.add(new Bucket(var10, var6));
      }

   }

   private DataReadResult(List var1, List var2, Status var3) {
      this.zzaku = 5;
      this.zzaTq = var1;
      this.mStatus = var3;
      this.zzaXu = var2;
      this.zzaXv = 1;
      this.zzaXw = new ArrayList();
      this.zzaXx = new ArrayList();
   }

   public static DataReadResult zza(Status var0, List var1, List var2) {
      ArrayList var3 = new ArrayList();
      Iterator var4 = var2.iterator();

      while(var4.hasNext()) {
         DataSource var5 = (DataSource)var4.next();
         var3.add(DataSet.create(var5));
      }

      var4 = var1.iterator();

      while(var4.hasNext()) {
         DataType var7 = (DataType)var4.next();
         DataSource var6 = (new DataSource.Builder()).setDataType(var7).setType(1).setName("Default").build();
         var3.add(DataSet.create(var6));
      }

      return new DataReadResult(var3, Collections.emptyList(), var0);
   }

   public DataSet getDataSet(DataType var1) {
      Iterator var2 = this.zzaTq.iterator();

      DataSet var3;
      do {
         if (!var2.hasNext()) {
            return DataSet.create((new DataSource.Builder()).setDataType(var1).setType(1).build());
         }

         var3 = (DataSet)var2.next();
      } while(!var1.equals(var3.getDataType()));

      return var3;
   }

   public DataSet getDataSet(DataSource var1) {
      Iterator var2 = this.zzaTq.iterator();

      DataSet var3;
      do {
         if (!var2.hasNext()) {
            return DataSet.create(var1);
         }

         var3 = (DataSet)var2.next();
      } while(!var1.equals(var3.getDataSource()));

      return var3;
   }

   public List getDataSets() {
      return this.zzaTq;
   }

   public List getBuckets() {
      return this.zzaXu;
   }

   public final int zztX() {
      return this.zzaXv;
   }

   public final void zzb(DataReadResult var1) {
      Iterator var2 = var1.getDataSets().iterator();

      while(var2.hasNext()) {
         zza((DataSet)var2.next(), this.zzaTq);
      }

      var2 = var1.getBuckets().iterator();

      while(true) {
         label27:
         while(var2.hasNext()) {
            Bucket var3 = (Bucket)var2.next();
            List var6 = this.zzaXu;
            Bucket var5 = var3;
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
               Bucket var8;
               if ((var8 = (Bucket)var7.next()).zza(var5)) {
                  Iterator var9 = var5.getDataSets().iterator();

                  while(var9.hasNext()) {
                     zza((DataSet)var9.next(), var8.getDataSets());
                  }
                  continue label27;
               }
            }

            this.zzaXu.add(var5);
         }

         return;
      }
   }

   private static void zza(DataSet var0, List var1) {
      Iterator var2 = var1.iterator();

      DataSet var3;
      do {
         if (!var2.hasNext()) {
            var1.add(var0);
            return;
         }
      } while(!(var3 = (DataSet)var2.next()).getDataSource().equals(var0.getDataSource()));

      var3.zzb((Iterable)var0.getDataPoints());
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof DataReadResult) {
            DataReadResult var3 = (DataReadResult)var1;
            if (this.mStatus.equals(var3.mStatus) && zzbe.equal(this.zzaTq, var3.zzaTq) && zzbe.equal(this.zzaXu, var3.zzaXu)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.mStatus, this.zzaTq, this.zzaXu});
   }

   public String toString() {
      zzbg var10000 = zzbe.zzt(this).zzg("status", this.mStatus);
      int var1;
      Object var10002;
      if (this.zzaTq.size() > 5) {
         var1 = this.zzaTq.size();
         var10002 = (new StringBuilder(21)).append(var1).append(" data sets").toString();
      } else {
         var10002 = this.zzaTq;
      }

      var10000 = var10000.zzg("dataSets", var10002);
      if (this.zzaXu.size() > 5) {
         var1 = this.zzaXu.size();
         var10002 = (new StringBuilder(19)).append(var1).append(" buckets").toString();
      } else {
         var10002 = this.zzaXu;
      }

      return var10000.zzg("buckets", var10002).toString();
   }

   private List zztY() {
      ArrayList var1 = new ArrayList(this.zzaXu.size());
      Iterator var2 = this.zzaXu.iterator();

      while(var2.hasNext()) {
         Bucket var3 = (Bucket)var2.next();
         var1.add(new RawBucket(var3, this.zzaXw, this.zzaXx));
      }

      return var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      DataReadResult var6 = this;
      ArrayList var7 = new ArrayList(this.zzaTq.size());
      Iterator var8 = this.zzaTq.iterator();

      while(var8.hasNext()) {
         DataSet var9 = (DataSet)var8.next();
         var7.add(new RawDataSet(var9, var6.zzaXw, var6.zzaXx));
      }

      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 1, var7, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzd(var1, 3, this.zztY(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaXv);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzaXw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzaXx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
