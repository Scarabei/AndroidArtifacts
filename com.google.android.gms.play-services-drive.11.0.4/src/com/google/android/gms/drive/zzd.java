package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;

public final class zzd implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzc[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      ParcelFileDescriptor var4 = null;
      int var5 = 0;
      int var6 = 0;
      DriveId var7 = null;
      boolean var8 = false;
      String var9 = null;

      while(var2.dataPosition() < var3) {
         int var10;
         switch((var10 = var2.readInt()) & 65535) {
         case 2:
            var4 = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, ParcelFileDescriptor.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var10);
            break;
         case 5:
            var7 = (DriveId)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var10, DriveId.CREATOR);
            break;
         case 6:
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var10);
            break;
         case 7:
            var8 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(var2, var10);
            break;
         case 8:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var10);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzc(var4, var5, var6, var7, var8, var9);
   }
}
