package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSet;

public final class zzy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataUpdateRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      DataSet var9 = null;
      IBinder var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 3:
            var9 = (DataSet)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, DataSet.CREATOR);
            break;
         case 4:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var11);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var11);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataUpdateRequest(var4, var5, var7, var9, var10);
   }
}
