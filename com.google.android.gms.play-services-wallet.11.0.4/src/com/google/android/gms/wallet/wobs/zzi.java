package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzh[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      double var6 = 0.0D;
      String var8 = null;
      long var9 = 0L;
      int var11 = -1;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var12);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var12);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzh(var4, var5, var6, var8, var9, var11);
   }
}
