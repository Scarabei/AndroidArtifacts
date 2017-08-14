package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

public class zze implements Creator {
   public RoomEntity zzj(Parcel var1) {
      int var2 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var3 = null;
      String var4 = null;
      long var5 = 0L;
      int var7 = 0;
      String var8 = null;
      int var9 = 0;
      Bundle var10 = null;
      ArrayList var11 = null;
      int var12 = 0;

      while(var1.dataPosition() < var2) {
         int var13;
         switch((var13 = var1.readInt()) & 65535) {
         case 1:
            var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var13);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var13);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var1, var13);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var13);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var13);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var13);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var1, var13);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var13, ParticipantEntity.CREATOR);
            break;
         case 9:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var13);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var1, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var1, var2);
      return new RoomEntity(var3, var4, var5, var7, var8, var9, var10, var11, var12);
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return new RoomEntity[var1];
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.zzj(var1);
   }
}
