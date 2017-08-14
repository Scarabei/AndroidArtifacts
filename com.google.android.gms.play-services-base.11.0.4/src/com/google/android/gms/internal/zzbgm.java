package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbgm implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbgj[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      boolean var6 = false;
      int var7 = 0;
      boolean var8 = false;
      String var9 = null;
      int var10 = 0;
      String var11 = null;
      zzbgc var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var13);
            break;
         case 2:
            var5 = zzb.zzg(var2, var13);
            break;
         case 3:
            var6 = zzb.zzc(var2, var13);
            break;
         case 4:
            var7 = zzb.zzg(var2, var13);
            break;
         case 5:
            var8 = zzb.zzc(var2, var13);
            break;
         case 6:
            var9 = zzb.zzq(var2, var13);
            break;
         case 7:
            var10 = zzb.zzg(var2, var13);
            break;
         case 8:
            var11 = zzb.zzq(var2, var13);
            break;
         case 9:
            var12 = (zzbgc)zzb.zza(var2, var13, zzbgc.CREATOR);
            break;
         default:
            zzb.zzb(var2, var13);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbgj(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
