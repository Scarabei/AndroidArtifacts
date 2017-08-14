package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zza[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      ArrayList var5 = null;
      int var6 = 0;
      String var7 = null;
      ArrayList var8 = null;
      String var9 = null;
      ArrayList var10 = null;
      String var11 = null;
      ArrayList var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(var2, var13);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13, zzb.CREATOR);
            break;
         case 5:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var13);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13, zzb.CREATOR);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13, zzb.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zza(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
