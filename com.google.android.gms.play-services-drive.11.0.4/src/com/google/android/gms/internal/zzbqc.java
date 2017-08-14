package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.DriveId;

public final class zzbqc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbqb[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      DriveId var4 = null;
      int var5 = 0;
      int var6 = 0;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DriveId)zzb.zza(var2, var7, DriveId.CREATOR);
            break;
         case 3:
            var5 = zzb.zzg(var2, var7);
            break;
         case 4:
            var6 = zzb.zzg(var2, var7);
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbqb(var4, var5, var6);
   }
}
