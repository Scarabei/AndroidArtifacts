package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbpy implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbpx[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      ParcelFileDescriptor var4 = null;
      IBinder var5 = null;
      String var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = (ParcelFileDescriptor)zzb.zza(var2, var7, ParcelFileDescriptor.CREATOR);
            break;
         case 3:
            var5 = zzb.zzr(var2, var7);
            break;
         case 4:
            var6 = zzb.zzq(var2, var7);
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbpx(var4, var5, var6);
   }
}
