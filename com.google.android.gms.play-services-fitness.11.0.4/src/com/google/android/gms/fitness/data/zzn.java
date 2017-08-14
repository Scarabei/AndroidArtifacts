package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzn implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataUpdateNotification[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      int var9 = 0;
      DataSource var10 = null;
      DataType var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var12);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var12);
            break;
         case 3:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 4:
            var10 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, DataSource.CREATOR);
            break;
         case 5:
            var11 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, DataType.CREATOR);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataUpdateNotification(var4, var5, var7, var9, var10, var11);
   }
}
