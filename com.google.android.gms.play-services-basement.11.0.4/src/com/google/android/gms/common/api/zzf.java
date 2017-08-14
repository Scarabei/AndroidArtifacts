package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Status[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      String var6 = null;
      PendingIntent var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var5 = zzb.zzg(var2, var8);
            break;
         case 2:
            var6 = zzb.zzq(var2, var8);
            break;
         case 3:
            var7 = (PendingIntent)zzb.zza(var2, var8, PendingIntent.CREATOR);
            break;
         case 1000:
            var4 = zzb.zzg(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new Status(var4, var5, var6, var7);
   }
}
