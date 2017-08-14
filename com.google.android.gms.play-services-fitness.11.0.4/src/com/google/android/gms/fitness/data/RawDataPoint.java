package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzbuf;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
public final class RawDataPoint extends com.google.android.gms.common.internal.safeparcel.zza {
   private int versionCode;
   public final long zzaUW;
   public final long zzaUX;
   public final Value[] zzaUY;
   public final int zzaUZ;
   public final int zzaVa;
   public final long zzaVb;
   public final long zzaVc;
   public static final Creator CREATOR = new zzz();

   public RawDataPoint(int var1, long var2, long var4, Value[] var6, int var7, int var8, long var9, long var11) {
      this.versionCode = var1;
      this.zzaUW = var2;
      this.zzaUX = var4;
      this.zzaUZ = var7;
      this.zzaVa = var8;
      this.zzaVb = var9;
      this.zzaVc = var11;
      this.zzaUY = var6;
   }

   RawDataPoint(DataPoint var1, List var2) {
      this.versionCode = 4;
      this.zzaUW = var1.getTimestamp(TimeUnit.NANOSECONDS);
      this.zzaUX = var1.getStartTime(TimeUnit.NANOSECONDS);
      this.zzaUY = var1.zztG();
      this.zzaUZ = zzbuf.zza(var1.getDataSource(), var2);
      this.zzaVa = zzbuf.zza(var1.zztH(), var2);
      this.zzaVb = var1.zztI();
      this.zzaVc = var1.zztJ();
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof RawDataPoint) {
            RawDataPoint var3 = (RawDataPoint)var1;
            if (this.zzaUW == var3.zzaUW && this.zzaUX == var3.zzaUX && Arrays.equals(this.zzaUY, var3.zzaUY) && this.zzaUZ == var3.zzaUZ && this.zzaVa == var3.zzaVa && this.zzaVb == var3.zzaVb) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUW, this.zzaUX});
   }

   public final String toString() {
      return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.zzaUY), this.zzaUX, this.zzaUW, this.zzaUZ, this.zzaVa);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUW);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUX);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaUY, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaUZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaVa);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaVb);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzaVc);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
