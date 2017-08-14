package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new LocationRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 102;
      long var5 = 3600000L;
      long var7 = 600000L;
      boolean var9 = false;
      long var10 = Long.MAX_VALUE;
      int var12 = Integer.MAX_VALUE;
      float var13 = 0.0F;
      long var14 = 0L;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var16);
            break;
         case 5:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 7:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 8:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new LocationRequest(var4, var5, var7, var9, var10, var12, var13, var14);
   }
}
