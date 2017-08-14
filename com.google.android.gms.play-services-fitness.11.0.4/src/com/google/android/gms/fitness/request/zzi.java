package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

public final class zzi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DataDeleteRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      ArrayList var9 = null;
      ArrayList var10 = null;
      ArrayList var11 = null;
      boolean var12 = false;
      boolean var13 = false;
      IBinder var14 = null;

      while(var2.dataPosition() < var3) {
         int var15;
         switch((var15 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var15);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var15);
            break;
         case 3:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, DataSource.CREATOR);
            break;
         case 4:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, DataType.CREATOR);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15, Session.CREATOR);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 7:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var15);
            break;
         case 8:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var15);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DataDeleteRequest(var4, var5, var7, var9, var10, var11, var12, var13, var14);
   }
}
