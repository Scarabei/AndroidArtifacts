package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import java.util.ArrayList;

public final class zzad implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new MediaMetadata[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var4 = null;
      Bundle var5 = null;
      int var6 = 0;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var7, WebImage.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var7);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var7);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var7);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new MediaMetadata(var4, var5, var6);
   }
}
