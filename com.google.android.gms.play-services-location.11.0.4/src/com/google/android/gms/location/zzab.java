package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzab implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzaa[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ArrayList var4 = null;
      PendingIntent var5 = null;
      String var6 = "";

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var7);
            break;
         case 2:
            var5 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var7, PendingIntent.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var7);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var7);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzaa(var4, var5, var6);
   }
}
