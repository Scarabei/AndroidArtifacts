package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public final class zzr implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new LocationResult[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Object var4 = LocationResult.zzbic;

      while(var2.dataPosition() < var3) {
         int var5;
         switch((var5 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var5, Location.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var5);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new LocationResult((List)var4);
   }
}
