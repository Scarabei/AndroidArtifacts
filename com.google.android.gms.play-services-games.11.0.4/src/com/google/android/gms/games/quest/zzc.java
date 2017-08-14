package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new QuestEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      GameEntity var4 = null;
      String var5 = null;
      long var6 = 0L;
      Uri var8 = null;
      String var9 = null;
      String var10 = null;
      long var11 = 0L;
      long var13 = 0L;
      Uri var15 = null;
      String var16 = null;
      String var17 = null;
      long var18 = 0L;
      long var20 = 0L;
      int var22 = 0;
      int var23 = 0;
      ArrayList var24 = null;

      while(var2.dataPosition() < var3) {
         int var25;
         switch((var25 = var2.readInt()) & 65535) {
         case 1:
            var4 = (GameEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var25, GameEntity.CREATOR);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var25);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var25);
            break;
         case 4:
            var8 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var25, Uri.CREATOR);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var25);
            break;
         case 6:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var25);
            break;
         case 7:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var25);
            break;
         case 8:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var25);
            break;
         case 9:
            var15 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var25, Uri.CREATOR);
            break;
         case 10:
            var16 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var25);
            break;
         case 11:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var25);
            break;
         case 12:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var25);
            break;
         case 13:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var25);
            break;
         case 14:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var25);
            break;
         case 15:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var25);
            break;
         case 16:
            var23 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var25);
            break;
         case 17:
            var24 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var25, MilestoneEntity.CREATOR);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new QuestEntity(var4, var5, var6, var8, var9, var10, var11, var13, var15, var16, var17, var18, var20, var22, var23, var24);
   }
}
