package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzbtm implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbtl[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      ArrayList var6 = null;
      boolean var7 = false;
      boolean var8 = false;
      boolean var9 = false;
      String var10 = null;
      String var11 = null;
      zzbtt var12 = null;
      zzbtr var13 = null;
      zzbtx var14 = null;
      zzbtz var15 = null;
      zzbub var16 = null;
      zzbtv var17 = null;
      zzbtp var18 = null;
      zzbtj var19 = null;
      zzbth var20 = null;

      while(var2.dataPosition() < var3) {
         int var21;
         switch((var21 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var21);
            break;
         case 3:
            var5 = zzb.zzq(var2, var21);
            break;
         case 4:
            var6 = zzb.zzC(var2, var21);
            break;
         case 5:
            var7 = zzb.zzc(var2, var21);
            break;
         case 6:
            var10 = zzb.zzq(var2, var21);
            break;
         case 7:
            var11 = zzb.zzq(var2, var21);
            break;
         case 8:
            var12 = (zzbtt)zzb.zza(var2, var21, zzbtt.CREATOR);
            break;
         case 9:
            var13 = (zzbtr)zzb.zza(var2, var21, zzbtr.CREATOR);
            break;
         case 10:
            var14 = (zzbtx)zzb.zza(var2, var21, zzbtx.CREATOR);
            break;
         case 11:
            var15 = (zzbtz)zzb.zza(var2, var21, zzbtz.CREATOR);
            break;
         case 12:
            var16 = (zzbub)zzb.zza(var2, var21, zzbub.CREATOR);
            break;
         case 13:
            var17 = (zzbtv)zzb.zza(var2, var21, zzbtv.CREATOR);
            break;
         case 14:
            var18 = (zzbtp)zzb.zza(var2, var21, zzbtp.CREATOR);
            break;
         case 15:
            var19 = (zzbtj)zzb.zza(var2, var21, zzbtj.CREATOR);
            break;
         case 16:
            var8 = zzb.zzc(var2, var21);
            break;
         case 17:
            var9 = zzb.zzc(var2, var21);
            break;
         case 18:
            var20 = (zzbth)zzb.zza(var2, var21, zzbth.CREATOR);
            break;
         default:
            zzb.zzb(var2, var21);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbtl(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20);
   }
}
