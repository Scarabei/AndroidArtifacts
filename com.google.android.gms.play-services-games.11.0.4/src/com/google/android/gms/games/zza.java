package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class zza implements Creator {
   public GameEntity zzf(Parcel var1) {
      int var2 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var3 = null;
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      Uri var9 = null;
      Uri var10 = null;
      Uri var11 = null;
      boolean var12 = false;
      boolean var13 = false;
      String var14 = null;
      int var15 = 0;
      int var16 = 0;
      int var17 = 0;
      boolean var18 = false;
      boolean var19 = false;
      String var20 = null;
      String var21 = null;
      String var22 = null;
      boolean var23 = false;
      boolean var24 = false;
      boolean var25 = false;
      String var26 = null;
      boolean var27 = false;

      while(var1.dataPosition() < var2) {
         int var28;
         switch((var28 = var1.readInt()) & 65535) {
         case 1:
            var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 7:
            var9 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var28, Uri.CREATOR);
            break;
         case 8:
            var10 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var28, Uri.CREATOR);
            break;
         case 9:
            var11 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var28, Uri.CREATOR);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var28);
            break;
         case 14:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var28);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var28);
            break;
         case 16:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 17:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 18:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 19:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 20:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 21:
            var23 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 22:
            var24 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 23:
            var25 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         case 24:
            var26 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var28);
            break;
         case 25:
            var27 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var28);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var1, var28);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var1, var2);
      return new GameEntity(var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27);
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return new GameEntity[var1];
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.zzf(var1);
   }
}
