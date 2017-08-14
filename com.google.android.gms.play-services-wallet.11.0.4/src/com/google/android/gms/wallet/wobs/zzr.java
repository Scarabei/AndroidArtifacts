package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzr implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzq[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      String var4 = null;
      String var5 = null;
      zzm var6 = null;
      zzo var7 = null;
      zzo var8 = null;

      while(var2.dataPosition() < var3) {
         int var9;
         switch((var9 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var9);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var9);
            break;
         case 4:
            var6 = (zzm)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, zzm.CREATOR);
            break;
         case 5:
            var7 = (zzo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, zzo.CREATOR);
            break;
         case 6:
            var8 = (zzo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var9, zzo.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var9);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new zzq(var4, var5, var6, var7, var8);
   }
}
