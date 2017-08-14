package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zze implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new WebImage[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      Uri var5 = null;
      int var6 = 0;
      int var7 = 0;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 2:
            var5 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, Uri.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new WebImage(var4, var5, var6, var7);
   }
}
