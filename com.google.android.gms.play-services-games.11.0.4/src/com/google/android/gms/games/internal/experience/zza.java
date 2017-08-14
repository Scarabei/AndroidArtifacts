package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.GameEntity;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new ExperienceEventEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      GameEntity var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      Uri var9 = null;
      long var10 = 0L;
      long var12 = 0L;
      long var14 = 0L;
      int var16 = 0;
      int var17 = 0;

      while(var2.dataPosition() < var3) {
         int var18;
         switch((var18 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var18);
            break;
         case 2:
            var5 = (GameEntity)zzb.zza(var2, var18, GameEntity.CREATOR);
            break;
         case 3:
            var6 = zzb.zzq(var2, var18);
            break;
         case 4:
            var7 = zzb.zzq(var2, var18);
            break;
         case 5:
            var8 = zzb.zzq(var2, var18);
            break;
         case 6:
            var9 = (Uri)zzb.zza(var2, var18, Uri.CREATOR);
            break;
         case 7:
            var10 = zzb.zzi(var2, var18);
            break;
         case 8:
            var12 = zzb.zzi(var2, var18);
            break;
         case 9:
            var14 = zzb.zzi(var2, var18);
            break;
         case 10:
            var16 = zzb.zzg(var2, var18);
            break;
         case 11:
            var17 = zzb.zzg(var2, var18);
            break;
         default:
            zzb.zzb(var2, var18);
         }
      }

      zzb.zzF(var2, var3);
      return new ExperienceEventEntity(var4, var5, var6, var7, var8, var9, var10, var12, var14, var16, var17);
   }
}
