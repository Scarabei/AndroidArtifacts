package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzb[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      long var6 = 0L;
      Uri var8 = null;
      Uri var9 = null;
      Uri var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 2:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 3:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var11);
            break;
         case 4:
            var8 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Uri.CREATOR);
            break;
         case 5:
            var9 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Uri.CREATOR);
            break;
         case 6:
            var10 = (Uri)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Uri.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzb(var4, var5, var6, var8, var9, var10);
   }
}
