package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzit implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzir[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      Bundle var7 = null;
      int var8 = 0;
      ArrayList var9 = null;
      boolean var10 = false;
      int var11 = 0;
      boolean var12 = false;
      String var13 = null;
      zzlt var14 = null;
      Location var15 = null;
      String var16 = null;
      Bundle var17 = null;
      Bundle var18 = null;
      ArrayList var19 = null;
      String var20 = null;
      String var21 = null;
      boolean var22 = false;

      while(var2.dataPosition() < var3) {
         int var23;
         switch((var23 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var23);
            break;
         case 2:
            var5 = zzb.zzi(var2, var23);
            break;
         case 3:
            var7 = zzb.zzs(var2, var23);
            break;
         case 4:
            var8 = zzb.zzg(var2, var23);
            break;
         case 5:
            var9 = zzb.zzC(var2, var23);
            break;
         case 6:
            var10 = zzb.zzc(var2, var23);
            break;
         case 7:
            var11 = zzb.zzg(var2, var23);
            break;
         case 8:
            var12 = zzb.zzc(var2, var23);
            break;
         case 9:
            var13 = zzb.zzq(var2, var23);
            break;
         case 10:
            var14 = (zzlt)zzb.zza(var2, var23, zzlt.CREATOR);
            break;
         case 11:
            var15 = (Location)zzb.zza(var2, var23, Location.CREATOR);
            break;
         case 12:
            var16 = zzb.zzq(var2, var23);
            break;
         case 13:
            var17 = zzb.zzs(var2, var23);
            break;
         case 14:
            var18 = zzb.zzs(var2, var23);
            break;
         case 15:
            var19 = zzb.zzC(var2, var23);
            break;
         case 16:
            var20 = zzb.zzq(var2, var23);
            break;
         case 17:
            var21 = zzb.zzq(var2, var23);
            break;
         case 18:
            var22 = zzb.zzc(var2, var23);
            break;
         default:
            zzb.zzb(var2, var23);
         }
      }

      zzb.zzF(var2, var3);
      return new zzir(var4, var5, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22);
   }
}
