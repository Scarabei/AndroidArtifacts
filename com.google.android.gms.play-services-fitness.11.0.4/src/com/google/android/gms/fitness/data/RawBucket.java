package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
public final class RawBucket extends com.google.android.gms.common.internal.safeparcel.zza {
   private int zzaku;
   public final long zzagZ;
   public final long zzaTo;
   public final Session zzaTe;
   public final int zzaUV;
   public final List zzaTq;
   public final int zzaTr;
   public final boolean zzaTs;
   public static final Creator CREATOR = new zzy();

   public RawBucket(int var1, long var2, long var4, Session var6, int var7, List var8, int var9, boolean var10) {
      this.zzaku = var1;
      this.zzagZ = var2;
      this.zzaTo = var4;
      this.zzaTe = var6;
      this.zzaUV = var7;
      this.zzaTq = var8;
      this.zzaTr = var9;
      this.zzaTs = var10;
   }

   public RawBucket(Bucket var1, List var2, List var3) {
      this.zzaku = 2;
      this.zzagZ = var1.getStartTime(TimeUnit.MILLISECONDS);
      this.zzaTo = var1.getEndTime(TimeUnit.MILLISECONDS);
      this.zzaTe = var1.getSession();
      this.zzaUV = var1.zztD();
      this.zzaTr = var1.getBucketType();
      this.zzaTs = var1.zztE();
      List var4 = var1.getDataSets();
      this.zzaTq = new ArrayList(var4.size());
      Iterator var5 = var4.iterator();

      while(var5.hasNext()) {
         DataSet var6 = (DataSet)var5.next();
         this.zzaTq.add(new RawDataSet(var6, var2, var3));
      }

   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof RawBucket) {
            RawBucket var3 = (RawBucket)var1;
            if (this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && this.zzaUV == var3.zzaUV && zzbe.equal(this.zzaTq, var3.zzaTq) && this.zzaTr == var3.zzaTr && this.zzaTs == var3.zzaTs) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzagZ, this.zzaTo, this.zzaTr});
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("startTime", this.zzagZ).zzg("endTime", this.zzaTo).zzg("activity", this.zzaUV).zzg("dataSets", this.zzaTq).zzg("bucketType", this.zzaTr).zzg("serverHasMoreData", this.zzaTs).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaTe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaUV);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaTq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzaTr);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaTs);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
