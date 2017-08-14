package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public final class zzo implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataSourcesRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      ArrayList var5 = null;
      ArrayList var6 = null;
      boolean var7 = false;
      IBinder var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var9, DataType.CREATOR);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var9);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var9);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var9);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var9);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataSourcesRequest(var4, var5, var6, var7, var8);
   }
}
