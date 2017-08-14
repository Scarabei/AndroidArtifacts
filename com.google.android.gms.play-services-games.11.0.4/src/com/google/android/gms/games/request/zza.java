package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new GameRequestEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      GameEntity var4 = null;
      PlayerEntity var5 = null;
      byte[] var6 = null;
      String var7 = null;
      ArrayList var8 = null;
      int var9 = 0;
      long var10 = 0L;
      long var12 = 0L;
      Bundle var14 = null;
      int var15 = 0;

      while(var2.dataPosition() < var3) {
         int var16;
         switch((var16 = var2.readInt()) & 65535) {
         case 1:
            var4 = (GameEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, GameEntity.CREATOR);
            break;
         case 2:
            var5 = (PlayerEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var16, PlayerEntity.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var16);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var16);
            break;
         case 5:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var16, PlayerEntity.CREATOR);
            break;
         case 6:
         case 8:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var16);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
            break;
         case 9:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 10:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var16);
            break;
         case 11:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var16);
            break;
         case 12:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var16);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new GameRequestEntity(var4, var5, var6, var7, var8, var9, var10, var12, var14, var15);
   }
}
