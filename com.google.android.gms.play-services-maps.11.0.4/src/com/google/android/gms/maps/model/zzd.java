package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GroundOverlayOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      IBinder var4 = null;
      LatLng var5 = null;
      float var6 = 0.0F;
      float var7 = 0.0F;
      LatLngBounds var8 = null;
      float var9 = 0.0F;
      float var10 = 0.0F;
      boolean var11 = false;
      float var12 = 0.0F;
      float var13 = 0.0F;
      float var14 = 0.0F;
      boolean var15 = false;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var16);
            break;
         case 3:
            var5 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, LatLng.CREATOR);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 6:
            var8 = (LatLngBounds)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, LatLngBounds.CREATOR);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var16);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var16);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GroundOverlayOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
   }
}
