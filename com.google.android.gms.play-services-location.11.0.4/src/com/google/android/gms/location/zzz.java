package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzz implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzy[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 1;
      int var5 = 1;
      long var6 = -1L;
      long var8 = -1L;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzy(var4, var5, var6, var8);
   }
}
