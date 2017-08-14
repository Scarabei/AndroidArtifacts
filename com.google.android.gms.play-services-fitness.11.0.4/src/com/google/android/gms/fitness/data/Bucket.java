package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bucket extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int TYPE_TIME = 1;
   public static final int TYPE_SESSION = 2;
   public static final int TYPE_ACTIVITY_TYPE = 3;
   public static final int TYPE_ACTIVITY_SEGMENT = 4;
   private final int zzaku;
   private final long zzagZ;
   private final long zzaTo;
   private final Session zzaTe;
   private final int zzaTp;
   private final List zzaTq;
   private final int zzaTr;
   private boolean zzaTs;
   public static final Creator CREATOR = new zze();

   Bucket(int var1, long var2, long var4, Session var6, int var7, List var8, int var9, boolean var10) {
      this.zzaTs = false;
      this.zzaku = var1;
      this.zzagZ = var2;
      this.zzaTo = var4;
      this.zzaTe = var6;
      this.zzaTp = var7;
      this.zzaTq = var8;
      this.zzaTr = var9;
      this.zzaTs = var10;
   }

   public Bucket(RawBucket var1, List var2) {
      this(2, var1.zzagZ, var1.zzaTo, var1.zzaTe, var1.zzaUV, zza(var1.zzaTq, var2), var1.zzaTr, var1.zzaTs);
   }

   private static List zza(Collection var0, List var1) {
      ArrayList var2 = new ArrayList(var0.size());
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         RawDataSet var4 = (RawDataSet)var3.next();
         var2.add(new DataSet(var4, var1));
      }

      return var2;
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public Session getSession() {
      return this.zzaTe;
   }

   public String getActivity() {
      return com.google.android.gms.fitness.zza.getName(this.zzaTp);
   }

   public final int zztD() {
      return this.zzaTp;
   }

   public List getDataSets() {
      return this.zzaTq;
   }

   public DataSet getDataSet(DataType var1) {
      Iterator var2 = this.zzaTq.iterator();

      DataSet var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }
      } while(!(var3 = (DataSet)var2.next()).getDataType().equals(var1));

      return var3;
   }

   public int getBucketType() {
      return this.zzaTr;
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof Bucket) {
            Bucket var3 = (Bucket)var1;
            if (this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && this.zzaTp == var3.zzaTp && zzbe.equal(this.zzaTq, var3.zzaTq) && this.zzaTr == var3.zzaTr && this.zzaTs == var3.zzaTs) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final boolean zza(Bucket var1) {
      return this.zzagZ == var1.zzagZ && this.zzaTo == var1.zzaTo && this.zzaTp == var1.zzaTp && this.zzaTr == var1.zzaTr;
   }

   public final boolean zztE() {
      if (this.zzaTs) {
         return true;
      } else {
         Iterator var1 = this.zzaTq.iterator();

         do {
            if (!var1.hasNext()) {
               return false;
            }
         } while(!((DataSet)var1.next()).zztE());

         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzagZ, this.zzaTo, this.zzaTp, this.zzaTr});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("startTime", this.zzagZ).zzg("endTime", this.zzaTo).zzg("activity", this.zzaTp).zzg("dataSets", this.zzaTq).zzg("bucketType", zzaS(this.zzaTr)).zzg("serverHasMoreData", this.zzaTs).toString();
   }

   public static String zzaS(int var0) {
      switch(var0) {
      case 0:
         return "unknown";
      case 1:
         return "time";
      case 2:
         return "session";
      case 3:
         return "type";
      case 4:
         return "segment";
      default:
         return "bug";
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getSession(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaTp);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getDataSets(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getBucketType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zztE());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
