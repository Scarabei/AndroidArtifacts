package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new CustomPropertyKey[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var6);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var6);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var6);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new CustomPropertyKey(var4, var5);
   }
}
