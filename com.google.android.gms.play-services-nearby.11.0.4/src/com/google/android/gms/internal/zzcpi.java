package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcpi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcph[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      ParcelUuid var5 = null;
      ParcelUuid var6 = null;
      ParcelUuid var7 = null;
      byte[] var8 = null;
      byte[] var9 = null;
      int var10 = 0;
      byte[] var11 = null;
      byte[] var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var13);
            break;
         case 2:
         case 3:
         default:
            zzb.zzb(var2, var13);
            break;
         case 4:
            var5 = (ParcelUuid)zzb.zza(var2, var13, ParcelUuid.CREATOR);
            break;
         case 5:
            var6 = (ParcelUuid)zzb.zza(var2, var13, ParcelUuid.CREATOR);
            break;
         case 6:
            var7 = (ParcelUuid)zzb.zza(var2, var13, ParcelUuid.CREATOR);
            break;
         case 7:
            var8 = zzb.zzt(var2, var13);
            break;
         case 8:
            var9 = zzb.zzt(var2, var13);
            break;
         case 9:
            var10 = zzb.zzg(var2, var13);
            break;
         case 10:
            var11 = zzb.zzt(var2, var13);
            break;
         case 11:
            var12 = zzb.zzt(var2, var13);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcph(var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }
}
