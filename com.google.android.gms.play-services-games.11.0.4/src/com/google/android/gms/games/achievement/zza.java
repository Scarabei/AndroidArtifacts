package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.PlayerEntity;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new AchievementEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      int var5 = 0;
      String var6 = null;
      String var7 = null;
      Uri var8 = null;
      String var9 = null;
      Uri var10 = null;
      String var11 = null;
      int var12 = 0;
      String var13 = null;
      PlayerEntity var14 = null;
      int var15 = 0;
      int var16 = 0;
      String var17 = null;
      long var18 = 0L;
      long var20 = 0L;

      while(var2.dataPosition() < var3) {
         int var22;
         switch((var22 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var22);
            break;
         case 2:
            var5 = zzb.zzg(var2, var22);
            break;
         case 3:
            var6 = zzb.zzq(var2, var22);
            break;
         case 4:
            var7 = zzb.zzq(var2, var22);
            break;
         case 5:
            var8 = (Uri)zzb.zza(var2, var22, Uri.CREATOR);
            break;
         case 6:
            var9 = zzb.zzq(var2, var22);
            break;
         case 7:
            var10 = (Uri)zzb.zza(var2, var22, Uri.CREATOR);
            break;
         case 8:
            var11 = zzb.zzq(var2, var22);
            break;
         case 9:
            var12 = zzb.zzg(var2, var22);
            break;
         case 10:
            var13 = zzb.zzq(var2, var22);
            break;
         case 11:
            var14 = (PlayerEntity)zzb.zza(var2, var22, PlayerEntity.CREATOR);
            break;
         case 12:
            var15 = zzb.zzg(var2, var22);
            break;
         case 13:
            var16 = zzb.zzg(var2, var22);
            break;
         case 14:
            var17 = zzb.zzq(var2, var22);
            break;
         case 15:
            var18 = zzb.zzi(var2, var22);
            break;
         case 16:
            var20 = zzb.zzi(var2, var22);
            break;
         default:
            zzb.zzb(var2, var22);
         }
      }

      zzb.zzF(var2, var3);
      return new AchievementEntity(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var20);
   }
}
