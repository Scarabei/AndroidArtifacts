package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;

public final class zzad implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PlaceEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      ArrayList var5 = null;
      ArrayList var6 = null;
      Bundle var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      ArrayList var12 = null;
      LatLng var13 = null;
      float var14 = 0.0F;
      LatLngBounds var15 = null;
      String var16 = null;
      Uri var17 = null;
      boolean var18 = false;
      float var19 = 0.0F;
      int var20 = 0;
      zzaj var21 = null;
      zzal var22 = null;
      zzae var23 = null;

      while(var2.dataPosition() < var3) {
         int var24;
         switch((var24 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var24);
            break;
         case 3:
            var21 = (zzaj)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, zzaj.CREATOR);
            break;
         case 4:
            var13 = (LatLng)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, LatLng.CREATOR);
            break;
         case 5:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var24);
            break;
         case 6:
            var15 = (LatLngBounds)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, LatLngBounds.CREATOR);
            break;
         case 7:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 8:
            var17 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, Uri.CREATOR);
            break;
         case 9:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var24);
            break;
         case 10:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var24);
            break;
         case 11:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var24);
            break;
         case 12:
         case 18:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var24);
            break;
         case 13:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var24);
            break;
         case 14:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 15:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 16:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 17:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var24);
            break;
         case 19:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var24);
            break;
         case 20:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var24);
            break;
         case 21:
            var22 = (zzal)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, zzal.CREATOR);
            break;
         case 22:
            var23 = (zzae)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var24, zzae.CREATOR);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PlaceEntity(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23);
   }
}
