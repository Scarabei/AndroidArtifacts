package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSet;

public final class zzk implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzj[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataSet var5 = null;
      IBinder var6 = null;
      boolean var7 = false;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataSet)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, DataSet.CREATOR);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var8);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzj(var4, var5, var6, var7);
   }
}
