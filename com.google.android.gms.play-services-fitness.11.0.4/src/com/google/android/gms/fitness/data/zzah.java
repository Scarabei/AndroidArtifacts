package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzah implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Subscription[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataSource var5 = null;
      DataType var6 = null;
      long var7 = 0L;
      int var9 = 0;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, DataSource.CREATOR);
            break;
         case 2:
            var6 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, DataType.CREATOR);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Subscription(var4, var5, var6, var7, var9);
   }
}
