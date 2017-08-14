package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzh implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PlaceFilter[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var4 = null;
      boolean var5 = false;
      ArrayList var6 = null;
      ArrayList var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var8);
            break;
         case 2:
         case 5:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8, zzo.CREATOR);
            break;
         case 6:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PlaceFilter(var4, var5, var6, var7);
   }
}
