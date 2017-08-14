package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzx implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Goal.MetricObjective[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      double var6 = 0.0D;
      double var8 = 0.0D;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var10);
            break;
         case 3:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var10);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Goal.MetricObjective(var4, var5, var6, var8);
   }
}
