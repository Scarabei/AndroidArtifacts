package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzaag implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaae[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      Bundle var5 = null;
      zzir var6 = null;
      zziv var7 = null;
      String var8 = null;
      ApplicationInfo var9 = null;
      PackageInfo var10 = null;
      String var11 = null;
      String var12 = null;
      String var13 = null;
      zzaje var14 = null;
      Bundle var15 = null;
      int var16 = 0;
      ArrayList var17 = null;
      Bundle var18 = null;
      boolean var19 = false;
      int var20 = 0;
      int var21 = 0;
      float var22 = 0.0F;
      String var23 = null;
      long var24 = 0L;
      String var26 = null;
      ArrayList var27 = null;
      String var28 = null;
      zzon var29 = null;
      ArrayList var30 = null;
      long var31 = 0L;
      String var33 = null;
      float var34 = 0.0F;
      boolean var35 = false;
      int var36 = 0;
      int var37 = 0;
      boolean var38 = false;
      boolean var39 = false;
      String var40 = null;
      String var41 = null;
      boolean var42 = false;
      int var43 = 0;
      Bundle var44 = null;
      String var45 = null;
      zzky var46 = null;
      boolean var47 = false;
      Bundle var48 = null;
      String var49 = null;
      String var50 = null;
      String var51 = null;
      boolean var52 = false;
      ArrayList var53 = null;
      String var54 = null;
      ArrayList var55 = null;

      while(var2.dataPosition() < var3) {
         int var56;
         switch((var56 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var56);
            break;
         case 2:
            var5 = zzb.zzs(var2, var56);
            break;
         case 3:
            var6 = (zzir)zzb.zza(var2, var56, zzir.CREATOR);
            break;
         case 4:
            var7 = (zziv)zzb.zza(var2, var56, zziv.CREATOR);
            break;
         case 5:
            var8 = zzb.zzq(var2, var56);
            break;
         case 6:
            var9 = (ApplicationInfo)zzb.zza(var2, var56, ApplicationInfo.CREATOR);
            break;
         case 7:
            var10 = (PackageInfo)zzb.zza(var2, var56, PackageInfo.CREATOR);
            break;
         case 8:
            var11 = zzb.zzq(var2, var56);
            break;
         case 9:
            var12 = zzb.zzq(var2, var56);
            break;
         case 10:
            var13 = zzb.zzq(var2, var56);
            break;
         case 11:
            var14 = (zzaje)zzb.zza(var2, var56, zzaje.CREATOR);
            break;
         case 12:
            var15 = zzb.zzs(var2, var56);
            break;
         case 13:
            var16 = zzb.zzg(var2, var56);
            break;
         case 14:
            var17 = zzb.zzC(var2, var56);
            break;
         case 15:
            var18 = zzb.zzs(var2, var56);
            break;
         case 16:
            var19 = zzb.zzc(var2, var56);
            break;
         case 17:
         case 22:
         case 23:
         case 24:
         case 32:
         default:
            zzb.zzb(var2, var56);
            break;
         case 18:
            var20 = zzb.zzg(var2, var56);
            break;
         case 19:
            var21 = zzb.zzg(var2, var56);
            break;
         case 20:
            var22 = zzb.zzl(var2, var56);
            break;
         case 21:
            var23 = zzb.zzq(var2, var56);
            break;
         case 25:
            var24 = zzb.zzi(var2, var56);
            break;
         case 26:
            var26 = zzb.zzq(var2, var56);
            break;
         case 27:
            var27 = zzb.zzC(var2, var56);
            break;
         case 28:
            var28 = zzb.zzq(var2, var56);
            break;
         case 29:
            var29 = (zzon)zzb.zza(var2, var56, zzon.CREATOR);
            break;
         case 30:
            var30 = zzb.zzC(var2, var56);
            break;
         case 31:
            var31 = zzb.zzi(var2, var56);
            break;
         case 33:
            var33 = zzb.zzq(var2, var56);
            break;
         case 34:
            var34 = zzb.zzl(var2, var56);
            break;
         case 35:
            var36 = zzb.zzg(var2, var56);
            break;
         case 36:
            var37 = zzb.zzg(var2, var56);
            break;
         case 37:
            var38 = zzb.zzc(var2, var56);
            break;
         case 38:
            var39 = zzb.zzc(var2, var56);
            break;
         case 39:
            var40 = zzb.zzq(var2, var56);
            break;
         case 40:
            var35 = zzb.zzc(var2, var56);
            break;
         case 41:
            var41 = zzb.zzq(var2, var56);
            break;
         case 42:
            var42 = zzb.zzc(var2, var56);
            break;
         case 43:
            var43 = zzb.zzg(var2, var56);
            break;
         case 44:
            var44 = zzb.zzs(var2, var56);
            break;
         case 45:
            var45 = zzb.zzq(var2, var56);
            break;
         case 46:
            var46 = (zzky)zzb.zza(var2, var56, zzky.CREATOR);
            break;
         case 47:
            var47 = zzb.zzc(var2, var56);
            break;
         case 48:
            var48 = zzb.zzs(var2, var56);
            break;
         case 49:
            var49 = zzb.zzq(var2, var56);
            break;
         case 50:
            var50 = zzb.zzq(var2, var56);
            break;
         case 51:
            var51 = zzb.zzq(var2, var56);
            break;
         case 52:
            var52 = zzb.zzc(var2, var56);
            break;
         case 53:
            var53 = zzb.zzB(var2, var56);
            break;
         case 54:
            var54 = zzb.zzq(var2, var56);
            break;
         case 55:
            var55 = zzb.zzC(var2, var56);
         }
      }

      zzb.zzF(var2, var3);
      return new zzaae(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var26, var27, var28, var29, var30, var31, var33, var34, var35, var36, var37, var38, var39, var40, var41, var42, var43, var44, var45, var46, var47, var48, var49, var50, var51, var52, var53, var54, var55);
   }
}
