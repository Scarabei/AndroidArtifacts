package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new ProxyResponse[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      int var5 = 0;
      PendingIntent var6 = null;
      int var7 = 0;
      Bundle var8 = null;
      byte[] var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 2:
            var6 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, PendingIntent.CREATOR);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var10);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var10);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new ProxyResponse(var4, var5, var6, var7, var8, var9);
   }
}
