package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new TurnBasedMatchEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      GameEntity var4 = null;
      String var5 = null;
      String var6 = null;
      long var7 = 0L;
      String var9 = null;
      long var10 = 0L;
      String var12 = null;
      int var13 = 0;
      int var14 = 0;
      int var15 = 0;
      byte[] var16 = null;
      ArrayList var17 = null;
      String var18 = null;
      byte[] var19 = null;
      int var20 = 0;
      Bundle var21 = null;
      int var22 = 0;
      boolean var23 = false;
      String var24 = null;
      String var25 = null;

      while(var2.dataPosition() < var3) {
         int var26;
         switch((var26 = var2.readInt()) & 65535) {
         case 1:
            var4 = (GameEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var26, GameEntity.CREATOR);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var26);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var26);
            break;
         case 7:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 8:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var26);
            break;
         case 9:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var26);
            break;
         case 10:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var26);
            break;
         case 11:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var26);
            break;
         case 12:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var26);
            break;
         case 13:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var26, ParticipantEntity.CREATOR);
            break;
         case 14:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 15:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(var2, var26);
            break;
         case 16:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var26);
            break;
         case 17:
            var21 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(var2, var26);
            break;
         case 18:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var26);
            break;
         case 19:
            var23 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var26);
            break;
         case 20:
            var24 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
            break;
         case 21:
            var25 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var26);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new TurnBasedMatchEntity(var4, var5, var6, var7, var9, var10, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25);
   }
}
