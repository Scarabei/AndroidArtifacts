package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzdx[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      byte[] var6 = null;
      String var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var8);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var8);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var8);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var8);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzdx(var4, var5, var6, var7);
   }
}
