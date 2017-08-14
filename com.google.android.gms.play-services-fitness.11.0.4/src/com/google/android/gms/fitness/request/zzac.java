package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzac implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GoalsReadRequest[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var3 = var1;
      zzac var2 = this;
      int var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var5 = 0;
      IBinder var6 = null;
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      ArrayList var9 = new ArrayList();

      while(var3.dataPosition() < var4) {
         int var10;
         switch((var10 = var3.readInt()) & 65535) {
         case 1:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var3, var10);
            break;
         case 2:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var10, var7, var2.getClass().getClassLoader());
            break;
         case 3:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var10, var8, var2.getClass().getClassLoader());
            break;
         case 4:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var10, var9, var2.getClass().getClassLoader());
            break;
         case 1000:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var3, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var3, var4);
      return new GoalsReadRequest(var5, var6, var7, var8, var9);
   }
}
