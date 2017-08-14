package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new TextTrackStyle[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      float var4 = 0.0F;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      String var12 = null;
      int var13 = 0;
      int var14 = 0;
      String var15 = null;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new TextTrackStyle(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
   }
}
