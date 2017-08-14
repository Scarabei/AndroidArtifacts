package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new AdBreakInfo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      long var4 = 0L;
      String var6 = null;
      long var7 = 0L;
      boolean var9 = false;
      String[] var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var11);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(var2, var11);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new AdBreakInfo(var4, var6, var7, var9, var10);
   }
}
