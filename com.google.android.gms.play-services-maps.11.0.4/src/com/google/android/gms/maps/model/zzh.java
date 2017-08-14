package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzh implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MarkerOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      LatLng var4 = null;
      String var5 = null;
      String var6 = null;
      IBinder var7 = null;
      float var8 = 0.0F;
      float var9 = 0.0F;
      boolean var10 = false;
      boolean var11 = false;
      boolean var12 = false;
      float var13 = 0.0F;
      float var14 = 0.5F;
      float var15 = 0.0F;
      float var16 = 1.0F;
      float var17 = 0.0F;

      while(var2.dataPosition() < var3) {
         int var18;
         switch((var18 = var2.readInt()) & 65535) {
         case 2:
            var4 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, LatLng.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var18);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var18);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var18);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var18);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var18);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var18);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 14:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         case 15:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var18);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var18);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MarkerOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
   }
}
