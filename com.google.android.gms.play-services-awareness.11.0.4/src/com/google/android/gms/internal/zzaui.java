package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzaui implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzauh[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      float var4 = 0.0F;
      float var5 = 0.0F;
      float var6 = 0.0F;
      int var7 = 0;
      int[] var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzl(var2, var9);
            break;
         case 3:
            var5 = zzb.zzl(var2, var9);
            break;
         case 4:
            var6 = zzb.zzl(var2, var9);
            break;
         case 5:
            var7 = zzb.zzg(var2, var9);
            break;
         case 6:
            var8 = zzb.zzw(var2, var9);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzauh(var4, var5, var6, var7, var8);
   }
}
