package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzlu implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzlt[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      String var12 = null;
      int var13 = 0;
      String var14 = null;
      int var15 = 0;
      int var16 = 0;
      String var17 = null;

      while(var2.dataPosition() < var3) {
         int var18;
         switch((var18 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var18);
            break;
         case 3:
            var5 = zzb.zzg(var2, var18);
            break;
         case 4:
            var6 = zzb.zzg(var2, var18);
            break;
         case 5:
            var7 = zzb.zzg(var2, var18);
            break;
         case 6:
            var8 = zzb.zzg(var2, var18);
            break;
         case 7:
            var9 = zzb.zzg(var2, var18);
            break;
         case 8:
            var10 = zzb.zzg(var2, var18);
            break;
         case 9:
            var11 = zzb.zzg(var2, var18);
            break;
         case 10:
            var12 = zzb.zzq(var2, var18);
            break;
         case 11:
            var13 = zzb.zzg(var2, var18);
            break;
         case 12:
            var14 = zzb.zzq(var2, var18);
            break;
         case 13:
            var15 = zzb.zzg(var2, var18);
            break;
         case 14:
            var16 = zzb.zzg(var2, var18);
            break;
         case 15:
            var17 = zzb.zzq(var2, var18);
            break;
         default:
            zzb.zzb(var2, var18);
         }
      }

      zzb.zzF(var2, var3);
      return new zzlt(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
   }
}
