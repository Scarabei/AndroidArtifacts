package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzk implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataSource[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataType var5 = null;
      String var6 = null;
      int var7 = 0;
      Device var8 = null;
      zzb var9 = null;
      String var10 = null;
      int[] var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, DataType.CREATOR);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 4:
            var8 = (Device)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, Device.CREATOR);
            break;
         case 5:
            var9 = (zzb)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, zzb.CREATOR);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzw(var2, var12);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataSource(var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
