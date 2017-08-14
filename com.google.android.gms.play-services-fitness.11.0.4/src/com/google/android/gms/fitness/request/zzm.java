package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.ArrayList;

public final class zzm implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataReadRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      ArrayList var5 = null;
      ArrayList var6 = null;
      long var7 = 0L;
      long var9 = 0L;
      ArrayList var11 = null;
      ArrayList var12 = null;
      int var13 = 0;
      long var14 = 0L;
      DataSource var16 = null;
      int var17 = 0;
      boolean var18 = false;
      boolean var19 = false;
      IBinder var20 = null;
      ArrayList var21 = null;
      ArrayList var22 = null;

      while(var2.dataPosition() < var3) {
         int var23;
         switch((var23 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, DataType.CREATOR);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, DataSource.CREATOR);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var23);
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var23);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, DataType.CREATOR);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, DataSource.CREATOR);
            break;
         case 7:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var23);
            break;
         case 8:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var23);
            break;
         case 9:
            var16 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var23, DataSource.CREATOR);
            break;
         case 10:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var23);
            break;
         case 12:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23);
            break;
         case 13:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23);
            break;
         case 14:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var23);
            break;
         case 16:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var23, Device.CREATOR);
            break;
         case 17:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var23);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var23);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var23);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataReadRequest(var4, var5, var6, var7, var9, var11, var12, var13, var14, var16, var17, var18, var19, var20, var21, var22);
   }
}
