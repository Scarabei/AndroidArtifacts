package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class zza implements Creator {
   public InvitationEntity zzh(Parcel var1) {
      int var2 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      GameEntity var3 = null;
      String var4 = null;
      long var5 = 0L;
      int var7 = 0;
      ParticipantEntity var8 = null;
      ArrayList var9 = null;
      int var10 = 0;
      int var11 = 0;

      while(var1.dataPosition() < var2) {
         int var12;
         switch((var12 = var1.readInt()) & 65535) {
         case 1:
            var3 = (GameEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var12, GameEntity.CREATOR);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var12);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var1, var12);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var12);
            break;
         case 5:
            var8 = (ParticipantEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var12, ParticipantEntity.CREATOR);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var12, ParticipantEntity.CREATOR);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var12);
            break;
         case 8:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var1, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var1, var2);
      return new InvitationEntity(var3, var4, var5, var7, var8, var9, var10, var11);
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return new InvitationEntity[var1];
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.zzh(var1);
   }
}
