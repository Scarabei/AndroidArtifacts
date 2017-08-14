package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzz implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new RawDataPoint[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      Value[] var9 = null;
      int var10 = 0;
      int var11 = 0;
      long var12 = 0L;
      long var14 = 0L;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 3:
            var9 = (Value[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16, Value.CREATOR);
            break;
         case 4:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 7:
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
      return new RawDataPoint(var4, var5, var7, var9, var10, var11, var12, var14);
   }
}
