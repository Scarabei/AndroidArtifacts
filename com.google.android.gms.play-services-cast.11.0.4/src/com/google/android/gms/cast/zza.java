package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new AdBreakClipInfo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      long var6 = 0L;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var12);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new AdBreakClipInfo(var4, var5, var6, var8, var9, var10, var11);
   }
}
