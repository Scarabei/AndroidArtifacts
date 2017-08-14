package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzo implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzn[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String[] var6 = null;
      String[] var7 = null;
      String[] var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      PlusCommonExtras var13 = null;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(var2, var14);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(var2, var14);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(var2, var14);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var14);
            break;
         case 9:
            var13 = (PlusCommonExtras)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, PlusCommonExtras.CREATOR);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var14);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzn(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }
}
