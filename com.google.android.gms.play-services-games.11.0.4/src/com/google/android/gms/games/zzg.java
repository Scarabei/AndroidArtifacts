package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class zzg implements Creator {
   public PlayerEntity zzg(Parcel var1) {
      int var2 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var3 = null;
      String var4 = null;
      Uri var5 = null;
      Uri var6 = null;
      long var7 = 0L;
      int var9 = 0;
      long var10 = 0L;
      String var12 = null;
      String var13 = null;
      String var14 = null;
      com.google.android.gms.games.internal.player.zzb var15 = null;
      PlayerLevelInfo var16 = null;
      boolean var17 = false;
      boolean var18 = false;
      String var19 = null;
      String var20 = null;
      Uri var21 = null;
      String var22 = null;
      Uri var23 = null;
      String var24 = null;
      int var25 = 0;
      long var26 = 0L;
      boolean var28 = false;

      while(var1.dataPosition() < var2) {
         int var29;
         switch((var29 = var1.readInt()) & 65535) {
         case 1:
            var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 3:
            var5 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, Uri.CREATOR);
            break;
         case 4:
            var6 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, Uri.CREATOR);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var1, var29);
            break;
         case 6:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var29);
            break;
         case 7:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var1, var29);
            break;
         case 8:
            var12 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 9:
            var13 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 10:
         case 11:
         case 12:
         case 13:
         case 17:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var1, var29);
            break;
         case 14:
            var14 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 15:
            var15 = (com.google.android.gms.games.internal.player.zzb)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, com.google.android.gms.games.internal.player.zzb.CREATOR);
            break;
         case 16:
            var16 = (PlayerLevelInfo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, PlayerLevelInfo.CREATOR);
            break;
         case 18:
            var17 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var29);
            break;
         case 19:
            var18 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var29);
            break;
         case 20:
            var19 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 21:
            var20 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 22:
            var21 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, Uri.CREATOR);
            break;
         case 23:
            var22 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 24:
            var23 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var1, var29, Uri.CREATOR);
            break;
         case 25:
            var24 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var1, var29);
            break;
         case 26:
            var25 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var1, var29);
            break;
         case 27:
            var26 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var1, var29);
            break;
         case 28:
            var28 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var1, var29);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var1, var2);
      return new PlayerEntity(var3, var4, var5, var6, var7, var9, var10, var12, var13, var14, var15, var16, var17, var18, var19, var20, var21, var22, var23, var24, var25, var26, var28);
   }

   // $FF: synthetic method
   public Object[] newArray(int var1) {
      return new PlayerEntity[var1];
   }

   // $FF: synthetic method
   public Object createFromParcel(Parcel var1) {
      return this.zzg(var1);
   }
}
