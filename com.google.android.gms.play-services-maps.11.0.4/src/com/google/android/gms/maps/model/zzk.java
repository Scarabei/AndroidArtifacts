package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzk implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PolygonOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var3 = var1;
      zzk var2 = this;
      int var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var5 = null;
      ArrayList var6 = new ArrayList();
      float var7 = 0.0F;
      int var8 = 0;
      int var9 = 0;
      float var10 = 0.0F;
      boolean var11 = false;
      boolean var12 = false;
      boolean var13 = false;
      int var14 = 0;
      ArrayList var15 = null;

      while(var3.dataPosition() < var4) {
         int var16;
         switch((var16 = var3.readInt()) & 65535) {
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var16, LatLng.CREATOR);
            break;
         case 3:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, var6, var2.getClass().getClassLoader());
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var3, var16);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var16);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var16);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var3, var16);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var16);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var16);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var16);
            break;
         case 11:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var16);
            break;
         case 12:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var3, var16, PatternItem.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var3, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var3, var4);
      return new PolygonOptions(var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
   }
}
