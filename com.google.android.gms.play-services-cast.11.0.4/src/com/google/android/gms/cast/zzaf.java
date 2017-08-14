package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzaf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MediaQueueItem[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      MediaInfo var4 = null;
      int var5 = 0;
      boolean var6 = false;
      double var7 = 0.0D;
      double var9 = 0.0D;
      double var11 = 0.0D;
      long[] var13 = null;
      String var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 2:
            var4 = (MediaInfo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var15, MediaInfo.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var15);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var15);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var15);
            break;
         case 8:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzx(var2, var15);
            break;
         case 9:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MediaQueueItem(var4, var5, var6, var7, var9, var11, var13, var14);
   }
}
