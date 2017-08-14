package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzj implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzg[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      zzh var5 = null;
      String var6 = null;
      zzm var7 = null;

      while(var2.dataPosition() < var3) {
         int var8;
         switch((var8 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var8);
            break;
         case 3:
            var5 = (zzh)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, zzh.CREATOR);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var8);
            break;
         case 5:
            var7 = (zzm)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var8, zzm.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var8);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzg(var4, var5, var6, var7);
   }
}
