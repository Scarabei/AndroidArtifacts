package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new WakeLockEvent[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      int var7 = 0;
      String var8 = null;
      int var9 = 0;
      ArrayList var10 = null;
      String var11 = null;
      long var12 = 0L;
      int var14 = 0;
      String var15 = null;
      String var16 = null;
      float var17 = 0.0F;
      long var18 = 0L;
      String var20 = null;

      while(var2.dataPosition() < var3) {
         int var21;
         switch((var21 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var21);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 3:
         case 7:
         case 9:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var21);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var21);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var21);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 10:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 11:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var21);
            break;
         case 12:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 13:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 14:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var21);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var21);
            break;
         case 16:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 17:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new WakeLockEvent(var4, var5, var7, var8, var9, var10, var11, var12, var14, var15, var16, var17, var18, var20);
   }
}
