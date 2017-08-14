package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.HashSet;

public final class zzcrj implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcri[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      HashSet var4 = new HashSet();
      int var5 = 0;
      String var6 = null;
      zzcri.zza var7 = null;
      String var8 = null;
      String var9 = null;
      int var10 = 0;
      zzcri.zzb var11 = null;
      String var12 = null;
      String var13 = null;
      int var14 = 0;
      String var15 = null;
      zzcri.zzc var16 = null;
      boolean var17 = false;
      String var18 = null;
      zzcri.zzd var19 = null;
      String var20 = null;
      int var21 = 0;
      ArrayList var22 = null;
      ArrayList var23 = null;
      int var24 = 0;
      int var25 = 0;
      String var26 = null;
      String var27 = null;
      ArrayList var28 = null;
      boolean var29 = false;

      while(var2.dataPosition() < var3) {
         int var30;
         switch((var30 = var2.readInt()) & 65535) {
         case 1:
            var5 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(1));
            break;
         case 2:
            var6 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(2));
            break;
         case 3:
            var7 = (zzcri.zza)zzb.zza(var2, var30, zzcri.zza.CREATOR);
            var4.add(Integer.valueOf(3));
            break;
         case 4:
            var8 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(4));
            break;
         case 5:
            var9 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(5));
            break;
         case 6:
            var10 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(6));
            break;
         case 7:
            var11 = (zzcri.zzb)zzb.zza(var2, var30, zzcri.zzb.CREATOR);
            var4.add(Integer.valueOf(7));
            break;
         case 8:
            var12 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(8));
            break;
         case 9:
            var13 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(9));
            break;
         case 10:
         case 11:
         case 13:
         case 17:
         default:
            zzb.zzb(var2, var30);
            break;
         case 12:
            var14 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(12));
            break;
         case 14:
            var15 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(14));
            break;
         case 15:
            var16 = (zzcri.zzc)zzb.zza(var2, var30, zzcri.zzc.CREATOR);
            var4.add(Integer.valueOf(15));
            break;
         case 16:
            var17 = zzb.zzc(var2, var30);
            var4.add(Integer.valueOf(16));
            break;
         case 18:
            var18 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(18));
            break;
         case 19:
            var19 = (zzcri.zzd)zzb.zza(var2, var30, zzcri.zzd.CREATOR);
            var4.add(Integer.valueOf(19));
            break;
         case 20:
            var20 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(20));
            break;
         case 21:
            var21 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(21));
            break;
         case 22:
            var22 = zzb.zzc(var2, var30, zzcri.zze.CREATOR);
            var4.add(Integer.valueOf(22));
            break;
         case 23:
            var23 = zzb.zzc(var2, var30, zzcri.zzf.CREATOR);
            var4.add(Integer.valueOf(23));
            break;
         case 24:
            var24 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(24));
            break;
         case 25:
            var25 = zzb.zzg(var2, var30);
            var4.add(Integer.valueOf(25));
            break;
         case 26:
            var26 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(26));
            break;
         case 27:
            var27 = zzb.zzq(var2, var30);
            var4.add(Integer.valueOf(27));
            break;
         case 28:
            var28 = zzb.zzc(var2, var30, zzcri.zzg.CREATOR);
            var4.add(Integer.valueOf(28));
            break;
         case 29:
            var29 = zzb.zzc(var2, var30);
            var4.add(Integer.valueOf(29));
         }
      }

      if (var2.dataPosition() != var3) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var3).toString(), var2);
      } else {
         return new zzcri(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27, var28, var29);
      }
   }
}
