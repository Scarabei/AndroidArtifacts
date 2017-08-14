package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.HashSet;

public final class zzcrq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcri.zze[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      HashSet var4 = new HashSet();
      int var5 = 0;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      boolean var11 = false;
      String var12 = null;
      String var13 = null;
      int var14 = 0;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 1:
            var5 = zzb.zzg(var2, var15);
            var4.add(Integer.valueOf(1));
            break;
         case 2:
            var6 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(2));
            break;
         case 3:
            var7 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(3));
            break;
         case 4:
            var8 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(4));
            break;
         case 5:
            var9 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(5));
            break;
         case 6:
            var10 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(6));
            break;
         case 7:
            var11 = zzb.zzc(var2, var15);
            var4.add(Integer.valueOf(7));
            break;
         case 8:
            var12 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(8));
            break;
         case 9:
            var13 = zzb.zzq(var2, var15);
            var4.add(Integer.valueOf(9));
            break;
         case 10:
            var14 = zzb.zzg(var2, var15);
            var4.add(Integer.valueOf(10));
            break;
         default:
            zzb.zzb(var2, var15);
         }
      }

      if (var2.dataPosition() != var3) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var3).toString(), var2);
      } else {
         return new zzcri.zze(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
      }
   }
}
