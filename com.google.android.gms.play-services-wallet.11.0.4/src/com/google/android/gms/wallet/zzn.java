package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzn implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new LineItem[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      int var8 = 0;
      String var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new LineItem(var4, var5, var6, var7, var8, var9);
   }
}
