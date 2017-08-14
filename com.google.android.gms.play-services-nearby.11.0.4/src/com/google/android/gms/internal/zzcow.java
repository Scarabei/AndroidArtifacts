package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcow implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcov[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      IBinder var4 = null;
      String[] var5 = null;
      zzcoo var6 = null;
      boolean var7 = false;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzr(var2, var8);
            break;
         case 2:
            var5 = zzb.zzA(var2, var8);
            break;
         case 3:
            var6 = (zzcoo)zzb.zza(var2, var8, zzcoo.CREATOR);
            break;
         case 4:
            var7 = zzb.zzc(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcov(var4, var5, var6, var7);
   }
}
