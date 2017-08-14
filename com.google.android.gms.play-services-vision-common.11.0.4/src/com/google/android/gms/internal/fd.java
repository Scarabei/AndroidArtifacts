package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class fd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new fc[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      long var7 = 0L;
      int var9 = 0;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var10);
            break;
         case 3:
            var5 = zzb.zzg(var2, var10);
            break;
         case 4:
            var6 = zzb.zzg(var2, var10);
            break;
         case 5:
            var7 = zzb.zzi(var2, var10);
            break;
         case 6:
            var9 = zzb.zzg(var2, var10);
            break;
         default:
            zzb.zzb(var2, var10);
         }
      }

      zzb.zzF(var2, var3);
      return new fc(var4, var5, var6, var7, var9);
   }
}
