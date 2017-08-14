package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzctw implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzctv[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      zzbp var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 1:
            var4 = zzb.zzg(var2, var6);
            break;
         case 2:
            var5 = (zzbp)zzb.zza(var2, var6, zzbp.CREATOR);
            break;
         default:
            zzb.zzb(var2, var6);
         }
      }

      zzb.zzF(var2, var3);
      return new zzctv(var4, var5);
   }
}
