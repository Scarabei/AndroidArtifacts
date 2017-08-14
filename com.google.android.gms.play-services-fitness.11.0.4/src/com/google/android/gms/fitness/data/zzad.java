package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzad implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Session[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      int var12 = 0;
      zzb var13 = null;
      Long var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var15);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var15);
            break;
         case 3:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 4:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 7:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 8:
            var13 = (zzb)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, zzb.CREATOR);
            break;
         case 9:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzj(var2, var15);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Session(var4, var5, var7, var9, var10, var11, var12, var13, var14);
   }
}
