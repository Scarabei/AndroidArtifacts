package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public final class zzav implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new SessionReadRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      long var7 = 0L;
      long var9 = 0L;
      ArrayList var11 = null;
      ArrayList var12 = null;
      boolean var13 = false;
      boolean var14 = false;
      ArrayList var15 = null;
      IBinder var16 = null;

      while(var2.dataPosition() < var3) {
         int var17;
         switch((var17 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 2:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var17);
            break;
         case 3:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var17);
            break;
         case 4:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var17);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var17, DataType.CREATOR);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var17, DataSource.CREATOR);
            break;
         case 7:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var17);
            break;
         case 8:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var17);
            break;
         case 9:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var17);
            break;
         case 10:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var17);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var17);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var17);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new SessionReadRequest(var4, var5, var6, var7, var9, var11, var12, var13, var14, var15, var16);
   }
}
