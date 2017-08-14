package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zze implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Bucket[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      long var7 = 0L;
      Session var9 = null;
      int var10 = 0;
      ArrayList var11 = null;
      int var12 = 0;
      boolean var13 = false;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 1:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var14);
            break;
         case 2:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var14);
            break;
         case 3:
            var9 = (Session)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var14, Session.CREATOR);
            break;
         case 4:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         case 5:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14, DataSet.CREATOR);
            break;
         case 6:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         case 7:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var14);
            break;
         case 1000:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var14);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var14);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Bucket(var4, var5, var7, var9, var10, var11, var12, var13);
   }
}
