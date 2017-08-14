package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcds implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcdr[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      short var6 = 0;
      double var7 = 0.0D;
      double var9 = 0.0D;
      float var11 = 0.0F;
      long var12 = 0L;
      int var14 = 0;
      int var15 = -1;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var16);
            break;
         case 2:
            var12 = zzb.zzi(var2, var16);
            break;
         case 3:
            var6 = zzb.zzf(var2, var16);
            break;
         case 4:
            var7 = zzb.zzn(var2, var16);
            break;
         case 5:
            var9 = zzb.zzn(var2, var16);
            break;
         case 6:
            var11 = zzb.zzl(var2, var16);
            break;
         case 7:
            var5 = zzb.zzg(var2, var16);
            break;
         case 8:
            var14 = zzb.zzg(var2, var16);
            break;
         case 9:
            var15 = zzb.zzg(var2, var16);
            break;
         default:
            zzb.zzb(var2, var16);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcdr(var4, var5, var6, var7, var9, var11, var12, var14, var15);
   }
}
