package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzh implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new FilterHolder[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      zzb var4 = null;
      zzd var5 = null;
      zzr var6 = null;
      zzv var7 = null;
      zzp var8 = null;
      zzt var9 = null;
      zzn var10 = null;
      zzl var11 = null;
      zzz var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = (zzb)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzb.CREATOR);
            break;
         case 2:
            var5 = (zzd)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzd.CREATOR);
            break;
         case 3:
            var6 = (zzr)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzr.CREATOR);
            break;
         case 4:
            var7 = (zzv)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzv.CREATOR);
            break;
         case 5:
            var8 = (zzp)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzp.CREATOR);
            break;
         case 6:
            var9 = (zzt)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzt.CREATOR);
            break;
         case 7:
            var10 = (zzn)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzn.CREATOR);
            break;
         case 8:
            var11 = (zzl)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzl.CREATOR);
            break;
         case 9:
            var12 = (zzz)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var13, zzz.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var13);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new FilterHolder(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
