package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzab implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Goal.Recurrence[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var7);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var7);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var7);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var7);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Goal.Recurrence(var4, var5, var6);
   }
}
