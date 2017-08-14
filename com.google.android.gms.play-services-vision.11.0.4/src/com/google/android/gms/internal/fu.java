package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class fu implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new ft[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      fo[] var4 = null;
      fe var5 = null;
      fe var6 = null;
      String var7 = null;
      float var8 = 0.0F;
      String var9 = null;
      boolean var10 = false;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = (fo[])zzb.zzb(var2, var11, fo.CREATOR);
            break;
         case 3:
            var5 = (fe)zzb.zza(var2, var11, fe.CREATOR);
            break;
         case 4:
            var6 = (fe)zzb.zza(var2, var11, fe.CREATOR);
            break;
         case 5:
            var7 = zzb.zzq(var2, var11);
            break;
         case 6:
            var8 = zzb.zzl(var2, var11);
            break;
         case 7:
            var9 = zzb.zzq(var2, var11);
            break;
         case 8:
            var10 = zzb.zzc(var2, var11);
            break;
         default:
            zzb.zzb(var2, var11);
         }
      }

      zzb.zzF(var2, var3);
      return new ft(var4, var5, var6, var7, var8, var9, var10);
   }
}
