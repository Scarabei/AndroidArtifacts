package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzaje;

public final class zzv implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new AdOverlayInfoParcel[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      zzc var4 = null;
      IBinder var5 = null;
      IBinder var6 = null;
      IBinder var7 = null;
      IBinder var8 = null;
      String var9 = null;
      boolean var10 = false;
      String var11 = null;
      IBinder var12 = null;
      int var13 = 0;
      int var14 = 0;
      String var15 = null;
      zzaje var16 = null;
      String var17 = null;
      com.google.android.gms.ads.internal.zzap var18 = null;

      while(var2.dataPosition() < var3) {
         int var19;
         switch((var19 = var2.readInt()) & 65535) {
         case 2:
            var4 = (zzc)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var19, zzc.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var19);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var19);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var19);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var19);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var19);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var19);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var19);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var19);
            break;
         case 13:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 14:
            var16 = (zzaje)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var19, zzaje.CREATOR);
            break;
         case 15:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var19);
            break;
         case 16:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var19);
            break;
         case 17:
            var18 = (com.google.android.gms.ads.internal.zzap)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var19, com.google.android.gms.ads.internal.zzap.CREATOR);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new AdOverlayInfoParcel(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18);
   }
}
