package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

public final class zzcoy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcox[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      IBinder var4 = null;
      IBinder var5 = null;
      String var6 = null;
      String var7 = null;
      long var8 = 0L;
      AdvertisingOptions var10 = null;
      IBinder var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzr(var2, var12);
            break;
         case 2:
            var5 = zzb.zzr(var2, var12);
            break;
         case 3:
            var6 = zzb.zzq(var2, var12);
            break;
         case 4:
            var7 = zzb.zzq(var2, var12);
            break;
         case 5:
            var8 = zzb.zzi(var2, var12);
            break;
         case 6:
            var10 = (AdvertisingOptions)zzb.zza(var2, var12, AdvertisingOptions.CREATOR);
            break;
         case 7:
            var11 = zzb.zzr(var2, var12);
            break;
         default:
            zzb.zzb(var2, var12);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcox(var4, var5, var6, var7, var8, var10, var11);
   }
}
