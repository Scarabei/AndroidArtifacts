package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzj implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new DriveId[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      long var5 = 0L;
      long var7 = 0L;
      int var9 = -1;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 4:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(var2, var10);
            break;
         case 5:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new DriveId(var4, var5, var7, var9);
   }
}
