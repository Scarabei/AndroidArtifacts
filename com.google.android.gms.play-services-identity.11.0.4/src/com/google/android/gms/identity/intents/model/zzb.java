package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new UserAddress[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      String var13 = null;
      String var14 = null;
      String var15 = null;
      boolean var16 = false;
      String var17 = null;
      String var18 = null;

      while(var2.dataPosition() < var3) {
         int var19;
         switch((var19 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 14:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var19);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 16:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var19);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new UserAddress(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18);
   }
}
