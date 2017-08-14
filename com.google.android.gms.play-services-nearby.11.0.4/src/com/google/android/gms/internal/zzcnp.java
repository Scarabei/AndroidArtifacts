package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzcnp implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzcno[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      String var6 = null;
      boolean var7 = false;
      byte[] var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzq(var2, var9);
            break;
         case 2:
            var5 = zzb.zzq(var2, var9);
            break;
         case 3:
            var6 = zzb.zzq(var2, var9);
            break;
         case 4:
            var7 = zzb.zzc(var2, var9);
            break;
         case 5:
            var8 = zzb.zzt(var2, var9);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzcno(var4, var5, var6, var7, var8);
   }
}
