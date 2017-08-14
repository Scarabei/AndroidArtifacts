package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbtq implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbtp[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      int var6 = 0;
      int var7 = 0;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzq(var2, var8);
            break;
         case 3:
            var5 = zzb.zzq(var2, var8);
            break;
         case 4:
            var6 = zzb.zzg(var2, var8);
            break;
         case 5:
            var7 = zzb.zzg(var2, var8);
            break;
         default:
            zzb.zzb(var2, var8);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbtp(var4, var5, var6, var7);
   }
}
