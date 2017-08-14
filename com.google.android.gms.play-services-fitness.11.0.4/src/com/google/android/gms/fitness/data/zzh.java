package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzh implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataPoint[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataSource var5 = null;
      long var6 = 0L;
      long var8 = 0L;
      Value[] var10 = null;
      DataSource var11 = null;
      long var12 = 0L;
      long var14 = 0L;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, DataSource.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 5:
            var10 = (Value[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16, Value.CREATOR);
            break;
         case 6:
            var11 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, DataSource.CREATOR);
            break;
         case 7:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 8:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataPoint(var4, var5, var6, var8, var10, var11, var12, var14);
   }
}
