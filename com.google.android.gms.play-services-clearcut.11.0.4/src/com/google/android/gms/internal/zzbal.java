package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbal implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbak[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      int var6 = 0;
      String var7 = null;
      String var8 = null;
      boolean var9 = true;
      String var10 = null;
      boolean var11 = false;
      int var12 = 0;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var13);
            break;
         case 3:
            var5 = zzb.zzg(var2, var13);
            break;
         case 4:
            var6 = zzb.zzg(var2, var13);
            break;
         case 5:
            var7 = zzb.zzq(var2, var13);
            break;
         case 6:
            var8 = zzb.zzq(var2, var13);
            break;
         case 7:
            var9 = zzb.zzc(var2, var13);
            break;
         case 8:
            var10 = zzb.zzq(var2, var13);
            break;
         case 9:
            var11 = zzb.zzc(var2, var13);
            break;
         case 10:
            var12 = zzb.zzg(var2, var13);
            break;
         default:
            zzb.zzb(var2, var13);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbak(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
