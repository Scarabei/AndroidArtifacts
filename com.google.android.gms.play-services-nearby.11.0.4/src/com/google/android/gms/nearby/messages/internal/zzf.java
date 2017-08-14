package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zze[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      double var6 = 0.0D;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(var2, var8);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zze(var4, var5, var6);
   }
}
