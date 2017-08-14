package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzl implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new PolylineOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var4 = null;
      float var5 = 0.0F;
      int var6 = 0;
      float var7 = 0.0F;
      boolean var8 = false;
      boolean var9 = false;
      boolean var10 = false;
      Cap var11 = null;
      Cap var12 = null;
      int var13 = 0;
      ArrayList var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, LatLng.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var15);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 9:
            var11 = (Cap)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, Cap.CREATOR);
            break;
         case 10:
            var12 = (Cap)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, Cap.CREATOR);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, PatternItem.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new PolylineOptions(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
   }
}
