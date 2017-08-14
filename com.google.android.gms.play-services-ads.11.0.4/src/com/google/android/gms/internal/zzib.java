package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzib implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzia[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      long var5 = 0L;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      Bundle var10 = null;
      boolean var11 = false;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var12);
            break;
         case 3:
            var5 = zzb.zzi(var2, var12);
            break;
         case 4:
            var7 = zzb.zzq(var2, var12);
            break;
         case 5:
            var8 = zzb.zzq(var2, var12);
            break;
         case 6:
            var9 = zzb.zzq(var2, var12);
            break;
         case 7:
            var10 = zzb.zzs(var2, var12);
            break;
         case 8:
            var11 = zzb.zzc(var2, var12);
            break;
         default:
            zzb.zzb(var2, var12);
         }
      }

      zzb.zzF(var2, var3);
      return new zzia(var4, var5, var7, var8, var9, var10, var11);
   }
}
