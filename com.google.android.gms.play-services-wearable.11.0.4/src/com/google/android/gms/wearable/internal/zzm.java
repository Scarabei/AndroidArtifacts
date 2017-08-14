package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzm implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzl[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      byte var11 = 0;
      byte var12 = 0;
      byte var13 = 0;
      byte var14 = 0;
      String var15 = null;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var16);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var16);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var16);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var16);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzl(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
   }
}
