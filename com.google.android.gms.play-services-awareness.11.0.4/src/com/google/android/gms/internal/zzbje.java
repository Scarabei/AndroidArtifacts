package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbje implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbjd[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      long var5 = 0L;
      String var7 = null;
      int var8 = 0;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var9);
            break;
         case 3:
            var5 = zzb.zzi(var2, var9);
            break;
         case 4:
            var7 = zzb.zzq(var2, var9);
            break;
         case 5:
            var8 = zzb.zzg(var2, var9);
            break;
         default:
            zzb.zzb(var2, var9);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbjd(var4, var5, var7, var8);
   }
}
