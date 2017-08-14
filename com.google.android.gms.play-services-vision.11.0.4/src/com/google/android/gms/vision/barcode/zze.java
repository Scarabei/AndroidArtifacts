package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zze implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Barcode.CalendarEvent[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      Barcode.CalendarDateTime var9 = null;
      Barcode.CalendarDateTime var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 7:
            var9 = (Barcode.CalendarDateTime)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Barcode.CalendarDateTime.CREATOR);
            break;
         case 8:
            var10 = (Barcode.CalendarDateTime)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Barcode.CalendarDateTime.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Barcode.CalendarEvent(var4, var5, var6, var7, var8, var9, var10);
   }
}
