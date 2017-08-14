package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zza[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      float var4 = 0.0F;
      float var5 = 0.0F;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      float var9 = 0.0F;
      float var10 = 0.0F;
      Bundle var11 = null;
      float var12 = 0.0F;
      float var13 = 0.0F;
      float var14 = 0.0F;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var15);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 11:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zza(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
   }
}
