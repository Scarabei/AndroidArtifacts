package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcop implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcoo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      long var4 = 0L;
      int var6 = 0;
      byte[] var7 = null;
      ParcelFileDescriptor var8 = null;
      String var9 = null;
      long var10 = 0L;
      ParcelFileDescriptor var12 = null;

      while(var2.dataPosition() < var3) {
         int var13;
         switch((var13 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzi(var2, var13);
            break;
         case 2:
            var6 = zzb.zzg(var2, var13);
            break;
         case 3:
            var7 = zzb.zzt(var2, var13);
            break;
         case 4:
            var8 = (ParcelFileDescriptor)zzb.zza(var2, var13, ParcelFileDescriptor.CREATOR);
            break;
         case 5:
            var9 = zzb.zzq(var2, var13);
            break;
         case 6:
            var10 = zzb.zzi(var2, var13);
            break;
         case 7:
            var12 = (ParcelFileDescriptor)zzb.zza(var2, var13, ParcelFileDescriptor.CREATOR);
            break;
         default:
            zzb.zzb(var2, var13);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcoo(var4, var6, var7, var8, var9, var10, var12);
   }
}
