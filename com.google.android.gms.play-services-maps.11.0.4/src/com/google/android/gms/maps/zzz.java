package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzz implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GoogleMapOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      byte var4 = -1;
      byte var5 = -1;
      int var6 = 0;
      CameraPosition var7 = null;
      byte var8 = -1;
      byte var9 = -1;
      byte var10 = -1;
      byte var11 = -1;
      byte var12 = -1;
      byte var13 = -1;
      byte var14 = -1;
      byte var15 = -1;
      byte var16 = -1;
      Float var17 = null;
      Float var18 = null;
      LatLngBounds var19 = null;

      while(var2.dataPosition() < var3) {
         int var20;
         switch((var20 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var20);
            break;
         case 5:
            var7 = (CameraPosition)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, CameraPosition.CREATOR);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 13:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var20);
            break;
         case 14:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 15:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zze(var2, var20);
            break;
         case 16:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzm(var2, var20);
            break;
         case 17:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzm(var2, var20);
            break;
         case 18:
            var19 = (LatLngBounds)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var20, LatLngBounds.CREATOR);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GoogleMapOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19);
   }
}
