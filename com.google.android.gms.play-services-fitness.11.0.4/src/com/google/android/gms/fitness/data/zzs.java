package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zzs implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Goal[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var3 = var1;
      zzs var2 = this;
      int var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var5 = 0;
      long var6 = 0L;
      long var8 = 0L;
      ArrayList var10 = new ArrayList();
      Goal.Recurrence var11 = null;
      int var12 = 0;
      Goal.MetricObjective var13 = null;
      Goal.DurationObjective var14 = null;
      Goal.FrequencyObjective var15 = null;

      while(var3.dataPosition() < var4) {
         int var16;
         switch((var16 = var3.readInt()) & 65535) {
         case 1:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var3, var16);
            break;
         case 2:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var3, var16);
            break;
         case 3:
            com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, var10, var2.getClass().getClassLoader());
            break;
         case 4:
            var11 = (Goal.Recurrence)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, Goal.Recurrence.CREATOR);
            break;
         case 5:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var16);
            break;
         case 6:
            var13 = (Goal.MetricObjective)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, Goal.MetricObjective.CREATOR);
            break;
         case 7:
            var14 = (Goal.DurationObjective)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, Goal.DurationObjective.CREATOR);
            break;
         case 8:
            var15 = (Goal.FrequencyObjective)com.google.android.gms.common.internal.safeparcel.zzb.zza(var3, var16, Goal.FrequencyObjective.CREATOR);
            break;
         case 1000:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var3, var16);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var3, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var3, var4);
      return new Goal(var5, var6, var8, var10, var11, var12, var13, var14, var15);
   }
}
