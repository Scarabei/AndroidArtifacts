package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zze;
import com.google.android.gms.drive.events.zzp;
import com.google.android.gms.drive.events.zzt;

public final class zzbks implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbkr[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      DriveId var4 = null;
      int var5 = 0;
      zze var6 = null;
      zzt var7 = null;
      zzp var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DriveId)zzb.zza(var2, var9, DriveId.CREATOR);
            break;
         case 3:
            var5 = zzb.zzg(var2, var9);
            break;
         case 4:
            var6 = (zze)zzb.zza(var2, var9, zze.CREATOR);
            break;
         case 5:
            var7 = (zzt)zzb.zza(var2, var9, zzt.CREATOR);
            break;
         case 6:
            var8 = (zzp)zzb.zza(var2, var9, zzp.CREATOR);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbkr(var4, var5, var6, var7, var8);
   }
}
