package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

public final class zzao implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzan[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      DataSource var5 = null;
      DataType var6 = null;
      IBinder var7 = null;
      int var8 = 0;
      int var9 = 0;
      long var10 = 0L;
      long var12 = 0L;
      PendingIntent var14 = null;
      long var15 = 0L;
      int var17 = 0;
      ArrayList var18 = null;
      long var19 = 0L;
      IBinder var21 = null;

      while(var2.dataPosition() < var3) {
         int var22;
         switch((var22 = var2.readInt()) & 65535) {
         case 1:
            var5 = (DataSource)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var22, DataSource.CREATOR);
            break;
         case 2:
            var6 = (DataType)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var22, DataType.CREATOR);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var22);
            break;
         case 4:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var22);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var22);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var22);
            break;
         case 7:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var22);
            break;
         case 8:
            var14 = (PendingIntent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var22, PendingIntent.CREATOR);
            break;
         case 9:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var22);
            break;
         case 10:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var22);
            break;
         case 11:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var22, LocationRequest.CREATOR);
            break;
         case 12:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var22);
            break;
         case 13:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var22);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var22);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var22);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzan(var4, var5, var6, var7, var8, var9, var10, var12, var14, var15, var17, var18, var19, var21);
   }
}
