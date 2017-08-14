package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public final class zzu implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataUpdateListenerRegistrationRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataSource var5 = null;
      DataType var6 = null;
      PendingIntent var7 = null;
      IBinder var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, DataSource.CREATOR);
            break;
         case 2:
            var6 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, DataType.CREATOR);
            break;
         case 3:
            var7 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, PendingIntent.CREATOR);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var9);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var9);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataUpdateListenerRegistrationRequest(var4, var5, var6, var7, var8);
   }
}
