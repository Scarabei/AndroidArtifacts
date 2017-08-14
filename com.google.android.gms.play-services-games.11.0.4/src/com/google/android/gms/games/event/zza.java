package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.games.PlayerEntity;

public final class zza implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new EventEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      Uri var7 = null;
      String var8 = null;
      PlayerEntity var9 = null;
      long var10 = 0L;
      String var12 = null;
      boolean var13 = false;

      while(var2.dataPosition() < var3) {
         int var14;
         switch((var14 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var14);
            break;
         case 2:
            var5 = zzb.zzq(var2, var14);
            break;
         case 3:
            var6 = zzb.zzq(var2, var14);
            break;
         case 4:
            var7 = (Uri)zzb.zza(var2, var14, Uri.CREATOR);
            break;
         case 5:
            var8 = zzb.zzq(var2, var14);
            break;
         case 6:
            var9 = (PlayerEntity)zzb.zza(var2, var14, PlayerEntity.CREATOR);
            break;
         case 7:
            var10 = zzb.zzi(var2, var14);
            break;
         case 8:
            var12 = zzb.zzq(var2, var14);
            break;
         case 9:
            var13 = zzb.zzc(var2, var14);
            break;
         default:
            zzb.zzb(var2, var14);
         }
      }

      zzb.zzF(var2, var3);
      return new EventEntity(var4, var5, var6, var7, var8, var9, var10, var12, var13);
   }
}
