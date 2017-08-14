package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataSet[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var3 = var1;
      zzi var2 = this;
      int var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var5 = 0;
      DataSource var6 = null;
      DataType var7 = null;
      ArrayList var8 = new ArrayList();
      ArrayList var9 = null;
      boolean var10 = false;

      while(var3.dataPosition() < var4) {
         int var11;
         switch((var11 = var3.readInt()) & 65535) {
         case 1:
            var6 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var11, DataSource.CREATOR);
            break;
         case 2:
            var7 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var11, DataType.CREATOR);
            break;
         case 3:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var11, var8, var2.getClass().getClassLoader());
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var11, DataSource.CREATOR);
            break;
         case 5:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var11);
            break;
         case 1000:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var11);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var3, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var3, var4);
      return new DataSet(var5, var6, var7, var8, var9, var10);
   }
}
