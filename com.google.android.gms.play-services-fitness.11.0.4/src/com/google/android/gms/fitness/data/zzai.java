package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzai implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Value[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      boolean var6 = false;
      float var7 = 0.0F;
      String var8 = null;
      Bundle var9 = null;
      int[] var10 = null;
      float[] var11 = null;
      byte[] var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var13);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var13);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzw(var2, var13);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzy(var2, var13);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var13);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Value(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
