package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzajf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaje[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var9);
            break;
         case 3:
            var5 = zzb.zzg(var2, var9);
            break;
         case 4:
            var6 = zzb.zzg(var2, var9);
            break;
         case 5:
            var7 = zzb.zzc(var2, var9);
            break;
         case 6:
            var8 = zzb.zzc(var2, var9);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzaje(var4, var5, var6, var7, var8);
   }
}
