package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzb;

public final class zzbpm implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbpl[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      DataHolder var4 = null;
      boolean var5 = false;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DataHolder)zzb.zza(var2, var6, DataHolder.CREATOR);
            break;
         case 3:
            var5 = zzb.zzc(var2, var6);
            break;
         default:
            zzb.zzb(var2, var6);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbpl(var4, var5);
   }
}
