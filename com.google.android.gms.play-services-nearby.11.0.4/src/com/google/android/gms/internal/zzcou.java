package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcou implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcot[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      IBinder var4 = null;
      IBinder var5 = null;
      IBinder var6 = null;
      String var7 = null;
      String var8 = null;
      byte[] var9 = null;
      IBinder var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzr(var2, var11);
            break;
         case 2:
            var5 = zzb.zzr(var2, var11);
            break;
         case 3:
            var6 = zzb.zzr(var2, var11);
            break;
         case 4:
            var7 = zzb.zzq(var2, var11);
            break;
         case 5:
            var8 = zzb.zzq(var2, var11);
            break;
         case 6:
            var9 = zzb.zzt(var2, var11);
            break;
         case 7:
            var10 = zzb.zzr(var2, var11);
            break;
         default:
            zzb.zzb(var2, var11);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcot(var4, var5, var6, var7, var8, var9, var10);
   }
}
