package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbix implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbiw[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      zzbiy var5 = null;
      long var6 = 0L;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var8);
            break;
         case 3:
            var5 = (zzbiy)zzb.zza(var2, var8, zzbiy.CREATOR);
            break;
         case 4:
            var6 = zzb.zzi(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbiw(var4, var5, var6);
   }
}
