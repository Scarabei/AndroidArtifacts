package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzag implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MediaStatus[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      MediaInfo var4 = null;
      long var5 = 0L;
      int var7 = 0;
      double var8 = 0.0D;
      int var10 = 0;
      int var11 = 0;
      long var12 = 0L;
      long var14 = 0L;
      double var16 = 0.0D;
      boolean var18 = false;
      long[] var19 = null;
      int var20 = 0;
      int var21 = 0;
      String var22 = null;
      int var23 = 0;
      ArrayList var24 = null;
      boolean var25 = false;
      AdBreakStatus var26 = null;
      VideoInfo var27 = null;

      while(var2.dataPosition() < var3) {
         int var28;
         switch((var28 = var2.readInt()) & 65535) {
         case 2:
            var4 = (MediaInfo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var28, MediaInfo.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var28);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var28);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var28);
            break;
         case 9:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var28);
            break;
         case 10:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var28);
            break;
         case 11:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var28);
            break;
         case 12:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzx(var2, var28);
            break;
         case 13:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 14:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 15:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var28);
            break;
         case 16:
            var23 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var28);
            break;
         case 17:
            var24 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var28, MediaQueueItem.CREATOR);
            break;
         case 18:
            var25 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var28);
            break;
         case 19:
            var26 = (AdBreakStatus)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var28, AdBreakStatus.CREATOR);
            break;
         case 20:
            var27 = (VideoInfo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var28, VideoInfo.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var28);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MediaStatus(var4, var5, var7, var8, var10, var11, var12, var14, var16, var18, var19, var20, var21, var22, var23, var24, var25, var26, var27);
   }
}
