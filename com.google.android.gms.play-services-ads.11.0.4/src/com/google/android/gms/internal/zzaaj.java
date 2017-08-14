package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzaaj implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaai[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      ArrayList var7 = null;
      int var8 = 0;
      ArrayList var9 = null;
      long var10 = 0L;
      boolean var12 = false;
      long var13 = 0L;
      ArrayList var15 = null;
      long var16 = 0L;
      int var18 = 0;
      String var19 = null;
      long var20 = 0L;
      String var22 = null;
      boolean var23 = false;
      String var24 = null;
      String var25 = null;
      boolean var26 = false;
      boolean var27 = false;
      boolean var28 = false;
      boolean var29 = false;
      boolean var30 = false;
      zzaau var31 = null;
      String var32 = null;
      String var33 = null;
      boolean var34 = false;
      boolean var35 = false;
      zzaee var36 = null;
      ArrayList var37 = null;
      ArrayList var38 = null;
      boolean var39 = false;
      zzaak var40 = null;
      boolean var41 = false;
      String var42 = null;
      ArrayList var43 = null;
      boolean var44 = false;
      String var45 = null;
      zzaeq var46 = null;
      String var47 = null;
      boolean var48 = false;
      boolean var49 = false;
      Bundle var50 = null;

      while(var2.dataPosition() < var3) {
         int var51;
         switch((var51 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var51);
            break;
         case 2:
            var5 = zzb.zzq(var2, var51);
            break;
         case 3:
            var6 = zzb.zzq(var2, var51);
            break;
         case 4:
            var7 = zzb.zzC(var2, var51);
            break;
         case 5:
            var8 = zzb.zzg(var2, var51);
            break;
         case 6:
            var9 = zzb.zzC(var2, var51);
            break;
         case 7:
            var10 = zzb.zzi(var2, var51);
            break;
         case 8:
            var12 = zzb.zzc(var2, var51);
            break;
         case 9:
            var13 = zzb.zzi(var2, var51);
            break;
         case 10:
            var15 = zzb.zzC(var2, var51);
            break;
         case 11:
            var16 = zzb.zzi(var2, var51);
            break;
         case 12:
            var18 = zzb.zzg(var2, var51);
            break;
         case 13:
            var19 = zzb.zzq(var2, var51);
            break;
         case 14:
            var20 = zzb.zzi(var2, var51);
            break;
         case 15:
            var22 = zzb.zzq(var2, var51);
            break;
         case 16:
         case 17:
         case 20:
         case 27:
         case 41:
         default:
            zzb.zzb(var2, var51);
            break;
         case 18:
            var23 = zzb.zzc(var2, var51);
            break;
         case 19:
            var24 = zzb.zzq(var2, var51);
            break;
         case 21:
            var25 = zzb.zzq(var2, var51);
            break;
         case 22:
            var26 = zzb.zzc(var2, var51);
            break;
         case 23:
            var27 = zzb.zzc(var2, var51);
            break;
         case 24:
            var28 = zzb.zzc(var2, var51);
            break;
         case 25:
            var29 = zzb.zzc(var2, var51);
            break;
         case 26:
            var30 = zzb.zzc(var2, var51);
            break;
         case 28:
            var31 = (zzaau)zzb.zza(var2, var51, zzaau.CREATOR);
            break;
         case 29:
            var32 = zzb.zzq(var2, var51);
            break;
         case 30:
            var33 = zzb.zzq(var2, var51);
            break;
         case 31:
            var34 = zzb.zzc(var2, var51);
            break;
         case 32:
            var35 = zzb.zzc(var2, var51);
            break;
         case 33:
            var36 = (zzaee)zzb.zza(var2, var51, zzaee.CREATOR);
            break;
         case 34:
            var37 = zzb.zzC(var2, var51);
            break;
         case 35:
            var38 = zzb.zzC(var2, var51);
            break;
         case 36:
            var39 = zzb.zzc(var2, var51);
            break;
         case 37:
            var40 = (zzaak)zzb.zza(var2, var51, zzaak.CREATOR);
            break;
         case 38:
            var41 = zzb.zzc(var2, var51);
            break;
         case 39:
            var42 = zzb.zzq(var2, var51);
            break;
         case 40:
            var43 = zzb.zzC(var2, var51);
            break;
         case 42:
            var44 = zzb.zzc(var2, var51);
            break;
         case 43:
            var45 = zzb.zzq(var2, var51);
            break;
         case 44:
            var46 = (zzaeq)zzb.zza(var2, var51, zzaeq.CREATOR);
            break;
         case 45:
            var47 = zzb.zzq(var2, var51);
            break;
         case 46:
            var48 = zzb.zzc(var2, var51);
            break;
         case 47:
            var49 = zzb.zzc(var2, var51);
            break;
         case 48:
            var50 = zzb.zzs(var2, var51);
         }
      }

      zzb.zzF(var2, var3);
      return new zzaai(var4, var5, var6, var7, var8, var9, var10, var12, var13, var15, var16, var18, var19, var20, var22, var23, var24, var25, var26, var27, var28, var29, var30, var31, var32, var33, var34, var35, var36, var37, var38, var39, var40, var41, var42, var43, var44, var45, var46, var47, var48, var49, var50);
   }
}
