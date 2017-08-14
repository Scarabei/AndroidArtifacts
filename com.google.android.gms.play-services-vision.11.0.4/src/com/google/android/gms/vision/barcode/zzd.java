package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Barcode.CalendarDateTime[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      String var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var12);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Barcode.CalendarDateTime(var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
