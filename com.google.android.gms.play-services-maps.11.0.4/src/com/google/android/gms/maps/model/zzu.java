package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzu implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new VisibleRegion[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      LatLng var4 = null;
      LatLng var5 = null;
      LatLng var6 = null;
      LatLng var7 = null;
      LatLngBounds var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, LatLng.CREATOR);
            break;
         case 3:
            var5 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, LatLng.CREATOR);
            break;
         case 4:
            var6 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, LatLng.CREATOR);
            break;
         case 5:
            var7 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, LatLng.CREATOR);
            break;
         case 6:
            var8 = (LatLngBounds)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, LatLngBounds.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new VisibleRegion(var4, var5, var6, var7, var8);
   }
}
