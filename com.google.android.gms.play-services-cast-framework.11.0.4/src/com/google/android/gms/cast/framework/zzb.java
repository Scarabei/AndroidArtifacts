package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import java.util.ArrayList;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new CastOptions[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      ArrayList var5 = null;
      boolean var6 = false;
      LaunchOptions var7 = null;
      boolean var8 = false;
      CastMediaOptions var9 = null;
      boolean var10 = false;
      double var11 = 0.0D;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var13);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var13);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 5:
            var7 = (LaunchOptions)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, LaunchOptions.CREATOR);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 7:
            var9 = (CastMediaOptions)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, CastMediaOptions.CREATOR);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var13);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var13);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new CastOptions(var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
