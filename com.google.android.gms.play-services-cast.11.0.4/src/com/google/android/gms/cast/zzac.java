package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzac implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MediaInfo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      String var6 = null;
      MediaMetadata var7 = null;
      long var8 = 0L;
      ArrayList var10 = null;
      TextTrackStyle var11 = null;
      String var12 = null;
      ArrayList var13 = null;
      ArrayList var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 5:
            var7 = (MediaMetadata)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, MediaMetadata.CREATOR);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var15);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, MediaTrack.CREATOR);
            break;
         case 8:
            var11 = (TextTrackStyle)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, TextTrackStyle.CREATOR);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, AdBreakInfo.CREATOR);
            break;
         case 11:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, AdBreakClipInfo.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MediaInfo(var4, var5, var6, var7, var8, var10, var11, var12, var13, var14);
   }
}
