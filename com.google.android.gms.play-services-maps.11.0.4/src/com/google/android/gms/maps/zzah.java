package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class zzah implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new StreetViewPanoramaOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      StreetViewPanoramaCamera var4 = null;
      String var5 = null;
      LatLng var6 = null;
      Integer var7 = null;
      byte var8 = 0;
      byte var9 = 0;
      byte var10 = 0;
      byte var11 = 0;
      byte var12 = 0;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 2:
            var4 = (StreetViewPanoramaCamera)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, StreetViewPanoramaCamera.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 4:
            var6 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, LatLng.CREATOR);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzh(var2, var13);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var13);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var13);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var13);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var13);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var13);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new StreetViewPanoramaOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
