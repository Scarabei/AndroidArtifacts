package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new CircleOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      LatLng var4 = null;
      double var5 = 0.0D;
      float var7 = 0.0F;
      int var8 = 0;
      int var9 = 0;
      float var10 = 0.0F;
      boolean var11 = false;
      boolean var12 = false;
      ArrayList var13 = null;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 2:
            var4 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, LatLng.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var14);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var14);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var14);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14, PatternItem.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var14);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new CircleOptions(var4, var5, var7, var8, var9, var10, var11, var12, var13);
   }
}
