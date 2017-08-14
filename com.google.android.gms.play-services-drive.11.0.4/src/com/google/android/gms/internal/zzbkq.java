package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;

public final class zzbkq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbkp[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      DriveId var5 = null;
      int var6 = 0;
      long var7 = 0L;
      long var9 = 0L;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var11);
            break;
         case 3:
            var5 = (DriveId)zzb.zza(var2, var11, DriveId.CREATOR);
            break;
         case 4:
            var6 = zzb.zzg(var2, var11);
            break;
         case 5:
            var7 = zzb.zzi(var2, var11);
            break;
         case 6:
            var9 = zzb.zzi(var2, var11);
            break;
         default:
            zzb.zzb(var2, var11);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbkp(var4, var5, var6, var7, var9);
   }
}
