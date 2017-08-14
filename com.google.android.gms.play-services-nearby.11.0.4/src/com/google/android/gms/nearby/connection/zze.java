package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zze implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PayloadTransferUpdate[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      long var4 = 0L;
      int var6 = 0;
      long var7 = 0L;
      long var9 = 0L;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var11);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PayloadTransferUpdate(var4, var6, var7, var9);
   }
}
