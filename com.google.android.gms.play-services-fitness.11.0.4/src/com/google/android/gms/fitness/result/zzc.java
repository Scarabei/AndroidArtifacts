package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataReadResult[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var3 = var1;
      zzc var2 = this;
      int var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var5 = 0;
      ArrayList var6 = new ArrayList();
      Status var7 = null;
      ArrayList var8 = new ArrayList();
      int var9 = 0;
      ArrayList var10 = null;
      ArrayList var11 = null;

      while(var3.dataPosition() < var4) {
         int var12;
         switch((var12 = var3.readInt()) & 65535) {
         case 1:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var12, var6, var2.getClass().getClassLoader());
            break;
         case 2:
            var7 = (Status)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var12, Status.CREATOR);
            break;
         case 3:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var12, var8, var2.getClass().getClassLoader());
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var12);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var12, DataSource.CREATOR);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var12, DataType.CREATOR);
            break;
         case 1000:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var3, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var3, var4);
      return new DataReadResult(var5, var6, var7, var8, var9, var10, var11);
   }
}
