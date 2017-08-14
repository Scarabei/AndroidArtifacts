package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbtg implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbtf[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      boolean var6 = false;
      int var7 = -1;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var8);
            break;
         case 3:
            var5 = zzb.zzg(var2, var8);
            break;
         case 4:
            var6 = zzb.zzc(var2, var8);
            break;
         case 5:
            var7 = zzb.zzg(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbtf(var4, var5, var6, var7);
   }
}
