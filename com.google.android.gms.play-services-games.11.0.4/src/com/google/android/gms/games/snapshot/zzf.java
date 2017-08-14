package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;

public final class zzf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new SnapshotMetadataEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      GameEntity var4 = null;
      PlayerEntity var5 = null;
      String var6 = null;
      Uri var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      long var11 = 0L;
      long var13 = 0L;
      float var15 = 0.0F;
      String var16 = null;
      boolean var17 = false;
      long var18 = 0L;
      String var20 = null;

      while(var2.dataPosition() < var3) {
         int var21;
         switch((var21 = var2.readInt()) & 65535) {
         case 1:
            var4 = (GameEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var21, GameEntity.CREATOR);
            break;
         case 2:
            var5 = (PlayerEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var21, PlayerEntity.CREATOR);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 4:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var21);
            break;
         case 5:
            var7 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var21, Uri.CREATOR);
            break;
         case 6:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 10:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 11:
            var15 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(var2, var21);
            break;
         case 12:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
            break;
         case 13:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var21);
            break;
         case 14:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var21);
            break;
         case 15:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var21);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new SnapshotMetadataEntity(var4, var5, var6, var7, var8, var9, var10, var11, var13, var15, var16, var17, var18, var20);
   }
}
