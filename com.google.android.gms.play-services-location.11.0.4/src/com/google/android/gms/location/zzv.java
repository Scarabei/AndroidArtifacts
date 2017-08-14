package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzv implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new LocationSettingsRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var4 = null;
      boolean var5 = false;
      boolean var6 = false;
      zzt var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8, LocationRequest.CREATOR);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var8);
            break;
         case 4:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
            break;
         case 5:
            var7 = (zzt)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, zzt.CREATOR);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new LocationSettingsRequest(var4, var5, var6, var7);
   }
}
