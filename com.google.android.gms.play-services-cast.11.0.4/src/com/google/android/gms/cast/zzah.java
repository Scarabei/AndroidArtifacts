package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzah implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MediaTrack[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      long var4 = 0L;
      int var6 = 0;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      int var11 = 0;
      String var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var13);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MediaTrack(var4, var6, var7, var8, var9, var10, var11, var12);
   }
}
