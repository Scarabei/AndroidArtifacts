package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DiscoveryOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Strategy var4 = null;

      while(var2.dataPosition() < var3) {
         int var5;
         switch((var5 = var2.readInt()) & 65535) {
         case 1:
            var4 = (Strategy)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var5, Strategy.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var5);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DiscoveryOptions(var4);
   }
}
