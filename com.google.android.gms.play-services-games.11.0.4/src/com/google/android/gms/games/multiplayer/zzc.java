package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.PlayerEntity;

public class zzc implements Creator {
   public ParticipantEntity zzi(Parcel var1) {
      int var2 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var3 = null;
      String var4 = null;
      Uri var5 = null;
      Uri var6 = null;
      int var7 = 0;
      String var8 = null;
      boolean var9 = false;
      PlayerEntity var10 = null;
      int var11 = 0;
      ParticipantResult var12 = null;
      String var13 = null;
      String var14 = null;

      while(var1.dataPosition() < var2) {
         int var15;
         switch((var15 = var1.readInt()) & 65535) {
         case 1:
            var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var15);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var15);
            break;
         case 3:
            var5 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var15, Uri.CREATOR);
            break;
         case 4:
            var6 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var15, Uri.CREATOR);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var15);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var15);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var15);
            break;
         case 8:
            var10 = (PlayerEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var15, PlayerEntity.CREATOR);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var15);
            break;
         case 10:
            var12 = (ParticipantResult)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var15, ParticipantResult.CREATOR);
            break;
         case 11:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var15);
            break;
         case 12:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var15);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var1, var15);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var1, var2);
      return new ParticipantEntity(var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14);
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return new ParticipantEntity[var1];
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.zzi(var1);
   }
}
