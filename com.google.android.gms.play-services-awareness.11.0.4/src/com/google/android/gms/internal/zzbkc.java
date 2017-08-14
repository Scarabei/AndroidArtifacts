package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbkc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbkb[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      int var6 = 0;
      String var7 = null;
      int var8 = 0;
      int var9 = 0;
      String var10 = null;
      String var11 = null;
      int var12 = 0;
      int var13 = 0;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var14);
            break;
         case 3:
            var5 = zzb.zzq(var2, var14);
            break;
         case 4:
            var6 = zzb.zzg(var2, var14);
            break;
         case 5:
            var7 = zzb.zzq(var2, var14);
            break;
         case 6:
            var8 = zzb.zzg(var2, var14);
            break;
         case 7:
            var9 = zzb.zzg(var2, var14);
            break;
         case 8:
            var10 = zzb.zzq(var2, var14);
            break;
         case 9:
            var11 = zzb.zzq(var2, var14);
            break;
         case 10:
            var12 = zzb.zzg(var2, var14);
            break;
         case 11:
            var13 = zzb.zzg(var2, var14);
            break;
         default:
            zzb.zzb(var2, var14);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbkb(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }
}
