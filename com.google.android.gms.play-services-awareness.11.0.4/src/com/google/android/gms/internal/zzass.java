package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzass implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzasu[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      byte[] var6 = null;

      while(var2.dataPosition() < var3) {
         int var7;
         switch((var7 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var7);
            break;
         case 3:
            var5 = zzb.zzq(var2, var7);
            break;
         case 4:
            var6 = zzb.zzt(var2, var7);
            break;
         default:
            zzb.zzb(var2, var7);
         }
      }

      zzb.zzF(var2, var3);
      return new zzasu(var4, var5, var6);
   }
}
