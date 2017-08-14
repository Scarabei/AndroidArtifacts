package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PlayerLevelInfo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      long var4 = 0L;
      long var6 = 0L;
      PlayerLevel var8 = null;
      PlayerLevel var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 3:
            var8 = (PlayerLevel)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, PlayerLevel.CREATOR);
            break;
         case 4:
            var9 = (PlayerLevel)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, PlayerLevel.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PlayerLevelInfo(var4, var6, var8, var9);
   }
}
